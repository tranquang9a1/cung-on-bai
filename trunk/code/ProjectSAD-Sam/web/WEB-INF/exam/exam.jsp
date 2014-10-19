<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : exam
    Created on : Oct 19, 2014, 11:34:16 AM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam</title>
    </head>
    <body>
        <h1>Exam</h1>
        <form action="ExamServlet" method="POST">
            <table>
                <tr>
                    <td>
                        Subject:
                    </td>
                    <td>
                        <select name="subject">
                            <c:forEach var="lst" items="${lstSubject}">
                                <option value="${lst.subjectId}">${lst.subjectName}</option>
                            </c:forEach>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <td>
                       Number of question: 
                    </td>
                    <td>
                          <input type="text" name="numberQuestion" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Use favorite question:  
                    </td>
                    <td>
                         <input type="radio" name="isFavoriteQS" value="" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="" value="Làm bài"/> 
                        <input type="text" hidden="true" name="action" value="start"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
