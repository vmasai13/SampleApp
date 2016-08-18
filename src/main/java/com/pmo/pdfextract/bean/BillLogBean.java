package com.pmo.pdfextract.bean;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.pmo.persistence.AbstractDatedDBRecord;

@Document(collection = "billLog")
public class BillLogBean extends AbstractDatedDBRecord {
	private String wonNumber;
	private String projectName;
	private String milestoneDesc;
	private String milestoneValue;
	private String clientInvoiceDate;
	private Date clientInvoiceDateinDate;
	private String customerInvoiceNumber;
	private String vat;
	private String attention;
	private String reference;
	private String comment = "";
	private String totalAmount;
	private String totalAmountInWords;
	private String bankName;
	private String bankAccount;
	private String iBANNo;
	private String swiftCode;
	private String item;
	private String quantity;
	private String PONumber;
	private String pricePerUnit;
	private String empName;
	private String empRole;
	private String billingFrom;
	private String billingTo;
	private String units;
	private String projectType;
	private String invoiceType;
	
	public BillLogBean(Date date, String wonNumber, String projectName, String milestoneDesc, String milestoneValue,
			String clientInvoiceDate, Date clientInvoiceDateinDate, String customerInvoiceNumber, String vat,
			String attention, String reference, String comment, String totalAmount, String totalAmountInWords,
			String bankName, String bankAccount, String iBANNo, String swiftCode, String item, String quantity,
			String pONumber, String pricePerUnit, String empName, String empRole, String billingFrom, String billingTo,
			String units, String projectType, String invoiceType) {
		super(date);
		this.wonNumber = wonNumber;
		this.projectName = projectName;
		this.milestoneDesc = milestoneDesc;
		this.milestoneValue = milestoneValue;
		this.clientInvoiceDate = clientInvoiceDate;
		this.clientInvoiceDateinDate = clientInvoiceDateinDate;
		this.customerInvoiceNumber = customerInvoiceNumber;
		this.vat = vat;
		this.attention = attention;
		this.reference = reference;
		this.comment = comment;
		this.totalAmount = totalAmount;
		this.totalAmountInWords = totalAmountInWords;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.iBANNo = iBANNo;
		this.swiftCode = swiftCode;
		this.item = item;
		this.quantity = quantity;
		this.PONumber = pONumber;
		this.pricePerUnit = pricePerUnit;
		this.empName = empName;
		this.empRole = empRole;
		this.billingFrom = billingFrom;
		this.billingTo = billingTo;
		this.units = units;
		this.projectType = projectType;
		this.invoiceType = invoiceType;
	}
	public BillLogBean() {
		// TODO Auto-generated constructor stub
	}
	public String getClientInvoiceDate() {
		return clientInvoiceDate;
	}
	public void setClientInvoiceDate(String clientInvoiceDate) {
		this.clientInvoiceDate = clientInvoiceDate;
	}
	public Date getClientInvoiceDateinDate() {
		return clientInvoiceDateinDate;
	}
	public void setClientInvoiceDateinDate(Date clientInvoiceDateinDate) {
		this.clientInvoiceDateinDate = clientInvoiceDateinDate;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	public String getBillingFrom() {
		return billingFrom;
	}
	public void setBillingFrom(String billingFrom) {
		this.billingFrom = billingFrom;
	}
	public String getBillingTo() {
		return billingTo;
	}
	public void setBillingTo(String billingTo) {
		this.billingTo = billingTo;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public String getPONumber() {
		return PONumber;
	}
	public void setPONumber(String PONumber) {
		this.PONumber = PONumber;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalAmountInWords() {
		return totalAmountInWords;
	}
	public void setTotalAmountInWords(String totalAmountInWords) {
		this.totalAmountInWords = totalAmountInWords;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getiBANNo() {
		return iBANNo;
	}
	public void setiBANNo(String iBANNo) {
		this.iBANNo = iBANNo;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getWonNumber() {
		return wonNumber;
	}
	public void setWonNumber(String wonNumber) {
		this.wonNumber = wonNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMilestoneDesc() {
		return milestoneDesc;
	}
	public void setMilestoneDesc(String milestoneDesc) {
		this.milestoneDesc = milestoneDesc;
	}
	public String getMilestoneValue() {
		return milestoneValue;
	}
	public void setMilestoneValue(String milestoneValue) {
		this.milestoneValue = milestoneValue;
	}
	/*public Date getClientInvoiceDate() {
		return clientInvoiceDate;
	}
	public void setClientInvoiceDate(Date clientInvoiceDate) {
		this.clientInvoiceDate = clientInvoiceDate;
	}*/
	public String getCustomerInvoiceNumber() {
		return customerInvoiceNumber;
	}
	public void setCustomerInvoiceNumber(String customerInvoiceNumber) {
		this.customerInvoiceNumber = customerInvoiceNumber;
	}
}	
