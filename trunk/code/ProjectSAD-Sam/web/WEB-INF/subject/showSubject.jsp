<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : showSubject
    Created on : Oct 21, 2014, 3:02:46 PM
    Author     : Huydt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favorite Question Page</title>
    </head>
    <body>
        <h1>List Favorite Question</h1>
        <table border="1">
            <thead>
            <td>
                questionId
            </td>
            </thead>
            <tbody>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td>
                        ${subject.subjectId}
                    </td>
                     <td>
                        ${subject.subjectName}
                    </td>
                </tr>
            </c:forEach>   
            </tbody>

        </table>
        <form method="post">
            <input type="text" name="subjectName" placeholder="Tên môn học"/>
            <input type="hidden" name="action" value="create"/>
            <input type="submit" value="Thêm môn học mới"/>
        </form>

    </body>
</html>
