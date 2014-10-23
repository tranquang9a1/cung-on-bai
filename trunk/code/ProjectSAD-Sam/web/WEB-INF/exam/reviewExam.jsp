<%-- 
    Document   : reviewExam
    Created on : Oct 23, 2014, 10:41:41 AM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xem lại bài làm</title>
    </head>
    <body>
        <c:forEach var="question" items="${lstDecorator}" varStatus="index">
                    ${question.question.content} </br>
                    <c:forEach var="answer"
                               items="${question.question.tblAnswerList}" varStatus="indexAnswer">
                        <c:choose>
                            <c:when test="${question.type eq 'checkBox'}">
                                <input disabled="true" type="checkbox" 
                                       name="${question.question.questionId}"  
                                       value="${answer.answerId}" />    
                            </c:when>
                            <c:otherwise>
                                <input disabled="true" type="radio" 
                                       name="${question.question.questionId}" 
                                       value="${answer.answerId}" />  
                            </c:otherwise>
                        </c:choose>
                        ${answer.point} - ${answer.content} </br>
                    </c:forEach>
                    Selected answer: ${question.answer.content}
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                </c:forEach>
    </body>
</html>
