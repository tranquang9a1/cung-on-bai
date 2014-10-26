<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : examsession
    Created on : Oct 19, 2014, 3:14:49 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<%@include file="../include/sidebar.jsp" %>
<form id="exam-form" action="ExamServlet" method="POST">
    <div class="center-content">
        <div id="scroll-div" class="wide">
            <div class="exam-session">
                <c:forEach var="question" items="${lstDecorator}" varStatus="index">
                        <input type="checkbox" class="css-checkbox" name="favorite" id="chk-${question.question.questionId}" value="${question.question.questionId}"/>
                        <label class="css-label" for="chk-${question.question.questionId}"></label>
                        <lable for="chk"><b>Câu hỏi ${index.count}:</b></lable>${question.question.content} 
                        </br>
                    <c:forEach var="answer"
                               items="${question.question.tblAnswerList}" varStatus="indexAnswer">
                        <c:choose>
                            <c:when test="${question.type eq 'checkBox'}">
                                <input type="checkbox" 
                                       name="${question.question.questionId}"  
                                       value="${answer.answerId}" />    
                            </c:when>
                            <c:otherwise>
                                <input type="radio" 
                                       name="${question.question.questionId}" 
                                       value="${answer.answerId}" />  
                            </c:otherwise>
                        </c:choose>
                        ${answer.content} </br>
                    </c:forEach>
                    <br/><br/>
                </c:forEach>
            </div>
        </div>
        <p style="text-align: center; margin-top: 40px;">
            <a class='button' href="javascript:$('#exam-form').submit();">Nộp bài</a>
        </p>
    </div>
    <input type="text" name="action" value="submit" hidden="true"/>
    <input type="text" name="startTime" value="${startTime}" hidden="true"/>
</form>
<%@include file="../include/footer.jsp" %>
