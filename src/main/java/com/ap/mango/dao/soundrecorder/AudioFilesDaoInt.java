package com.ap.mango.dao.soundrecorder;

import java.io.InputStream;
import java.util.List;

import com.ap.mango.entity.AudioFiles;
import com.mongodb.gridfs.GridFSDBFile;

public interface AudioFilesDaoInt {

	//public List<AudioFiles> getAllAudioFiles();

public 	void addNewAudioFile(final InputStream inputStream, final String fileName  );

	GridFSDBFile getAudioFile(String fileName );

  //  void updateAudioFile( AudioFiles audioFiles );
    void deleteAudioFile( String fileName);





}
