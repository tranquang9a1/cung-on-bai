<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : examsession
    Created on : Oct 19, 2014, 3:14:49 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Session</title>
    </head>
    <body>
        <h1>Exam</h1>
        <form>
            <c:forEach var="question" items="${lstQuestion}">
                ${question.content} </br>
                <c:forEach var="answer" items="${question.tblAnswerList}">
                    ${answer.content} </br>
                </c:forEach>
            </c:forEach>
                    <input type="submit" value="Nộp bài"/>
                    <input type="text" hidden="true" value="submit"/>
        </form>
    </body>
</html>
