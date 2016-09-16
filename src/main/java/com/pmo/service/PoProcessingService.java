package com.pmo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.pmo.bean.BillLogBean;
import com.pmo.bean.CounterBean;

@Service
public class PoProcessingService {
	
//	public static String INVOICE_HOME = System.getenv().get("INVOICE_HOME") + "\\";
	public static String INVOICE_HOME = "C:\\develop\\code\\SampleApp\\src\\main\\resources\\PO";
//	public static String pdfextractor_properties = INVOICE_HOME + "resources\\pdfextractor.properties";
	public static String pdfextractor_properties = "C:/develop/code/SampleApp/src/main/resources/pdfextractor.properties";
	
//	public static String PO = "C:/develop/code/Reporting/InvoiceGeneration/src/main/resources/PO/4700588583.pdf";
	public CounterBean counterBean = new CounterBean();
	
	public static void main(String args[]) {
		PoProcessingService extract = new PoProcessingService();
		extract.processPurchaseOrder();
	}
	
	public List<BillLogBean> processPurchaseOrder() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(pdfextractor_properties));
			return processByText(props);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<BillLogBean> processByText(Properties props) throws IOException, ParseException {
		List<BillLogBean> mileStonesList = new ArrayList<BillLogBean>();
		String poFolderLocation = INVOICE_HOME + props.getProperty("POFolderLocation");
		
		File folder = new File(poFolderLocation);
		if(folder.isDirectory()) {
			for(File file : folder.listFiles()) {
				if(file.isFile()) {
					try {
						List<BillLogBean> mileStonesPerPO = processPurchaseOrders(file, props);
						counterBean.setProcessedMileStones(mileStonesPerPO.size());
						mileStonesList.addAll(mileStonesPerPO);
						counterBean.setProcessedPOs(1);
					} catch(Exception exception) {
						counterBean.setNonProcessedPOs(1);
						System.out.println("Exception caught:"+exception.getMessage());
						continue;
					}
				}
			}
		} else {
			System.out.println("Please correct the folder location for accessing PO's");
			return null;
		}
		return mileStonesList;
	}
	
	private List<BillLogBean> processPurchaseOrders(File poFile, Properties props) throws Exception {
		PDDocument doc=PDDocument.load(poFile);
		PDFTextStripper pdfStripper=new PDFTextStripper();
		pdfStripper.setStartPage(0);
		pdfStripper.setEndPage(2);
		String fullContent = pdfStripper.getText(doc);
		String split[] = fullContent.split("\r\n");
		String poExtractStart = props.getProperty("POExtractStart");
		String poExtractEnd = props.getProperty("POExtractend");
		String poNumberRef = props.getProperty("PONumberRef");
		String poNumber = null;
		boolean extractStatus = false;
		List<BillLogBean> mileStonesPerPO = new ArrayList<BillLogBean>();
		
		BillLogBean invoiceBean = new BillLogBean();
		for (String str: split) {
			if(str.equals(poExtractStart)) {
				extractStatus = true;
			} else if(str.equals(poExtractEnd)) {
				extractStatus = false;
			}
			if(str.startsWith(poNumberRef)) {
				String lineSplit[] = str.split(" ");
				poNumber = lineSplit[1];
			}
			String previousString = null;
			if(extractStatus) {
				if(/*StringUtils.countOccurrencesOf(str, "EUR") == 2 && */str.endsWith("EUR") && !str.startsWith("TOTAL")) {
					// Re-initializing the invoice bean for next milestone
					invoiceBean = new BillLogBean();
					invoiceBean.setBillingMonth(new SimpleDateFormat("MMM-yy").format(new Date()));
					str = str.replaceAll("\\s+", " ");
					// [1, 12.12.2014, 23,00, EUR, 80,0, HR, 1.840,00, EUR]
					String lineSplit[] = str.split(" ");
					invoiceBean.setItem(lineSplit[0]);
					String invoiceDate = dateConversion(lineSplit[1]);
					invoiceBean.setClientInvoiceDate(invoiceDate);
					String prizePerUnit = lineSplit[2].split(",")[0];
					prizePerUnit = prizePerUnit.replace('.', ',');
					invoiceBean.setPricePerUnit(prizePerUnit);
					String quantity = lineSplit[4].split(",")[0];
					quantity = quantity.replace('.', ',');
					invoiceBean.setQuantity(quantity);
					if(quantity.equals("1")) {
						invoiceBean.setProjectType("FP");
					} else {
						invoiceBean.setProjectType("T&M");
					}
					String mileStoneValue = lineSplit[6].split(",")[0];
					mileStoneValue = mileStoneValue.replace('.', ',');
					invoiceBean.setMilestoneValue(mileStoneValue);
					invoiceBean.setPoNumber(poNumber);
				} else if(str.startsWith("Notes :")) {
					String lineSplit[] = str.split("Notes :");
					invoiceBean.setDescription(lineSplit[1].trim());
					mileStonesPerPO.add(invoiceBean);
				}/* else if(StringUtils.countOccurrencesOf(str, "EUR") == 1 && str.endsWith("EUR") && !str.startsWith("TOTAL")) {
					System.out.println(str);
				}*/
				previousString = str;
			}
		}
		doc.close();
		return mileStonesPerPO;
	}
	
	/**
	 * Conversion of date format from the PO document to bill log.
	 * EG: Conversion from "17.06.2016" to "17-Jun-16".
	 * @param rawdate
	 * @return {@link String}
	 * @throws ParseException 
	 */
	private String dateConversion(String rawdate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date = (Date) formatter.parse(rawdate);
		DateFormat destDf = new SimpleDateFormat("dd-MMM-yy");
		return destDf.format(date);	
	}
}