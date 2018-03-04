package com.ap.mango.utils;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;


public class MongoUtils {


    private static final String DATABASE_NAME="AspirePositivity";
    private static final String COLLECTION_NAME="";
    private static MongoClient serverConnection = null;


    public static DBCollection getCollection() {
        if(serverConnection==null){
            serverConnection = new MongoClient("localhost",27017);
        }
        final DB db = serverConnection.getDB(DATABASE_NAME);
        return db.getCollection(COLLECTION_NAME);
    }
}
