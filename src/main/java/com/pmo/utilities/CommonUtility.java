package com.pmo.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.pmo.bean.BillLogBean;

public class CommonUtility {
	
	public static boolean checkPreConditionForCreateInvoice(BillLogBean billLogBean) {
		if (StringUtils.isNotEmpty(billLogBean.getCustomerInvoiceNumber())) {
			if (StringUtils.equals(billLogBean.getMilestoneStatus(), "Approved") || StringUtils.equals(billLogBean.getInvoiceStatus(), "Create")) {
				return true;
			}
		}
		return false;
	}
	
	public static String getDateInddMMMyyyy(String clientInvoiceDate) {
		String dateStr= "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		try {
			Date invoiceDate;
			invoiceDate = (Date) formatter.parse(clientInvoiceDate);
			DateFormat destDf = new SimpleDateFormat("dd-MMM-yyyy");
			dateStr = destDf.format(invoiceDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error during parsing Date : " + e);
		}
		return dateStr;
	}

}
