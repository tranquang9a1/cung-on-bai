<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : examsession
    Created on : Oct 19, 2014, 3:14:49 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<%@include file="../include/sidebar.jsp" %>
    <div class="center-content">
        <h1 class="big-title">Các câu hỏi đã đánh dấu</h1>
                <form id="delete-bookmark" action="FavoriteServlet" method="POST">
        <div id="scroll-div" class="wide" style="height: 400px">
            <div class="exam-session">
                    <input type="text" name="action" value="delete" hidden="true"/>  
                     <c:forEach var="favorite" items="${lstFavorite}" varStatus="index">
                         <div class="amPmCheckbox">
                            <input type="checkbox" name="favorite" id="chk${favorite.id}" value="${favorite.id}"/>
                            <lable for="chk${favorite.id}"></lable>${favorite.questionId.content}
                         </div>
                    <c:forEach var="answer"
                               items="${favorite.questionId.tblAnswerList}" varStatus="indexAnswer">
                        <b style="color: blue; display: inline-block; width: 30px;"></b> ${answer.content} </br>
                    </c:forEach>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                </c:forEach>
            </div>
        </div>
                    <a href="#" class="button small" onclick="$('#delete-bookmark').submit()">Bỏ đánh dấu các câu đã chọn</a>
                </form>
        <p style="text-align: center; margin-top: 40px;">
            <a class='button' href="home">Quay lại</a>
        </p>
    </div>
    <%@include file="../include/footer.jsp" %>