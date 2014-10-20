<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : questionDetailAdminView
    Created on : Oct 19, 2014, 1:01:54 PM
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
        <h1>Hello World!</h1>
        ${requestScope.question.content}</br>
        <c:forEach var="answer" items="${requestScope.question.tblAnswerList}">
            ${answer.content}</BR>
        </c:forEach>
            <a href="QuestionServlet?action=updateQuestion&type=viewUpdatePage&questionId=${requestScope.question.questionId}">Update Question</a>    
    </body>
</html>
