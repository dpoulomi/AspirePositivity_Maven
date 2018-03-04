package com.ap.mango.web.controller;

import com.ap.mango.dao.soundrecorder.AudioFilesDao;


import com.ap.mango.entity.Users;
import com.ap.mango.services.AudioFileSaveService;
import com.ap.mango.services.LoginService;
import com.ap.mango.services.S3FileStorageService;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servlet implementation class SoundRecorder
 */
@Singleton
@MultipartConfig
public class SoundRecorderServlet extends HttpServlet {
    /**
     * Serial id
     */
    private static final long serialVersionUID = 1L;
    private final S3FileStorageService s3FileStorageService=new S3FileStorageService();

    @Override

    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException /*{

        final int bytesRead = -1;
        final OutputStream out = null;
        final String path = "/tmp";
        final byte[] bytes = new byte[1024];
        try {

            final String fileName = request.getHeader("Content-Type");
            final String username=request.getParameter("username");
            final InputStream inputStream = request.getInputStream();


            *//*if (inputStream != null) {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                while ((bytesRead = inputStream.read(bytes)) > 0) {
                    out.write(bytes, 0, bytesRead);
                }
            }*//*

            final AudioFilesDao mongoDbAudioFilesDao = new AudioFilesDao();
            mongoDbAudioFilesDao.addNewAudioFile(inputStream, fileName);
            mongoDbAudioFilesDao.getAudioFile(fileName);
        } catch (final IOException ex) {
            throw ex;
        }

        response.sendRedirect("soundRecord.jsp");
    }*/


    {


        try {
            final List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            final Map<String, FileItem> result =
                    items.stream().collect(Collectors.toMap(FileItem::getName, c -> c));
            final LoginService loginService = new LoginService();
            final HttpSession session = request.getSession(true);
            final Users user = (Users) session.getAttribute("user");
            String fieldValue = "";
            for (final FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    final String fieldName = item.getFieldName();
                    fieldValue = item.getString();
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    final String fieldName = item.getFieldName();
                    final String fileName = FilenameUtils.getName(item.getName());
                    final InputStream inputStream = item.getInputStream();
                    final AudioFilesDao mongoDbAudioFilesDao = new AudioFilesDao();
                    mongoDbAudioFilesDao.addNewAudioFile(inputStream, fieldValue, loginService.extractFieldValueFromJson(user, "username"));
                //    s3FileStorageService.fileUploadToS3(inputStream);
                }
            }
        } catch (final FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        response.sendRedirect("soundRecord.jsp");
    }
}
