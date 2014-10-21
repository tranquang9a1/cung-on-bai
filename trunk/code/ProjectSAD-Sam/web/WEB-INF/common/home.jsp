<%-- 
    Document   : home
    Created on : Oct 19, 2014, 9:25:35 PM
    Author     : dinhquangtrung
--%>

<%@page import="utils.Constants"%>
<%@page import="entity.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<style>
    ::-webkit-input-placeholder { /* WebKit browsers */
        color:    #ddd;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color:    #ddd;
        opacity:  1;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color:    #ddd;
        opacity:  1;
    }
    :-ms-input-placeholder { /* Internet Explorer 10+ */
        color:    #ddd;
    }
    .leftmenu {
        position: fixed;
        left: 0;
        top: 0;
        bottom: 0;
        color: white;
        border-right: 1px solid rgba(255,255,255,0.5);
        width: 25%;
        float: left;
        overflow: hidden;
        text-align: right;
    }
    .center-content {
        position: fixed;
        top: 0;
        bottom: 0;
        right: 0;
        width: 75%;
        color: white;
    }
    .big-title {
        font-size: 35px;
        line-height: 45px;
        color: #fff;
        margin: 25px 0 20px;
        text-align: center;
    }
    b {
        font-weight: bold;
    }
    a {
        color: white;
    }
    a:hover {
        color: white;
    }
    .menu a {
        font-size: 25px;
        display: block;
        padding: 20px 50px 20px 0;
    }
    .menu li {
        margin: 0;
        transition: background-color 1s;
        -webkit-transition: background-color 0.4s;
    }
    .menu li:hover, .menu li.selected {
        background-color: rgba(255,255,255,0.15);
    }
    .menu-left-content {
        margin: 50px;
    }
    #subject-list {
        padding: 0 100px;
        height: 400px;
        overflow-x: auto;
    }
    #subject-list ul li a {
        display: block;
        padding: 20px;
    }
    #profile {
        margin: 40px;
        padding: 10px;
        background-color: rgba(0,0,0,0.1);
        border-radius: 10px;
    }
    .bg {
        background-color: rgba(255,255,255,0.1);
    }
    .searchbox {
        padding: 0 100px;
    }
    .textbox {
        background-color: rgba(255,255,255,0.2);
        width: 100%;
        padding: 10px;
        color: white;
        border: 1px solid rgba(255,255,255,0.3);
        font-size: 25px;
    }
    .textbox:focus {
        outline: 0;
    }
</style>
<!--<div class="modal-dialog">
    <div class="modal-dialog-content">
        <h1>Xin chào, <%= ((TblUser) session.getAttribute(Constants.VAR_SESSION_USER)).getUsername()%>!</h1>
        <a href="user?action=logout">Logout</a>
    </div>
</div>-->

<div class="leftmenu">
    <h1 id="profile">Xin chào, ${user.username}!<br/>Bạn có <b>${user.score} điểm</b></h1>
    <ul class="menu">
        <li>
            <a href="#">Ôn bài</a>
        </li>
        <li>
            <a href="#">Xem lại bài</a>
        </li>
        <li>
            <a href="#">Đánh dấu</a>
        </li>
        <li>
            <a href="#">Thống kê</a>
        </li>
    </ul>

    <div class="menu-left-content">
        <a href="user?action=logout">Đăng xuất</a>
    </div>
</div>
<div class="center-content">
    <h2 class="big-title">Chọn môn học</h2>
    <div class="searchbox">
        <input class="textbox" placeholder="Tìm kiếm"/>
    </div>
    <div id="subject-list">
        <ul class="menu bg">
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
            <li><a href="#">Subject 1</a></li>
        </ul>
    </div>
</div>

<%@include file="../include/footer.jsp" %>