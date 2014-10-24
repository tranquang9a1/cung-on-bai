<%-- 
    Document   : stat
    Created on : Oct 20, 2014, 10:50:54 PM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <th>#</th>
            <th><a class="button small" href="stat?action=user">Tài khoản</a></th>
            <th><a class="button small" href="stat?action=point">Điểm</a></th>
        </tr>
        <c:forEach var="user" items="${users}" varStatus="count">
            <tr>
                <td>${count.index + 1}</td>
                <td>${user.username}</td>
                <td>${user.score}</td>

            </tr>
        </c:forEach>
    </table>        
        </div>
</div>
<%@include file="../include/footer.jsp" %>
