package com.ap.mango.dao.soundrecorder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import com.ap.mango.db.ConnectToDB;
import com.google.inject.Inject;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ap.mango.entity.AudioFiles;
import com.ap.mango.utils.HibernateUtil;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;
import org.springframework.http.HttpStatus;

@Singleton
public class AudioFilesDao  {

    private final DB db= ConnectToDB.getInstance().getDB();

  /*  public static final int MONGO_DB_PORT = 27017;
    private Mongo mongo;*/

    /*@Inject
    public MongoDbAudioFilesDao(final Mongo mongo) {
        this.mongo = mongo;
    }*/


    public GridFSDBFile getAudioFile(final String fileName) {
       // mongo = new Mongo("localhost", MONGO_DB_PORT);
       // final DB db = mongo.getDB("AspirePositivity");
        final GridFS gfs = new GridFS(db,"audio");
        final GridFSDBFile gridFSDBFile = gfs.findOne(fileName);

        return gridFSDBFile;


///Another way of retrieval
       /* final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));


            final MongoDatabase database = mongoClient.getDatabase("AspirePositivity");
            final GridFSBucket gridBucket = GridFSBuckets.create(database);

            final GridFSFile gridFSFile = gridBucket.find(eq("fileName",fileName)).first();
*/
    }



    public List<GridFSDBFile> getAudioFileList(final String user) {
      //  mongo = new Mongo("localhost", MONGO_DB_PORT);
      //  final DB db = mongo.getDB("AspirePositivity");
        final GridFS gfs = new GridFS(db,"audio");
        final BasicDBObject query = new BasicDBObject("user",user);
        final List<GridFSDBFile> gridFSDBFiles = gfs.find(query);
        return gridFSDBFiles;
    }



    public void addNewAudioFile(final InputStream inputStream, final String fileName, final String user) {
   //     final Mongo mongo = new Mongo("localhost", MONGO_DB_PORT);
     //   final DB db = mongo.getDB("AspirePositivity");

        final GridFS gfs = new GridFS(db,"audio");

        final GridFSInputFile gInputFile = gfs.createFile(inputStream, fileName);
        gInputFile.put("user",user);

        gInputFile.save();
        try {
            inputStream.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }


    }


    public void deleteAudioFile(final String fileName) {
     //   final DB db = mongo.getDB("AspirePositivity");
        final GridFS gfs = new GridFS(db);
        final GridFSDBFile gridFSDBFile = gfs.findOne(fileName);


        if (gridFSDBFile == null) {
            return;
        } else {
            // delete file
            gfs.remove(new BasicDBObject(fileName, fileName));
        }
    }
}
