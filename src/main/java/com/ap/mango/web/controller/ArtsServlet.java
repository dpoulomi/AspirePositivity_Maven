/*
package com.ap.mango.web.controller;

import javax.servlet.http.HttpServlet;

public class ArtsServlet extends HttpServlet {

try {
        final List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        final Map<String, FileItem> result =
                items.stream().collect(Collectors.toMap(FileItem::getName, c -> c));
        final LoginService loginService = new LoginService();
        final HttpSession session = request.getSession(true);
        final Users user = (Users) session.getAttribute("user");
*/
/*for(final String key:result.keySet()){
    if(key.equals("soundFileName")){}
final String filNm=result.get(key);

}*//*

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
            }
        }
    } catch (final FileUploadException e) {
        throw new ServletException("Cannot parse multipart request.", e);
    }

        response.sendRedirect("soundRecord.jsp")
}
}
*/
