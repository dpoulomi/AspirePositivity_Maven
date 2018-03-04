<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List" %>
<%@page import="com.ap.mango.services.LoginService" %>
<%@page import="java.util.Date" %>
<%@page import="com.ap.mango.entity.Users" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="org.codehaus.jackson.JsonNode" %>
<%@ page import="com.ap.mango.dao.soundrecorder.UsersDao" %>
<%@ page import="com.ap.mango.dao.soundrecorder.AudioFilesDao" %>
<%@ page import="com.mongodb.gridfs.GridFSDBFile" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <title>Welcome Page</title>
</head>
<body>
<center>
    <div id="container">
        <h1>Home Page</h1>
        <b>Welcome to our daily day doze of getting free from boredom...</b><br/>
            <%=new Date()%></br>
        <%
            final Users user = (Users) session.getAttribute("user");

            final LoginService loginService = new LoginService();
            final String firstName = loginService.extractFieldValueFromJson(user, "firstName");
            final String lastName = loginService.extractFieldValueFromJson(user, "lastName");


        %>
        <b>Welcome <%= firstName + " " + lastName%>
        </b> <br/>

        <a href="logout.jsp">Logout</a>
        <a href="AboutUs.jsp">About Us</a>
        </p>

        <form method="post" action="/AspirePositivity/a">
            <!-- <button type="submit" name="button1" value="Add activity for the day"></input>  -->
            <button type="submit" name="button" value="button1">Upload song</button>
            <button type="submit" name="button" value="button2">Arts and Crafts</button>
            <button type="submit" name="button" value="button3">Social Awareness</button>

        </form>

        <!-- <form method="post" action="/AspirePositivity/a">
        <button type="submit" name="button1" value="Add activity for the day"></input>
          <button type="submit" name="button" value="button1">Button 1</button>

        </form> -->


        <table>
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
                <%
					final UsersDao dao =new UsersDao();
                     final Users u = dao.getUserByUserName(loginService.extractFieldValueFromJson(user,"username"),
                     loginService.extractFieldValueFromJson(user,"password"));
                    /* for (final Users u : list) {*/
                 %>
            <tr>
                <td><%= loginService.extractFieldValueFromJson(u, "firstName").toString()%>
                </td>
                <td><%=loginService.extractFieldValueFromJson(u, "lastName").toString()%>
                </td>
                <td><%=loginService.extractFieldValueFromJson(u, "emailId").toString()%>
                </td>
            </tr>
               <%-- <%}%>--%>

            <tbody>
            <tbody>
                <%
                     final AudioFilesDao audioFilesDao = new AudioFilesDao();
                     final List<GridFSDBFile> gridFSDBList = audioFilesDao.getAudioFileList(loginService.extractFieldValueFromJson(user,"username"));
                     for (final GridFSDBFile gridFSDB : gridFSDBList) {
                 %>
            <tr>

                <td><a href="/AspirePositivity/playlist?fileName=<%=gridFSDB.getFilename()%>"
                       onclick="return confirm('Proceed with download ?');"><%=gridFSDB.getFilename()%>
                </a>
                </td>

            </tr>
                <%}%>

            <tbody>

        </table>

        <br/>
    </div>
</center>
</body>
