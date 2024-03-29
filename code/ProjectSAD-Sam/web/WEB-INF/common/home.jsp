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
    <h2 id ="subject-title" class="big-title">Chọn môn học</h2>
    <div class="searchbox">
        <input id='subject-search' class="textbox" placeholder="Tìm kiếm"/>
    </div>
    <div id="scroll-div">
        <ul id='subject-list' class="menu bg">
            <c:forEach var="lst" items="${lstSubject}" varStatus="index">
                <li class="subject-item" subject-name="${lst.subjectName}" value="${lst.subjectId}">
                    <a href="javascript:selectSubject('${lst.subjectName}', '${lst.subjectId}', ${lstNumberOfSubject[index.count-1]})">
                        ${lst.subjectName} (${lstNumberOfSubject[index.count-1]})
                    </a>
                </li>
            </c:forEach>
        </ul>
        </br>
        <div style="display: none" id='question-num-select' class="selected-subject">
            <h1 class='big-title' style='color: rgb(5, 221, 255);'>Môn học: <span id='selected-subject'>POA</span></h1>
            <p><h1 style="text-align: center">Số lượng câu hỏi: &nbsp;&nbsp;
                <input id='txtNumbQuestion' type="text" placeholder="1-60" class='textbox inline'/></h1></p>
            <br/>
            <p class='center'><a class='button' href='javascript:submitForm();'>Làm bài</a></p>
        </div>
        <br/>
        <br/>
        <br/>
         <div style="display: none" id="message1">
            <p style="color: red; padding: 8px; 
               background: #fff; margin: 10px; border-radius: 5px;">
                Vui lòng nhập lại số câu hỏi trong khoảng từ 1 đến 60</p>
        </div>
    </div>
    <div style="display: none" id="message">
            <p style="color: red; padding: 8px; 
               background: #fff; margin: 10px; border-radius: 5px;">
                Chưa có dữ liệu cho môn học này, vui lòng chọn môn học khác</p></div>
</div>
<form action="ExamServlet" method="post" id='choose-subject-form' style="display: none">
    <input type="hidden" id='txtSubject' name="subject"/>
    <input type='hidden' id='txtNumberQuestion' name='numberQuestion'/>
    <input type="text" hidden="true" name="action" value="start"/>
</form>
<script>
    function selectSubject(subject, id, num) {
        if (num > 0) {
            $('#selected-subject').text(subject);
            $('#txtSubject').val(id);
            $('#subject-list').slideUp(function() {
                $('#question-num-select').fadeIn();
            });
            $('#subject-title').fadeOut();
            $('#message').fadeOut();
            $('#subject-search').fadeOut();
        } else {
            $('#message').fadeIn();
        }
    }
    function submitForm() {
        var num = $('#txtNumbQuestion').val();
        if (!isNaN(num) && num > 0 && num <= 60) {
            $('#txtNumberQuestion').val($('#txtNumbQuestion').val());
            $('#choose-subject-form').submit();
        } else {
            $('#message1').fadeIn();
            $('#txtNumbQuestion').val("").focus();
        }
    }
    $(function () {
        $('#subject-search').keyup(function () {
            var keyword = $('#subject-search').val();
            if (keyword === "") {
                console.log('in all');
                $('.subject-item').slideDown();
            } else {
                console.log('show: ', $('.subject-item[subject-name~="'+$('#subject-search').val()+'"]'));
                $('.subject-item').each(function () {
                    if ($(this).attr('subject-name').toUpperCase()
                            .indexOf(keyword.toUpperCase()) > -1) {
                        $(this).slideDown();
                    } else {
                        $(this).slideUp();
                    }
                });
            }
        });
    });
</script>
<%@include file="../include/footer.jsp" %>
