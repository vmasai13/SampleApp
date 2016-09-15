package com.pmo.pdfextract.bean;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.pmo.persistence.AbstractDatedDBRecord;

@Document(collection = "billLog")
public class BillLogBean extends AbstractDatedDBRecord {
	
	/*private String vat;
	private String attention;
	private String reference;
	private String comment = "";
	private String totalAmount;
	private String totalAmountInWords;
	private String bankName;
	private String bankAccount;
	private String iBANNo;
	private String swiftCode;
	private String empName;
	private String empRole;
	private String billingFrom;
	private String billingTo;
	private String units;
	private String invoiceType;*/
	
	/*	Bean objects used for Bill log jtable */
    private String billingMonth;
    private String won;
    private String projectType;
	private String project;
	private String description;
	private String customerInvoiceNumber;
    private String klmPm;
    private String milestoneStatus;
    private String invoiceStatus;
    private String poNumber;
    private String clientInvoiceDate;
    private String item;
    private String quantity;
	private String pricePerUnit;
	
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public String getWon() {
		return won;
	}
	public void setWon(String won) {
		this.won = won;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCustomerInvoiceNumber() {
		return customerInvoiceNumber;
	}
	public void setCustomerInvoiceNumber(String customerInvoiceNumber) {
		this.customerInvoiceNumber = customerInvoiceNumber;
	}
	public String getKlmPm() {
		return klmPm;
	}
	public void setKlmPm(String klmPm) {
		this.klmPm = klmPm;
	}
	public String getMilestoneStatus() {
		return milestoneStatus;
	}
	public void setMilestoneStatus(String milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getClientInvoiceDate() {
		return clientInvoiceDate;
	}
	public void setClientInvoiceDate(String clientInvoiceDate) {
		this.clientInvoiceDate = clientInvoiceDate;
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
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public String getMilestoneValue() {
		return milestoneValue;
	}
	public void setMilestoneValue(String milestoneValue) {
		this.milestoneValue = milestoneValue;
	}
	public String getMilestoneValuewithVAT() {
		return milestoneValuewithVAT;
	}
	public void setMilestoneValuewithVAT(String milestoneValuewithVAT) {
		this.milestoneValuewithVAT = milestoneValuewithVAT;
	}
	public String getTcsInvoiceMapping1() {
		return tcsInvoiceMapping1;
	}
	public void setTcsInvoiceMapping1(String tcsInvoiceMapping1) {
		this.tcsInvoiceMapping1 = tcsInvoiceMapping1;
	}
	public String getTcsInvoiceMapping2() {
		return tcsInvoiceMapping2;
	}
	public void setTcsInvoiceMapping2(String tcsInvoiceMapping2) {
		this.tcsInvoiceMapping2 = tcsInvoiceMapping2;
	}
	public String getTcsInvoiceMapping3() {
		return tcsInvoiceMapping3;
	}
	public void setTcsInvoiceMapping3(String tcsInvoiceMapping3) {
		this.tcsInvoiceMapping3 = tcsInvoiceMapping3;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private String milestoneValue;
	private String milestoneValuewithVAT;
	private String tcsInvoiceMapping1;
	private String tcsInvoiceMapping2;
	private String tcsInvoiceMapping3;
	private String remarks;

	public BillLogBean(Date date,String billingMonth, String won, String projectType, String project, String description,
			String customerInvoiceNumber, String klmPm, String milestoneStatus, String invoiceStatus, String poNumber,
			String clientInvoiceDate, String item, String quantity, String pricePerUnit, String milestoneValue,
			String milestoneValuewithVAT, String tcsInvoiceMapping1, String tcsInvoiceMapping2,
			String tcsInvoiceMapping3, String remarks) {
		super(date);
		this.billingMonth = billingMonth;
		this.won = won;
		this.projectType = projectType;
		this.project = project;
		this.description = description;
		this.customerInvoiceNumber = customerInvoiceNumber;
		this.klmPm = klmPm;
		this.milestoneStatus = milestoneStatus;
		this.invoiceStatus = invoiceStatus;
		this.poNumber = poNumber;
		this.clientInvoiceDate = clientInvoiceDate;
		this.item = item;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.milestoneValue = milestoneValue;
		this.milestoneValuewithVAT = milestoneValuewithVAT;
		this.tcsInvoiceMapping1 = tcsInvoiceMapping1;
		this.tcsInvoiceMapping2 = tcsInvoiceMapping2;
		this.tcsInvoiceMapping3 = tcsInvoiceMapping3;
		this.remarks = remarks;
	}
	public BillLogBean() {
		// TODO Auto-generated constructor stub
	}
}	
