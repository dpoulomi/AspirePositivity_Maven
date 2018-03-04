package com.ap.mango.web.controller;


import com.ap.mango.dao.soundrecorder.AudioFilesDao;
import com.ap.mango.entity.AudioFiles;
import com.google.inject.Singleton;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Singleton
public class UserSpecificPlaylistServlet extends HttpServlet {

    /**
     * Serial id
     */
    private static final long serialVersionUID = 1L;


    /*@Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        final AudioFilesDao audioFilesDao = new AudioFilesDao();
        final String fileName = request.getParameter("fileName");
        final GridFSDBFile gridFSDBFile = audioFilesDao.getAudioFile(fileName);

        int bytesRead = -1;
        OutputStream out = null;
        final String path = "/tmp";
        final byte[] bytes = new byte[1024];
        try {
            final InputStream inputStream = gridFSDBFile.getInputStream();
            if (inputStream != null) {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                while ((bytesRead = inputStream.read(bytes)) > 0) {
                    out.write(bytes, 0, bytesRead);
                }
            }

        } catch (final IOException ex) {
            throw ex;
        }

        response.sendRedirect("home.jsp");
    }*/


    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        final DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        final File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String fileName = request.getParameter("fileName");

       /* if(fileName == null || fileName.equals("")){
            throw new ServletException("File Name can't be null or empty");
        }
        final AudioFilesDao audioFilesDao = new AudioFilesDao();
        final GridFSDBFile gridFSDBFile = audioFilesDao.getAudioFile(fileName);
        final File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
        *//*if(!file.exists()){
            throw new ServletException("File doesn't exists on server.");
        }*//*
        System.out.println("File location on server::"+file.getAbsolutePath());
        final ServletContext ctx = getServletContext();
        final InputStream fis = gridFSDBFile.getInputStream();
     //   final InputStream fis = new FileInputStream(file);
        final String mimeType = ctx.getMimeType(file.getAbsolutePath());

        response.setContentType("text/csv"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
        response.setHeader("Content-Disposition", "attachment; filename=name.csv"); // Force "Save As" dialogue.
       // response.getWriter().write(csvAsString); //




       *//* response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");*//*

        final ServletOutputStream os       = response.getOutputStream();
        final byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
        response.sendRedirect("home.jsp");
    }*/


        response.setContentType("audio/mpeg3");
        final PrintWriter out = response.getWriter();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        final AudioFilesDao audioFilesDao = new AudioFilesDao();
        final GridFSDBFile gridFSDBFile = audioFilesDao.getAudioFile(fileName);
        final InputStream fis = gridFSDBFile.getInputStream();
        int i;
        while ((i = fis.read()) != -1) {
            out.write(i);
        }
        fis.close();
        out.close();
        response.sendRedirect("home.jsp");
    }

}