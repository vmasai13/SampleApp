package com.pmo.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class TestClass {
	
	public static void main(String args[]) {
		TestClass test = new TestClass();
		try {
			test.checkDate();
//			test.checkMongodb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkMongodb() {
		Mongo mongo = new Mongo("127.0.0.1", 27017);
		DB db = mongo.getDB("sampleapp");
		DBCollection collection = db.getCollection("billLog");

		/*// convert JSON to DBObject directly
		DBObject dbObject = (DBObject) JSON
				.parse("{'poNumber':'mkyong', 'age':30}");

		collection.insert(dbObject);
		
		DBCursor cursorDoc = collection.find();
		while (cursorDoc.hasNext()) {
			System.out.println(cursorDoc.next());
		}
*/
		System.out.println("Done");
	}
	
	private void checkDate() throws ParseException {
		String date1 = "31-Jul-16"; // 17-Jun-16
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = (Date) formatter.parse(date1);
		DateFormat destDf = new SimpleDateFormat("dd-MMM-yyyy");
		System.out.println(destDf.format(date));
		
		String date2 = new SimpleDateFormat("MMM-yy").format(new Date());
		System.out.println(date2);
	}

}
