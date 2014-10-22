<%-- 
    Document   : home
    Created on : Oct 19, 2014, 9:25:35 PM
    Author     : dinhquangtrung
--%>

<%@page import="utils.Constants"%>
<%@page import="entity.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header.jsp" %>

<%@include file="../include/sidebar.jsp" %>
<div class="center-content">
    <h2 class="big-title">Chọn môn học</h2>
    <div class="searchbox">
        <input id='subject-search' class="textbox" placeholder="Tìm kiếm"/>
    </div>
    <div id="scroll-div">
        <ul id='subject-list' class="menu bg">
            <c:forEach var="lst" items="${lstSubject}">
                <li value="${lst.subjectId}"><a href="javascript:selectSubject('${lst.subjectName}', ${lst.subjectId})">${lst.subjectName}</a></li>
                </c:forEach>
        </ul>
        <div style="display: none" id='question-num-select' class="selected-subject">
            <h1 class='big-title' style='color: rgb(5, 221, 255);'>Môn học: <span id='selected-subject'>POA</span></h1>
            <p><h1 style="text-align: center">Số lượng câu hỏi: &nbsp;&nbsp;
                <input id='txtNumbQuestion' type="text" class='textbox inline'/></h1></p>
            <br/>
            <br/>
            <p class='center'><a class='button' href='javascript:submitForm();'>Làm bài</a></p>
        </div>
    </div>
</div>
<form action="ExamServlet" method="post" id='choose-subject-form' style="display: none">
    <input type="hidden" id='txtSubject' name="subject"/>
    <input type='hidden' id='txtNumberQuestion' name='numberQuestion'/>
    <input type="text" hidden="true" name="action" value="start"/>
</form>
<script>
    function selectSubject(subject, id) {
        $('#selected-subject').text(subject);
        $('#txtSubject').val(id);
        $('#subject-list').slideUp(function() {
            $('#question-num-select').fadeIn();
        });
        $('#subject-search').fadeOut();
    }
    function submitForm() {
        $('#txtNumberQuestion').val($('#txtNumbQuestion').val());
        $('#choose-subject-form').submit();
    }
</script>

<%@include file="../include/footer.jsp" %>