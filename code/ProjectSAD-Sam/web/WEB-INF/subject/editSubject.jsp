<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : questionAdminView
    Created on : Oct 19, 2014, 11:34:10 AM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../adminInclude/header.jsp" %>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">ADMIN</a> 
        </div>
        <div style="color: white;
             padding: 15px 50px 5px 50px;
             float: right;
             font-size: 16px;"><a href="user" class="btn btn-danger square-btn-adjust">Back to home</a> </div>
    </nav>   
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="adminResource/img/find_user.png" class="user-image img-responsive"/>
                </li>
                <li  >
                    <a   href="QuestionServlet?action=show&type=chooseSubject"><i class="fa fa-square-o fa-3x"></i>Question Management</a>
                </li>	
                <li  >
                    <a class="active-menu" href="SubjectServlet"><i class="fa fa-square-o fa-3x"></i>Subject Management</a>
                </li>
            </ul>

        </div>

    </nav>  
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Question Management</h2>   
                    <h5>Welcome Jhon Deo , Love to see you back. </h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <form method="post">
                <div class="form-group input-group">
                    <input type="hidden" name ="id" value="${subjectId}"/>
                    <input type="text" name ="name" value="${subjectName}" class="form-control" style="height: 34px;"/>
                    <span class="input-group-lg">
                        <input type="submit" name ="action" value="edit" class="btn btn-primary"/>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>  
<!-- /. PAGE INNER  -->
<!--</div>
 /. PAGE WRAPPER  
</div>
</div>-->

<%@include file="../adminInclude/footer.jsp" %>
