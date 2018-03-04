package com.ap.mango.web.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;


public class DaoModule extends AbstractModule {

    private static final String LOCALHOST = "localhost";
    private static final int MONGO_DB_PORT = 27017;

    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public Mongo providesMongoDb() {
        return new Mongo(LOCALHOST, MONGO_DB_PORT);
    }

    /*@Provides
    @Singleton
    @Named("POULOMI")
    public MongoDatabase providesMongoDatabase() {
        final MongoClient mngo = new MongoClient("localhost", MONGO_DB_PORT);
        final MongoCredential credential = MongoCredential.createCredential(
                "POULOMI", "", "".toCharArray());

        final MongoDatabase mongoDtabase = mngo.getDatabase("AspirePositivity");
        return mongoDtabase;
    }

    @Provides
    @Singleton
    @Named("BABAN")
    @Inject
    public MongoDatabase providesMongoDatabaseForBaban(@Named("BABAN_User") final String username) {
        final MongoClient mngo = new MongoClient("localhost", MONGO_DB_PORT);
        final MongoCredential credential = MongoCredential.createCredential(
                username, "", "".toCharArray());

        final MongoDatabase mongoDtabase = mngo.getDatabase("AspirePositivity");
        return mongoDtabase;
    }

    @Provides
    @Singleton
    @Named("BABAN_User")
    @Inject
    public String providesUserName() {
        return "baban";
    }*/

}
