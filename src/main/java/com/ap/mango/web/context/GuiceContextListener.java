package com.ap.mango.web.context;

import com.ap.mango.web.module.DaoModule;
import com.ap.mango.web.module.MangoDependencyModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Module injector creation at application scope.
 * 
 * @author bubai
 */
public class GuiceContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		System.out.println("GuiceContextListener started");
		return Guice.createInjector(new MangoDependencyModule(), new DaoModule());
	}
}
