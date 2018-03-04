package com.ap.mango.services;

import com.ap.mango.dao.soundrecorder.UsersDao;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.userdetails.User;

import com.ap.mango.entity.Users;
import com.ap.mango.utils.HibernateUtil;


public class RegisterService {


    public boolean isUserExists(final Users user) {/*
        final UsersDao usersDao = new UsersDao();
        final Users usr = usersDao.getUserByUserName(user.getUsername());
        if (usr == null) {
            return false;

        }*/
        return true;
   }
}
