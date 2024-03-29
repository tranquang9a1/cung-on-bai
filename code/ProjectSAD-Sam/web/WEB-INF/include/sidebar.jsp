<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : sidebar
    Created on : Oct 21, 2014, 5:10:04 PM
    Author     : dinhquangtrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="leftmenu">
    <h1 id="profile">Xin chào, ${user.username}!<br/>Bạn có <b>${user.score} điểm</b></h1>
    <ul class="menu">
        <li>
            <a href="home">Ôn bài</a>
        </li>
        <li>
            <a href="review">Xem lại bài</a>
        </li>
        <li>
            <a href="FavoriteServlet">Đánh dấu</a>
        </li>
        <li>
            <a href="stat">Thống kê</a>
        </li>
        <c:if test="${user.isAdmin == 1}">
        <li>
            <a style="background-color: rgba(255, 128, 128, 0.35);" href="QuestionServlet?action=show&type=chooseSubject">Quản lý</a>
        </li>
        </c:if>
    </ul>
    <script>
    $(function () {
        var activePage = location.href.split('/').splice(-1)[0].split('?')[0];
        $('a[href=' + activePage + ']').parent().addClass('selected');
    });  
    </script>
    <div class="menu-left-content">
        <a href="user?action=logout">Đăng xuất</a>
    </div>
</div>