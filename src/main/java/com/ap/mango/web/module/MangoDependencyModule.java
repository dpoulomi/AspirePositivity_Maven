package com.ap.mango.web.module;

//import com.ap.mango.dao.soundrecorder.AudioFilesDao;
//import com.ap.mango.dao.soundrecorder.MongoDbAudioFilesDao;
import com.ap.mango.web.controller.*;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class MangoDependencyModule extends ServletModule {

	@Override
	protected void configureServlets() {
		/**
		 *  URL mapping patterns
		 */
		super.configureServlets();
		serve("/l").with(LoginServlet.class);
		serve("/r").with(RegisterServlet.class);
		serve("/upload").with(SoundRecorderServlet.class);
		serve("/a").with(ActivityServlet.class);
		serve("/playlist").with(UserSpecificPlaylistServlet.class);
		//serve("/arts").with(ArtsServlet.class);
		
		/**
		 *  binding of objects require while injecting
		 */
	//	bind(AudioFilesDao.class).to(MongoDbAudioFilesDao.class).in(Singleton.class);
	}
}
