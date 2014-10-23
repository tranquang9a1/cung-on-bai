<%-- 
    Document   : editSubject
    Created on : Oct 23, 2014, 9:59:09 AM
    Author     : Huydt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <form method="post">
            <input type="hidden" name ="id" value="${subjectId}"/>
            <input type="text" name ="name" value="${subjectName}"/>
            <input type="submit" name ="action" value="edit"/>
        </form>
    </body>
</html>
