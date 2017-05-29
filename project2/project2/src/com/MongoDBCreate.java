package com;
import com.mongodb.*;
import com.mongodb.client.*;

public class MongoDBCreate {
	private static MongoClient mongoClient = new MongoClient();
	private static MongoDatabase db=mongoClient.getDatabase("test");
	public static MongoDatabase getMongoDatabase(){
		return db;
	}
}
