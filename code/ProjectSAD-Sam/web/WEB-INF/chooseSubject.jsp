<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : chooseSubject
    Created on : Oct 19, 2014, 4:34:51 PM
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
        <form action="QuestionServlet" method="POST">
            <input type="text" name="type" value="insertPage" />
            <select name="subjectId">
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <option value="${subject.subjectId}">${subject.subjectName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="insertQuestion" name="action" />
        </form>
    </body>
</html>
