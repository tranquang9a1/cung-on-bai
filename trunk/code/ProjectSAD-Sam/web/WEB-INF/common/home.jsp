<%-- 
    Document   : home
    Created on : Oct 19, 2014, 9:25:35 PM
    Author     : dinhquangtrung
--%>

<%@page import="utils.Constants"%>
<%@page import="entity.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--<div class="modal-dialog">
    <div class="modal-dialog-content">
        <h1>Xin chào, <%= ((TblUser) session.getAttribute(Constants.VAR_SESSION_USER)).getUsername()%>!</h1>
        <a href="user?action=logout">Logout</a>
    </div>
</div>-->

<%@include file="../include/sidebar.jsp" %>
<div class="center-content">
    <h2 class="big-title">Chọn môn học</h2>
    <div class="searchbox">
        <input class="textbox" placeholder="Tìm kiếm"/>
    </div>
    <div id="scroll-div">
        <ul class="menu bg">
            <c:forEach var="lst" items="${lstSubject}">
                <li value="${lst.subjectId}"><a href="#">${lst.subjectName}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<%@include file="../include/footer.jsp" %>