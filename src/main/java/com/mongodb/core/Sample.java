package com.mongodb.core;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class Sample {
	public static void main(String[] args) {

		try {

			/**** Connect to MongoDB Silpa ****/
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			DB db = mongo.getDB("testdb");

			/**** Get collection  from 'testdb' ****/
			DBCollection table = db.getCollection("user");

			/**** Insert ****/

			BasicDBObject document = new BasicDBObject();
			document.put("name", "aaa");
			document.put("age", 30);
			document.put("createdDate", new Date());
			table.insert(document);

			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "aaa");
            DBCursor cursor = table.find(searchQuery);

			/*******Delete*******/

			/*while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
			table.remove(new BasicDBObject());*/

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			/**** Update ****/

			BasicDBObject query = new BasicDBObject();
			query.put("name", "aaa");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", "aaa-updated");

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

			/**** Find and display ****/

			BasicDBObject searchQuery2 
				= new BasicDBObject().append("name", "aaa-updated");

			DBCursor cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}
			/**** Done ****/
			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
