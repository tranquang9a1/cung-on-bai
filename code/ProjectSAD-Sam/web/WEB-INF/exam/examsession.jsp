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
            <c:forEach var="question" items="${lstQuestion}" varStatus="index">
                ${question.content} </br>
                <c:forEach var="answer" items="${question.tblAnswerList}">
                        <c:choose>
                            <c:when test="${lstType[index.count-1] eq 'checkBox'}">
                            <input type="checkbox" name="answer+${question.questionId}" 
                                   value="${answer.answerId}" checked="false" />    
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="answer+${question.questionId}" 
                                   value="${answer.answerId}" checked="false"/>  
                        </c:otherwise>
                    </c:choose>
                    ${answer.content} </br>
                </c:forEach>
            </c:forEach>
                    <input type="submit" value="Nộp bài"/>
                    <input type="text" name="action" hidden="true" value="submit"/>
                    <c:set var="lstQuestion" value="${lstQuestion}" scope="request"></c:set>
        </form>
    </body>
</html>
