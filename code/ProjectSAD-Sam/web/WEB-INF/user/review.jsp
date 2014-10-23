<%-- 
    Document   : review
    Created on : Oct 23, 2014, 9:43:22 AM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review bài làm</title>
        <style>
            table td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Review bài làm</h1>
        <table border="1">
            <tr>
                <td>Session ID</td>
                <td>Tài khoản</td>
                <td>Subject ID</td>
                <td>Ngày làm</td>
                <td>Kết thúc</td>
                <td>Thao tác</td>
            </tr>
            <c:forEach var="sess" items="${sessions}" varStatus="count">
                <tr>
                    <td>${sess.sessionId}</td>
                    <td>${sess.userId.username}</td>
                    <td>${sess.subjectId.subjectName}</td>
                    <td>${sess.startedDate}</td>
                    <td>${sess.endedDate}</td>
                    <td>
                        <a href="?action=viewDetail&id=${sess.sessionId}">Xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
        </table>        
    </body>
</html>
