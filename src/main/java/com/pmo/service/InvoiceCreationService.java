package com.pmo.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.pmo.bean.BillLogBean;
import com.pmo.bean.InvoiceBean;
import com.pmo.utilities.BankDetails;
import com.pmo.utilities.NumToWords;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class InvoiceCreationService {

	//	public static String INVOICE_HOME = System.getenv().get("INVOICE_HOME");
	public static String INVOICE_HOME = "C:\\develop\\code\\SampleApp\\src\\main\\resources\\InvoiceGeneration";
	public static final String TEMPLATE_FILE = INVOICE_HOME + "\\report.jrxml";
	public static final String TEMPLATE_FILE_JUCC = INVOICE_HOME + "\\TM.jrxml";
	public static final String OUTPUT_PATH = INVOICE_HOME + "\\report\\";
	public static final String INPUT_FILE = INVOICE_HOME + "\\KLM-MasterOSTracker.xlsx";
	public static final String TIMESHEET_PATH = INVOICE_HOME + "\\timesheets\\";
	private static final String WON_JUCC = "2086990";
	private static final String WON_XF = "XF";

	public static String pdfextractor_properties = "C:/develop/code/SampleApp/src/main/resources/pdfextractor.properties";

	public static NumToWords numtowords = new NumToWords();

	public static void main(String[] args) {

		InvoiceCreationService invoiceReport = new InvoiceCreationService();
		//		invoiceReport.createInvoice();		
		List<InvoiceBean> listInvoiceBean = new ArrayList<InvoiceBean>();
		List<InvoiceBean> listInvoiceBeanJUCC = new ArrayList<InvoiceBean>();
		//		readFile(INPUT_FILE, listInvoiceBean, listInvoiceBeanJUCC);
//		readBillLogFile(INPUT_FILE_BILL_LOGS, listInvoiceBean, listInvoiceBeanJUCC, bankDetails, args[0]);
//		createPdf(TEMPLATE_FILE, OUTPUT_PATH, listInvoiceBean);
//		createJUCCPdf(TEMPLATE_FILE_JUCC, OUTPUT_PATH, listInvoiceBeanJUCC);

	}

	public void createInvoice(BillLogBean billLogBean) {
		BankDetails bankDetails = retrieveBankDetails();
		// TODO: For Only PO's to get processed as of now, Will be extended for DVO's also, so then we will set the InvoiceType to "T&M"
		// TODO: Populating the details for DVO's will be done in future
		billLogBean.setInvoiceType("PO");
		InvoiceBean invoiceBean = populateDetailsToInvoiceBean(bankDetails, billLogBean);
		if (billLogBean.getInvoiceType().equals("PO")) {
			createBillLogInvoice(TEMPLATE_FILE, OUTPUT_PATH, invoiceBean);
		} else {
			createDvoInvoice(TEMPLATE_FILE_JUCC, OUTPUT_PATH, invoiceBean);
		}
	}

	/**
	 * Populate invoice details from bill log bean
	 * @param bankDetails
	 * @param billLogBean
	 * @return
	 */
	private InvoiceBean populateDetailsToInvoiceBean(BankDetails bankDetails, BillLogBean billLogBean) {
		InvoiceBean invoiceBean = new InvoiceBean();
		invoiceBean.setWonNumber(billLogBean.getWon());
		invoiceBean.setProjectType(billLogBean.getProjectType());
		invoiceBean.setProjectName(billLogBean.getProject());
		invoiceBean.setInvoiceType("PO");
		invoiceBean.setPONumber(billLogBean.getPoNumber());
		invoiceBean.setReference("PO: " + billLogBean.getPoNumber());
		invoiceBean.setMilestoneDesc(billLogBean.getDescription());
		invoiceBean.setQuantity(billLogBean.getQuantity());
		invoiceBean.setPricePerUnit(billLogBean.getPricePerUnit());

		determineAttentionField(invoiceBean);
		determineReferenceField(invoiceBean);
		BigDecimal milestoneValue = calculateMilestoneValue(invoiceBean);
		if (milestoneValue != null) {
			determineTotalAmount(bankDetails, invoiceBean, milestoneValue);
		}

		invoiceBean.setClientInvoiceDate(billLogBean.getClientInvoiceDate());
		invoiceBean.setCustomerInvoiceNumber(billLogBean.getCustomerInvoiceNumber());
		invoiceBean.setBankAccount(bankDetails.getBankAccount());
		invoiceBean.setBankName(bankDetails.getBankName());
		invoiceBean.setiBANNo(bankDetails.getiBANNo());
		invoiceBean.setSwiftCode(bankDetails.getSwiftCode());
		return invoiceBean;
	}


	public static void readBillLogFile(String fileName, List<InvoiceBean> listInvoiceBean, 
			List<InvoiceBean> listInvoiceBeanJUCC, BankDetails bankDetails, String billingType) {
		System.out.println("Begin reading the bill log file: " + fileName);

		try {
			InputStream inputStream = new FileInputStream(fileName);

			Workbook workBook;
			workBook = WorkbookFactory.create(inputStream);

			// Formatter to format numeric fields
			NumberFormat f = NumberFormat.getInstance();
			f.setGroupingUsed(false);

			if(billingType.equalsIgnoreCase("DVO")) {
				System.out.println("Begin reading of DVO logs:"+workBook.getSheetName(1));
				// Read only DVO bill log sheet for creating DVO invoices
				for (Row row : workBook.getSheetAt(1)) {
					if (row.getRowNum() == 0)
						continue;
					try {
						String createStatus = (row.getCell(4) != null) ? row.getCell(4).toString() : "";
						String customerInvoiceNumber =  (row.getCell(3) != null) ? row.getCell(3).toString() : "";
						/* Valid condition:
							1. customerInvoiceNumber should be present.
							2. createStatus should be 'Create'*/
						if(!createStatus.isEmpty() && createStatus.equalsIgnoreCase("Create") && !customerInvoiceNumber.isEmpty()) {
							// System.out.println(row.getRowNum()); // vijay
							InvoiceBean dataBean = retrieveDVOInovice(f,
									bankDetails, row);
							if (dataBean.getProjectType().equals("FP")) {
								listInvoiceBean.add(dataBean);
							} else if (dataBean.getProjectType().equals("T&M")) {
								listInvoiceBeanJUCC.add(dataBean);
							}
						}
					} catch (Exception e) {
						System.out.println("Exception occured while processing row no in :" + workBook.getSheetName(1) + " row :"+ row.getRowNum() + ": exception:" + e);
						e.printStackTrace();
					}
				}

			}

			if(billingType.equalsIgnoreCase("PO")) {
				System.out.println("Begin reading of PO logs:"+workBook.getSheetName(0));
				// Read only PO bill log sheet for creating PO invoices
				for (Row row : workBook.getSheetAt(0)) {
					if (row.getRowNum() == 0 || row.getRowNum() == 1)
						continue;
					try {
						// Milestone status
						String milestoneStatus = (row.getCell(6) != null) ? row.getCell(6).toString() : "";
						// Invoice status
						String invoiceStatus = (row.getCell(7) != null) ? row.getCell(7).toString() : "";
						// KLM Invoice #
						String customerInvoiceNumber = (row.getCell(4) != null) ? row.getCell(4).toString() : "";
						/* Valid condition:
							1. customerInvoiceNumber should be present.
							2. milestoneStatus should be 'Approved'.
							3. invoiceStatus should be 'empty' (or) should not be 'Submitted'*/
						if(!milestoneStatus.isEmpty() && milestoneStatus.equalsIgnoreCase("Approved") && !customerInvoiceNumber.isEmpty()
								&& (invoiceStatus.equalsIgnoreCase("Create"))) {
							//							System.out.println("Initial :" + row.getRowNum()); // vijay
							InvoiceBean dataBean = retrieveBillLogsInovice(f,
									bankDetails, row);
							if (!dataBean.getTotalAmount().matches("0.00")) {
								if (dataBean.getInvoiceType().equals("PO")) {
									listInvoiceBean.add(dataBean);
								} else if (dataBean.getInvoiceType().equals("T&M")) {
									listInvoiceBeanJUCC.add(dataBean);
								}
							}
						}
					} catch (Exception e) {
						System.out.println("Exception occured while processing row no in :" + workBook.getSheetName(0) + " row :"+ row.getRowNum() + ": exception:" + e);
						e.printStackTrace();
					}
				}
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished reading the excel file.:"+listInvoiceBean.size() + ":"+listInvoiceBeanJUCC.size());
	}


	/**
	 * Reading the bank details from KLM-MasterOSTracker.xlsx
	 * @param fileName
	 * @return
	 */
	public static BankDetails retrieveBankDetails() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(pdfextractor_properties));
		} catch (IOException e) {
			System.out.println("Unable to retrieve the bank details for preparing invoice");
		}

		// Formatter to format numeric fields
		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(false);

		// Retrieve bank details
		BankDetails bankDetails = new BankDetails();
		bankDetails.setBankName(props.getProperty("BankName"));
		bankDetails.setBankAccount(props.getProperty("BankAccountNumber"));
		bankDetails.setiBANNo(props.getProperty("IBANNumber"));
		bankDetails.setSwiftCode(props.getProperty("SwiftCode"));
		bankDetails.setVatPercentage(new BigDecimal(props.getProperty("VatPercentage")));
		return bankDetails;
	}

	/**
	 * Read the excel file and populate the list of bean objects
	 * 
	 * @param fileName
	 * @return list of InvoiceBean objects
	 */
	public static void readFile(String fileName, List<InvoiceBean> listInvoiceBean, List<InvoiceBean> listInvoiceBeanJUCC ) {
		System.out.println("Begin reading the excel file.");

		try {
			InputStream inputStream = new FileInputStream(fileName);

			Workbook workBook;
			workBook = WorkbookFactory.create(inputStream);

			// Formatter to format numeric fields
			NumberFormat f = NumberFormat.getInstance();
			f.setGroupingUsed(false);

			// Retrieve bank details
			BankDetails bankDetails = null; //retrieveBankdetails(f, workBook);

			// Read each invoice row and populate the bean list
			for (Row row : workBook.getSheetAt(1)) {
				if (row.getRowNum() == 0 || row.getRowNum() == 1)
					continue;
				try {
					InvoiceBean dataBean = retrieveInvoiceDetails(f,
							bankDetails, row);
					if (dataBean.getProjectType().equals("FP")) {
						listInvoiceBean.add(dataBean);
					} else if (dataBean.getProjectType().equals("T&M")) {
						listInvoiceBeanJUCC.add(dataBean);
					}
				} catch (Exception e) {
					System.out
					.println("Exception occured while processing row no."
							+ row.getRowNum() + ":");
					e.printStackTrace();
				}
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished reading the excel file.");
	}

	/**
	 * Method to retrieve DVO invoice bill logs
	 * @param f
	 * @param bankDetails
	 * @param row
	 * @return
	 */
	private static InvoiceBean retrieveDVOInovice(NumberFormat f,
			BankDetails bankDetails, Row row) {
		InvoiceBean dvoDataBean = new InvoiceBean();
		// WON
		dvoDataBean.setWonNumber((row.getCell(0) != null) ? f.format(row
				.getCell(0).getNumericCellValue()) : "");
		//Project
		dvoDataBean.setProjectName((row.getCell(1) != null) ? row.getCell(1)
				.toString() : "");
		// Hard-coded
		dvoDataBean.setProjectType("T&M");
		// Hard-coded
		dvoDataBean.setInvoiceType("DVO");
		// DVO #
		dvoDataBean.setPONumber(!(row.getCell(5).toString().length() == 0) ? ((row
				.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) ? row
						.getCell(5).toString() : f.format(row.getCell(5)
								.getNumericCellValue())) : "");
		dvoDataBean.setReference("PO: " + dvoDataBean.getPONumber());
		// Hard-coded
		dvoDataBean.setMilestoneDesc("Fees for services rendered by our personnel as per Annexure attached");
		// Billable Effort
		dvoDataBean.setQuantity((row.getCell(8) != null) ? f.format(row.getCell(
				8).getNumericCellValue()) : "");
		// Rate
		dvoDataBean.setPricePerUnit((row.getCell(9) != null) ? formatCurrency(new BigDecimal(row.getCell(
				9).getNumericCellValue())) : "");
		// Billable amount
		BigDecimal milestoneValue = calculateMilestoneValue(dvoDataBean);
		if (milestoneValue != null) {
			determineTotalAmount(bankDetails, dvoDataBean, milestoneValue);
		}

		// Milestone date
		dvoDataBean.setClientInvoiceDate((row.getCell(6) != null) ? row.getCell(
				6).toString() : null);
		/*dvoDataBean.setClientInvoiceDateinString((row.getCell(6) != null) ? row.getCell(
				6).getDateCellValue() : null);*/
		// Invoice #
		dvoDataBean.setCustomerInvoiceNumber((row.getCell(3) != null) ? row
				.getCell(3).toString() : "");
		dvoDataBean.setComment(dvoDataBean.getCustomerInvoiceNumber());
		// Attention
		dvoDataBean.setAttention((row.getCell(19) != null) ? row.getCell(
				19).toString() : "");
		determineAttentionField(dvoDataBean);
		// Name
		dvoDataBean.setEmpName((row.getCell(12) != null) ? row.getCell(12)
				.toString() : "");
		// Role
		dvoDataBean.setEmpRole((row.getCell(13) != null) ? row.getCell(13)
				.toString() : "");
		// Start date
		dvoDataBean.setBillingFrom((row.getCell(14) != null) ? row.getCell(14)
				.toString() : "");
		// End date
		dvoDataBean.setBillingTo((row.getCell(15) != null) ? row.getCell(15)
				.toString() : "");
		// Setting the quantity
		dvoDataBean.setUnits(dvoDataBean.getQuantity() + " Hours(s)");
		// Setting the bank details
		dvoDataBean.setBankAccount(bankDetails.getBankAccount());
		dvoDataBean.setBankName(bankDetails.getBankName());
		dvoDataBean.setiBANNo(bankDetails.getiBANNo());
		dvoDataBean.setSwiftCode(bankDetails.getSwiftCode());
		return dvoDataBean;
	}

	private static InvoiceBean retrieveBillLogsInovice(NumberFormat f,
			BankDetails bankDetails, Row row) {
		InvoiceBean billLogsDataBean = new InvoiceBean();
		// WON
		billLogsDataBean.setWonNumber((row.getCell(1) != null) ? f.format(row
				.getCell(1).getNumericCellValue()) : "");
		// TYPE
		billLogsDataBean.setProjectType((row.getCell(2) != null) ? row.getCell(2)
				.toString() : "");
		//Project
		billLogsDataBean.setProjectName((row.getCell(3) != null) ? row.getCell(3)
				.toString() : "");
		// Hard-coded
		billLogsDataBean.setInvoiceType("PO");
		// PO #
		billLogsDataBean.setPONumber(!(row.getCell(8).toString().length() == 0) ? ((row
				.getCell(8).getCellType() == Cell.CELL_TYPE_STRING) ? row
						.getCell(8).toString() : f.format(row.getCell(8)
								.getNumericCellValue())) : "");
		billLogsDataBean.setReference("PO: " + billLogsDataBean.getPONumber());
		// Milestone Description
		billLogsDataBean.setMilestoneDesc((row.getCell(16) != null) ? row.getCell(16)
				.toString().trim() : "");
		// Qty
		billLogsDataBean.setQuantity((row.getCell(11) != null) ? f.format(row.getCell(
				11).getNumericCellValue()) : "");
		// Price per Unit
		billLogsDataBean.setPricePerUnit((row.getCell(12) != null) ? f.format(row.getCell(
				12).getNumericCellValue()) : "");

		//		billLogsDataBean.setPricePerUnit((row.getCell(12) != null) ? formatCurrency(new BigDecimal(row.getCell(
		//				12).getNumericCellValue())) : "");

		determineAttentionField(billLogsDataBean);
		//determineReferenceField(f, row, billLogsDataBean);

		//		BigDecimal milestoneValue = !(row.getCell(14).toString().length() == 0) ? new BigDecimal(
		// row.getCell(14).toString()) : null;
		BigDecimal milestoneValue = calculateMilestoneValue(billLogsDataBean);
		if (milestoneValue != null) {
			determineTotalAmount(bankDetails, billLogsDataBean, milestoneValue);
		}
		// Milestone Date
		billLogsDataBean.setClientInvoiceDate((row.getCell(9) != null) ? row.getCell(
				9).toString() : null);
		/*billLogsDataBean.setClientInvoiceDateinString((row.getCell(9) != null) ? row.getCell(
				9).toString() : null);*/
		// KLM Invoice #
		billLogsDataBean.setCustomerInvoiceNumber((row.getCell(4) != null) ? row
				.getCell(4).toString() : "");

		// Setting the bank details
		billLogsDataBean.setBankAccount(bankDetails.getBankAccount());
		billLogsDataBean.setBankName(bankDetails.getBankName());
		billLogsDataBean.setiBANNo(bankDetails.getiBANNo());
		billLogsDataBean.setSwiftCode(bankDetails.getSwiftCode());
		return billLogsDataBean;
	}

	/**
	 * Read the specified row and populate InvoiceBean
	 * 
	 * @param f
	 * @param bankDetails
	 * @param row
	 * @return the InvoiceBean object
	 */
	private static InvoiceBean retrieveInvoiceDetails(NumberFormat f,
			BankDetails bankDetails, Row row) {
		InvoiceBean dataBean = new InvoiceBean();
		dataBean.setWonNumber((row.getCell(0) != null) ? f.format(row
				.getCell(0).getNumericCellValue()) : "");
		dataBean.setProjectName((row.getCell(3) != null) ? row.getCell(3)
				.toString() : "");
		dataBean.setProjectType((row.getCell(4) != null) ? row.getCell(4)
				.toString() : "");
		dataBean.setPricePerUnit((row.getCell(26) != null) ? formatCurrency(new BigDecimal(row.getCell(
				26).getNumericCellValue())) : "");
		dataBean.setQuantity((row.getCell(25) != null) ? f.format(row.getCell(
				25).getNumericCellValue()) : "");
		dataBean.setMilestoneDesc((row.getCell(13) != null) ? row.getCell(13)
				.toString().trim() : "");
		dataBean.setAttention((row.getCell(34) != null) ? row.getCell(34)
				.toString().trim() : "");
		//		BigDecimal milestoneValue = !(row.getCell(14).toString().length() == 0) ? new BigDecimal(
		//				row.getCell(14).toString()) : null;
		BigDecimal milestoneValue = !(row.getCell(14).toString().length() == 0) ? new BigDecimal(
				row.getCell(14).toString()) : calculateMilestoneValue(dataBean);
				if (milestoneValue != null) {
					determineTotalAmount(bankDetails, dataBean, milestoneValue);
				}

				dataBean.setEmpName((row.getCell(29) != null) ? row.getCell(29)
						.toString() : "");

				determineAttentionField(dataBean);

				// determineReferenceField(f, row, dataBean);

				/*dataBean.setClientInvoiceDate((row.getCell(16) != null) ? row.getCell(
				16).getDateCellValue() : null);*/
				dataBean.setCustomerInvoiceNumber((row.getCell(17) != null) ? row
						.getCell(17).toString() : "");
				dataBean.setComment((row.getCell(27) != null) ? row.getCell(27)
						.toString() : "");
				dataBean.setEmpRole((row.getCell(30) != null) ? row.getCell(30)
						.toString() : "");
				dataBean.setBillingFrom((row.getCell(31) != null) ? row.getCell(31)
						.toString() : "");
				dataBean.setBillingTo((row.getCell(32) != null) ? row.getCell(32)
						.toString() : "");
				dataBean.setUnits((row.getCell(33) != null) ? row.getCell(33)
						.toString() : "");

				dataBean.setBankAccount(bankDetails.getBankAccount());
				dataBean.setBankName(bankDetails.getBankName());
				dataBean.setiBANNo(bankDetails.getiBANNo());
				dataBean.setSwiftCode(bankDetails.getSwiftCode());
				return dataBean;
	}

	/**
	 * calculateMilestoneValue
	 * 
	 * @param bankDetails
	 * @param dataBean
	 * @param milestoneValue
	 */
	private static BigDecimal calculateMilestoneValue(InvoiceBean dataBean) {
		BigDecimal milestoneValue = new BigDecimal(dataBean.getPricePerUnit()).multiply(new BigDecimal(dataBean.getQuantity()));
		return milestoneValue;
	}

	/**
	 * determineTotalAmount
	 * @param bankDetails
	 * @param invoiceBean
	 * @param milestoneValue
	 */
	private static void determineTotalAmount(BankDetails bankDetails,
			InvoiceBean invoiceBean, BigDecimal milestoneValue) {
		BigDecimal vat = (milestoneValue).multiply(
				bankDetails.getVatPercentage()).divide(new BigDecimal("100"),
						2, BigDecimal.ROUND_HALF_UP);
		BigDecimal totalAmount = milestoneValue.add(vat);

		invoiceBean.setMilestoneValue(formatCurrency(milestoneValue));
		invoiceBean.setVat(formatCurrency(vat));
		invoiceBean.setTotalAmount(formatCurrency(totalAmount));
		invoiceBean.setTotalAmountInWords(convertToWords(totalAmount));
	}

	/**
	 * determineReferenceField
	 * @param f
	 * @param row
	 * @param invoiceBean
	 */
	private static void determineReferenceField(InvoiceBean invoiceBean) {
		// For All "PO" bill logs, we need to have detailed reference
		if (invoiceBean.getInvoiceType().equals("PO")) {
			invoiceBean.setItem(invoiceBean.getItem());
			invoiceBean.setReference("PO: " + invoiceBean.getPONumber() + " / Item: "
					+ invoiceBean.getItem() + " / Qty: " + invoiceBean.getQuantity()
					+ " / Price Per Unit: " + invoiceBean.getPricePerUnit() + " EUR");
		} else if (invoiceBean.getInvoiceType().equals("T&M")) {
			invoiceBean.setReference("PO: " + invoiceBean.getPONumber());
		}

	}

	/**
	 * Format the currency string representation
	 * 
	 * @param amount
	 * @return String the formatted currency
	 */
	private static String formatCurrency(BigDecimal amount) {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(amount.doubleValue()).substring(1);
	}

	/**
	 * determineAttentionField
	 * 
	 * @param invoiceBean
	 */
	private static void determineAttentionField(InvoiceBean invoiceBean) {

		if (invoiceBean.getAttention() == null || invoiceBean.getAttention().equals("")) {
			if (invoiceBean.getWonNumber().equals(WON_JUCC)
					|| invoiceBean.getWonNumber().startsWith(WON_XF)) {
				invoiceBean.setAttention("Mr. C. Driehuis, SPL/XW-93401-0710");
			} else {
				invoiceBean.setAttention("KLM NV");
			}
		}
	}

	/**
	 * Convert the specified currency from numeric to words
	 * 
	 * @param amount
	 * @return String the amount in words
	 */
	private static String convertToWords(BigDecimal amount) {
		String[] parts = amount.toPlainString().split("\\.");
		String amountInWords = "Euro "
				+ numtowords.convert(Integer.parseInt(parts[0]))
				+ ((Integer.parseInt(parts[1]) != 0) ? " And "
						+ numtowords.convert(Integer.parseInt(parts[1]))
						+ " Cents Only." : " Only.");
		return amountInWords;
	}

	/**
	 * Create PDF invoices for the bill log entries
	 * @param templatePath
	 * @param targetPath
	 * @param invoiceBean
	 */
	private static void createBillLogInvoice(String templatePath, String targetPath, InvoiceBean invoiceBean) {
		Map<String, Object> params;
		JasperReport jasperReport;
		try {
			InputStream inputStreamForDetailedLog = new FileInputStream(
					templatePath);
			params = new HashMap<String, Object>();
			JasperDesign jasperDesign = JRXmlLoader
					.load(inputStreamForDetailedLog);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);

			try {
				List<InvoiceBean> tempList = new ArrayList<InvoiceBean>();
				tempList.add(invoiceBean);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						tempList);
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, params, beanColDataSource);
				String outputFile = targetPath
						+ invoiceBean.getCustomerInvoiceNumber()
						+ ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint,
						outputFile);
				System.out.println(outputFile);
			} catch (JRException e) {
				e.printStackTrace();
			}
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create output pdf for jucc POs
	 * 
	 * @param templatePath
	 * @param targetPath
	 * @param contentList
	 * @throws JRException
	 * @throws FileNotFoundException
	 */
	private static void createDvoInvoice(String templatePath, String targetPath, InvoiceBean invoiceBean) {
		Map<String, Object> params;
		JasperReport jasperReport;
		try {
			InputStream inputStreamForDetailedLog = new FileInputStream(
					templatePath);
			params = new HashMap<String, Object>();
			JasperDesign jasperDesign = JRXmlLoader
					.load(inputStreamForDetailedLog);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);

			try {
				List<InvoiceBean> tempList = new ArrayList<InvoiceBean>();
				tempList.add(invoiceBean);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						tempList);
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, params, beanColDataSource);
				String outputFile = targetPath
						+ invoiceBean.getCustomerInvoiceNumber()
						+ ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint,
						outputFile);
				if ((invoiceBean.getCustomerInvoiceNumber() != null) && !invoiceBean.getCustomerInvoiceNumber().equals("")) {
					mergePdf(outputFile, TIMESHEET_PATH + invoiceBean.getCustomerInvoiceNumber() + ".pdf");
				}
			} catch (JRException e) {
				e.printStackTrace();
			}
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print the list of InvoiceBean objects
	 * 
	 * @param dataHolder
	 */
	private static void printCellDataToConsole(List<InvoiceBean> dataHolder1 , List<InvoiceBean> dataHolder2) {

		System.out.println("List of Invoices read:");
		for (InvoiceBean dataBean : dataHolder1) {
			System.out.println(dataBean.toString());
		}
		System.out.println("List of JUCC Invoices read:");
		for (InvoiceBean dataBean : dataHolder2) {
			System.out.println(dataBean.toString());
		}
	}

	/**
	 * Merge pdf
	 * 
	 * @param filenames
	 */
	private static void mergePdf(String file1, String file2) {
		try {
			PDDocument document = new PDDocument();
			PDDocument part1 = PDDocument.load(file1);
			List<PDPage> list = part1.getDocumentCatalog().getAllPages();
			for (PDPage page : list) {
				document.addPage(page);
			}

			PDDocument part2 = PDDocument.load(file2);
			list = part2.getDocumentCatalog().getAllPages();
			for (PDPage page : list) {
				document.addPage(page);
			}

			document.save(file1);
			part1.close();
			part2.close();
			document.close();
		} catch (COSVisitorException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("No signed timesheet was found. Continuing.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
