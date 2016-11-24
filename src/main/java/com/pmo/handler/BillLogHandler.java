package com.pmo.handler;

import org.springframework.stereotype.Component;

import com.pmo.bean.BillLogBean;

@Component
public class BillLogHandler {
	
	public BillLogBean checkAndCreateNewMilestone(BillLogBean billLogBean) {
		BillLogBean newMileStone = billLogBean;
		// Make -> Billing month, milestoneStatus, invoiceStatus, id as null
		// Still check on -> Invoice number, Milestone description, Item no, etc,. 
		newMileStone.setBillingMonth(""); // to new month
		newMileStone.setMilestoneStatus("Check");
		newMileStone.setInvoiceStatus("");
		newMileStone.setCustomerInvoiceNumber("");
		newMileStone.setId("");
		return newMileStone;
	}

}
