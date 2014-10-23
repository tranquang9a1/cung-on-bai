
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : Oct 19, 2014, 10:28:59 AM
    Author     : dinhquangtrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý người dùng</title>
        <style>
            table td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Quản lý người dùng</h1>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Tài khoản</td>
                <td>Điểm</td>
                <td>Role</td>
                <td>Thao tác</td>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="count">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.score}</td>
                    <td>${user.isAdmin == 1 ? "<span style='color: red'>Admin</span>" : "User"}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.isAdmin == 0}">
                                <a href="?action=setadmin&id=${user.userId}">Đặt làm admin </a>
                            </c:when>
                            <c:otherwise>
                                <a href="?action=removeadmin&id=${user.userId}">Xoá quyền admin </a>
                            </c:otherwise>
                        </c:choose>
                        |
                        <a href="?action=delete&id=${user.userId}">Xoá tài khoản</a>
                    </td>
                </tr>
            </c:forEach>
        </table>        
        <form method="post">
            <input type="text" name="username" placeholder="Tên tài khoản"/>
            <input type="hidden" name="action" value="create"/>
            <input type="submit" value="Thêm user mới"/>
        </form>
        <p>
            <a href="user">Back</a>
        </p>
    </body>
</html>
