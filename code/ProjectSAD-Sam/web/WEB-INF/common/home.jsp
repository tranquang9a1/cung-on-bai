<%-- 
    Document   : home
    Created on : Oct 19, 2014, 9:25:35 PM
    Author     : dinhquangtrung
--%>

<%@page import="utils.Constants"%>
<%@page import="entity.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello, <%= ((TblUser) session.getAttribute(Constants.VAR_SESSION_USER)).getUsername() %>!</h1>
        <a href="user?action=logout">Logout</a>
    </body>
</html>
