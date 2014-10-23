<%-- 
    Document   : review
    Created on : Oct 23, 2014, 9:43:22 AM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@include file="../include/sidebar.jsp" %>
<div class="center-content">
    <h1 class="big-title">Thống kê điểm</h1>
    <style>
        table td {
            padding: 10px;
        }
        table th {
            background-color: rgba(255,255,255,0.2);
            padding: 10px;
        }
        table {
            width: 100%;
            color: white;
            font-size: 20px;
        }
    </style>
    <div id="scroll-div">
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Môn học</td>
                <td>Ngày làm</td>
                <td>Số câu hỏi</td>
                <td>Thời gian</td>
                <td>Thao tác</td>
            </tr>
            <c:forEach var="sess" items="${sessions}" varStatus="count">
                <tr>
                    <td>${sess.sessionId}</td>
                    <td>${sess.subjectId.subjectName}</td>

                    <jsp:useBean id="dateValue" class="java.util.Date" />
                    <jsp:setProperty name="dateValue" property="time" value="${sess.startedDate * 1000}" />

                    <td><fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" /></td>
                    <td>${fn:length(sess.tblSessionQuestionList)}</td>
                    <td><fmt:formatNumber type="number" 
                                      maxFractionDigits="1" 
                                      value="${(sess.startedDate - sess.endedDate) / 60}" /> phút</td>
                    
                    <td>
                        <a class="button small" href="?action=viewDetail&id=${sess.sessionId}">Xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
        </table>        
    </div>
</div>
<%@include file="../include/footer.jsp" %>
