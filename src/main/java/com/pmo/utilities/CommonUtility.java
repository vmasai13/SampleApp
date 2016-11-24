package com.pmo.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pmo.bean.BillLogBean;
import com.pmo.persistence.service.SettingsService;

public class CommonUtility {
	
	@Autowired
	SettingsService settingsService;
	
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
	
	public static String getInvoiceNumberFormat(String invoiceDate) {
		String invoiceDateSplit[] = invoiceDate.split("-");
		String month = "";
		switch (invoiceDateSplit[1]) {
			case "Jan": month = "01"; break;
			case "Feb": month = "02"; break;
			case "Mar": month = "03"; break;
			case "Apr": month = "04"; break;
			case "May": month = "05"; break;
			case "Jun": month = "06"; break;
			case "Jul": month = "07"; break;
			case "Aug": month = "08"; break;
			case "Sep": month = "09"; break;
			case "Oct": month = "10"; break;
			case "Nov": month = "11"; break;
			case "Dec": month = "12"; break;
			default: month = "999" ; break;
		}
		return invoiceDateSplit[2] + month;
	}
	
	public static String getIncrementalNumber(int invoiceSuffix) {
		invoiceSuffix = invoiceSuffix + 1;
		String incrementalNumber = Integer.toString(invoiceSuffix);
		int count = 0;
		for (;count < 5 - incrementalNumber.length();) {
			incrementalNumber = "0" + incrementalNumber;
		}
		return incrementalNumber;
	}

}
