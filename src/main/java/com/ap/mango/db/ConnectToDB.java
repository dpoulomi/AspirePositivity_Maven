package com.ap.mango.db;

import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Singleton
public class ConnectToDB {

    private static final int MONGO_DB_PORT = 27017;
    private static final String HOST = "localhost";
    private static final String DATABASE_NAME = "AspirePositivity";
    private static final String USERID = "Poulomi";
    private static final String PASSWORD = "password";


    public static ConnectToDB connectToDB;

    private static final Object lock = new Object();

    public static ConnectToDB getInstance() {
        if (connectToDB == null) {
            synchronized (lock) {
                if (connectToDB == null) {
                    connectToDB = new ConnectToDB();
                }
            }
        }
        return connectToDB;
    }


    public MongoDatabase getMongoDB() {
        final MongoClient mngo = new MongoClient(HOST, MONGO_DB_PORT);
        final MongoCredential credential = MongoCredential.createCredential(
                USERID, DATABASE_NAME, PASSWORD.toCharArray());

        final MongoDatabase mongoDtabase = mngo.getDatabase("AspirePositivity");
        return mongoDtabase;
    }

    public DB getDB() {
        final MongoClient mngo = new MongoClient(HOST, MONGO_DB_PORT);
        final MongoCredential credential = MongoCredential.createCredential(
                USERID, DATABASE_NAME, PASSWORD.toCharArray());
        final DB db = mngo.getDB(DATABASE_NAME);
        return db;
    }
}
