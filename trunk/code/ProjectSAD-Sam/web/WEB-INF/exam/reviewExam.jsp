<%-- 
    Document   : reviewExam
    Created on : Oct 23, 2014, 10:41:41 AM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>
<%@include file="../include/sidebar.jsp" %>
<form id="exam-form" action="ExamServlet" method="POST">
    <div class="center-content">
        <div id="scroll-div" class="wide">
            <div class="exam-session">

                <c:forEach var="question" items="${lstDecorator}" varStatus="index">
                    <div style="margin: 10px 0"><b>Câu hỏi ${index.count}:</b> ${question.question.content} </div>
                    <c:forEach var="answer"
                               items="${question.question.tblAnswerList}" varStatus="indexAnswer">
                        <b style="color: blue; display: inline-block; width: 30px;">${answer.point}</b> - ${answer.content} </br>
                    </c:forEach>
                    <br/>
                    <b>Bạn trả lời:</b> <i>${question.answer.content}</i>
                    <br/>
                    <br/>
                    <br/>
                </c:forEach>
            </div>
        </div>
        <p style="text-align: center; margin-top: 40px;">
            <a class='button' href="review">Quay lại</a>
        </p>
    </div>
    <%@include file="../include/footer.jsp" %>
