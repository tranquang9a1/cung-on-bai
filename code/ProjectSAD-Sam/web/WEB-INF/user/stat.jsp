<%-- 
    Document   : stat
    Created on : Oct 20, 2014, 10:50:54 PM
    Author     : DuanLA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thống kê điểm</title>
    </head>
    <body>
        <h1>Thống kê điểm</h1>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Tài khoản</td>
                <td>Điểm</td>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="count">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.score}</td>
                    
                </tr>
            </c:forEach>
        </table>        
    </body>
</html>
