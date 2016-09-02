package com.pmo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {
	
	public static void main(String args[]) {
		TestClass test = new TestClass();
		try {
			test.checkDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkDate() throws ParseException {
		String date1 = "17.06.2016"; // 17-Jun-16
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date = (Date) formatter.parse(date1);
		DateFormat destDf = new SimpleDateFormat("dd-MMM-yy");
		System.out.println(destDf.format(date));
		
		String date2 = new SimpleDateFormat("MMM-yy").format(new Date());
		System.out.println(date2);
	}

}
