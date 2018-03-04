package com.ap.mango.services;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;

import javax.inject.Singleton;
import java.io.*;

@NoArgsConstructor
@Singleton
public class AudioFileSaveService {

  /*  public void saveData(final InputStream inputStream, final String fileName) {
        final Mongo mongo = new Mongo("localhost", 27017);
        final DB db = mongo.getDB("AspirePositivity");

        final GridFS gfs = new GridFS(db, "audio");

        final GridFSInputFile gInputFile = gfs.createFile(inputStream, fileName);
        gInputFile.save();
        try {
            inputStream.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
*/
    /*public GridFSDBFile getFile() {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("AspirePositivity");
        GridFS gfs = new GridFS(db);
        GridFSDBFile something = gfs.findOne("something");
        System.out.println(something);
        return something;
    }*/

    public void saveData1(final File file, final String fileName) throws FileNotFoundException {
        //File file = new File("/Users/neo/Downloads/myRecording00.wav");
        final FileInputStream fileInputStream = new FileInputStream(file);

      //  saveData(fileInputStream, fileName);


      //  final GridFSDBFile file1 = audioFileSaveService.getFile();
    }
}
