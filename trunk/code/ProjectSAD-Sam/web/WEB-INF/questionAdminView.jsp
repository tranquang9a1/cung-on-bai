<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : questionAdminView
    Created on : Oct 19, 2014, 11:34:10 AM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Question</h1>
        <c:forEach var="question" items="${requestScope.questions}">
            ${status},${question.content}--> <a href="">showDetail</a>
        </c:forEach>
            <a href="insertQuestion.jsp">insert Question</a>    
    </body>
</html>