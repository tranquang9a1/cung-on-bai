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
            <a href="#">Đánh dấu</a>
        </li>
        <li>
            <a href="stat">Thống kê</a>
        </li>
    </ul>
    <script>
    $(function () {
        var activePage = location.href.split('/').splice(-1)[0];
        $('a[href=' + activePage + ']').parent().addClass('selected');
    });  
    </script>
    <div class="menu-left-content">
        <a href="user?action=logout">Đăng xuất</a>
    </div>
</div>