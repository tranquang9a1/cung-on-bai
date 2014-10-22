<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : examSession
    Created on : Oct 21, 2014, 9:54:21 PM
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
        <c:choose>
            <c:when test="${empty lstQuestion}">
                <p>Không có câu hỏi nào trong môn học này</p>
            </c:when>
            <c:otherwise>
                <form action="ExamServlet" method="POST">
            <c:forEach var="question" items="${lstQuestion}" varStatus="index">
                ${question.content} </br>
                <c:forEach var="answer"
                           items="${question.tblAnswerList}" varStatus="indexAnswer">
                        <c:choose>
                            <c:when test="${lstType[index.count-1] eq 'checkBox'}">
                            <input type="checkbox" 
                               name="${question.questionId}"  
                                   value="${answer.answerId}" />    
                        </c:when>
                        <c:otherwise>
                            <input type="radio" 
                              name="${question.questionId}" 
                                   value="${answer.answerId}" />  
                        </c:otherwise>
                    </c:choose>
                    ${answer.content} </br>
                </c:forEach>
            </c:forEach>
                    <c:set var="lstQuestion" value="${lstQuestion}" scope="session"></c:set>
                    <input type="submit" value="Nộp bài"/>
                    <input type="text" name="action" value="submit" hidden="true"/>
                    <input type="text" name="startTime" value="${startTime}" hidden="true"/>
        </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>

