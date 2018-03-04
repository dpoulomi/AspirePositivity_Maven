package com.ap.mango.dao.soundrecorder;

import com.ap.mango.db.ConnectToDB;

import com.ap.mango.entity.Users;
import com.ap.mango.services.LoginService;
import com.google.inject.Inject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.Document;

import org.codehaus.jackson.map.ObjectMapper;

import javax.management.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersDao {

    private final DB db = ConnectToDB.getInstance().getDB();
    private final MongoDatabase mongoDatabase = ConnectToDB.getInstance().getMongoDB();
    private final LoginService loginService=new LoginService();

    public boolean addNewUser(final Users user) throws IOException {
        // final Mongo mongo = new Mongo("localhost", 27017);
        // final DB db = mongo.getDB("AspirePositivity");

        if (getUserByUserName((loginService.extractFieldValueFromJson(user, "username")), (loginService.extractFieldValueFromJson(user, "password"))) != null) {
            return false;
        }
        final DBCollection users = db.getCollection(Users.COLLECTION_NAME);
        users.save(user);
        return true;
    }


    public Users getUserByUserName(final String username, final String password) {

        final List<Users> users = new ArrayList<Users>();
        // final ConnectToDB connectToDB = new ConnectToDB();
        //  final MongoDatabase db = connectToDB.getDB();
        final MongoCollection collection = mongoDatabase.getCollection("Users");


       /* final FindIterable<Document> usr=  collection.find(
                new Document("username", username)
                        .append("password", password));*/

         final FindIterable<Document> usr = collection.find(Filters.eq("username", username));
     //   final FindIterable<Document> usr = collection.find(Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
        Users user = null;
        final Iterator itr = usr.iterator();
        while (itr.hasNext()) {
            final Document doc = (Document) itr.next();
            final String json = doc.toJson();
            final ObjectMapper mapper = new ObjectMapper();

            try {
                user = mapper.readValue(json, Users.class);
            } catch (final IOException e) {
                e.printStackTrace();
            }

            users.add(user);


        }
        if (users.size() != 0 && !user.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }


    }

    public List<Users> getListOfUsers(final String username, final String password) {
        final List<Users> users = new ArrayList<>();
        // final ConnectToDB connectToDB = new ConnectToDB();
        //  final MongoDatabase db = connectToDB.getDB();
        final MongoCollection collection = mongoDatabase.getCollection("Users");
        final FindIterable<Document> usr = collection.find(Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
        final Iterator itr = usr.iterator();
        while (itr.hasNext()) {
            final Document doc = (Document) itr.next();
            final String json = doc.toJson();
            final ObjectMapper mapper = new ObjectMapper();
            Users user = null;
            try {
                user = mapper.readValue(json, Users.class);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            users.add(user);
        }
        return users;
    }
}
