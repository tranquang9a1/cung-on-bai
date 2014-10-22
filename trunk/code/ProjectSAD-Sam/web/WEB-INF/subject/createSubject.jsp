<%-- 
    Document   : createSubject
    Created on : Oct 20, 2014, 7:17:57 PM
    Author     : Huydt
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Subjects</title>
        <style>
            table td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Quản Lý Subjects</h1>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Tên môn học</td>
                <td>Thao tác</td>
            </tr>
            <c:forEach var="subject" items="${subjects}" varStatus="count">
                <tr>
                    <td>${subject.subjectId}</td>
                    <td>${subject.subjectName}</td>
                    <td>
                        <a href="?action=delete&id=${subject.subjectId}">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </table>        
        <form method="post">
            <input type="text" name="subjectName" placeholder="Tên môn học"/>
            <input type="hidden" name="action" value="create"/>
            <input type="submit" value="Thêm môn học mới"/>
        </form>
    </body>
</html>
