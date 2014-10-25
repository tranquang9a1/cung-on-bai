<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : chooseSubject
    Created on : Oct 19, 2014, 4:34:51 PM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../adminInclude/header.jsp" %>
<div class="box-center">
    <h1>Manage Question</h1>
    <form action="QuestionServlet" method="GET">
        <input type="hidden" name="type" value="insertPage" />
        <select name="subjectId" class="form-control" style="width: 200px;">
            <c:forEach var="subject" items="${requestScope.subjects}">
                <option value="${subject.subjectId}" >${subject.subjectName}</option>
            </c:forEach>
        </select>
        <script>
            $("select").selectBoxIt({
                // Sets default text to appear for the drop down
                theme: "bootstrap"

            });
        </script>
        <br><br><input type="submit" value="insertQuestion" name="action" class="btn btn-primary"/>
    </form>
</div>
<%@include file="../adminInclude/footer.jsp" %>