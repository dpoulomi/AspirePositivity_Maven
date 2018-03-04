package com.ap.mango.web.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FileLocationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        final String rootPath = System.getProperty("catalina.home");
        final ServletContext ctx = servletContextEvent.getServletContext();
        final String relativePath = ctx.getInitParameter("tempfile.dir");
        final File file = new File(rootPath + File.separator + relativePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        System.out.println("File Directory created to be used for storing files");
        ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        //do cleanup if needed
    }

}
