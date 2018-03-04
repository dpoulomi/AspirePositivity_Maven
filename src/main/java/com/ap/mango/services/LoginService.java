package com.ap.mango.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ap.mango.dao.soundrecorder.UsersDao;

import com.ap.mango.entity.Users;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;


public class LoginService {

    public boolean authenticateUser(final String username, final String password) throws IOException {
        final UsersDao usersDao=new UsersDao();
        final Users user = usersDao.getUserByUserName(username, password);
        final ObjectMapper mapper = new ObjectMapper();

        if (user != null) {

            final String field1 = extractFieldValueFromJson(user, "username");
            final String field2 = extractFieldValueFromJson(user, "password");
            if ((!field1.equals("") && field1.equals(username)) && (!field2.equals("") && field2.equals(password))) {
                return true;
        }
    }
    return false;
    }


    public String extractFieldValueFromJson(final Users user, final String field) throws IOException {
        if(user!=null) {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonInString = mapper.writeValueAsString(user);
            final JsonNode jsonNode = mapper.readValue(jsonInString, JsonNode.class);
            final JsonNode jsonNodeField = jsonNode.get(field);
            final String fieldValue = jsonNodeField.asText();
            return fieldValue;
        }
        return "";
    }


    public static void main(final String[] args) throws IOException {
        final Users user = new Users();
        user.put("firstName", "Polo");
        user.put("lastName", "Das");
        user.put("emailId", "polo@xyz.com");
        user.put("password", "polo123");
        user.put("username", "poulomi");


        final LoginService dao = new LoginService();
        dao.authenticateUser("po","jhbbjb");
    }


    /*public Users getUserByUserName(final String username) {
        final List<Users> users = new ArrayList<Users>();
        final ConnectToDB connectToDB = new ConnectToDB();
        final MongoDatabase db = connectToDB.getDB();
        final MongoCollection collection = db.getCollection("Users");
        final FindIterable<Document> usr = collection.find(Filters.eq("username",username));

        final Iterator itr = usr.iterator();
        while (itr.hasNext()) {
            final Document doc= (Document) itr.next();
            final String json = doc.toJson();
            final ObjectMapper mapper = new ObjectMapper();
           // mapper.configure(DeserializeFe.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Users user = null;
            try {
                user = mapper.readValue(json, Users.class);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            users.add(user);

        }

        //final Document document = collection.find(eq("key", value)).first();









       *//* for(Document doc:usr){
      Users  user= (Users) doc.get(username,Users);
      users.add(user);

        }*//*


        *//*BasicDBObject dbObject=new BasicDBObject();
        dbObject.append("username",username);
        Users user=collection.distinct("username",dbObject);*//*


        //  Morphia morphia = new Morphia();
*//*
      //  DBCollection table = db.getCollection("user");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);
       Users user = morphia.fromDBObject(users, searchQuery);
        FindIterable iterable= collection.find(searchQuery);

      //  DBCursor cursor = collection.find(searchQuery);

      //  DBObject dbObject = cursor.iterator();
        MongoCursor dbObject = iterable.iterator();*//*

        return users.get(0);
    }

    public List<Users> getListOfUsers(final String username) {
        final List<Users> users = new ArrayList<Users>();
        final ConnectToDB connectToDB = new ConnectToDB();
        final MongoDatabase db = connectToDB.getDB();
        final MongoCollection collection = db.getCollection("Users");
        final FindIterable<Document> usr = collection.find(Filters.eq("username", username));

        final Iterator itr = usr.iterator();
        while (itr.hasNext()) {
            final Document doc = (Document) itr.next();
            final String json = doc.toJson();
            final ObjectMapper mapper = new ObjectMapper();
            // mapper.configure(DeserializeFe.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Users user = null;
            try {
                user = mapper.readValue(json, Users.class);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            users.add(user);

        }
     

        return users;
    }*/
}
