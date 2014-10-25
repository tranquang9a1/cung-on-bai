<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : questionAdminView
    Created on : Oct 19, 2014, 11:34:10 AM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
             font-size: 16px;"> Last access : 30 May 2014 &nbsp; <a href="#" class="btn btn-danger square-btn-adjust">Logout</a> </div>
    </nav>   
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="adminResource/img/find_user.png" class="user-image img-responsive"/>
                </li>
                <li  >
                    <a class="active-menu"  href="blank.html"><i class="fa fa-square-o fa-3x"></i>Question Management</a>
                </li>	
                <li  >
                    <a href="SubjectServlet"><i class="fa fa-square-o fa-3x"></i>Subject Management</a>
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
            <%--<c:forEach var="question" items="${requestScope.questions}" varStatus="index">
                ${index.count}-${question.content}--> <a href="QuestionServlet?action=showDetail&id=${question.questionId}">show detail</a>
                --<a href="QuestionServlet?action=deleteQuestion&id=${question.questionId}&subjectId=${param.subjectId}">Delete Question</a> </br>
            </c:forEach>
            <a href="insertQuestion.jsp">insert Question</a>  --%>  
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Advanced Tables
                        </div>
                        <div class="panel-body">
                            <ul class="pagination">
                                <span class="question-pages"></span>
                            </ul>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Content</th>
                                            <th>Detail</th>
                                            <th>Delete</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="question" items="${requestScope.questions}">
                                            <tr class="odd gradeX">
                                                <td>${question.content}</td>
                                                <td><a class="btn btn-info"  href="QuestionServlet?action=showDetail&id=${question.questionId}">show detail</a></td>
                                                <td><a class="btn btn-danger" href="QuestionServlet?action=deleteQuestion&id=${question.questionId}&subjectId=${param.subjectId}">Delete Question</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div id="dataTables-example_paginate" class="dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination">
                                                <span class="question-pages"></span>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!--End Advanced Tables -->
                    </div>
                </div>  
                <!-- /. PAGE INNER  -->
            </div>
            <!-- /. PAGE WRAPPER  -->
        </div>
    </div>
    <script>
        $(function() {
            for (var i = 1; i <= ${number}; i++) {
                $('.question-pages').append('<a class="page-link-' + i + ' btn btn-default" href="QuestionServlet?action=show&type=viewQuestion&page=' + i + '&subjectId=${param.subjectId}">' + i + '</a>')
            }
            $('.page-link-${param.page}').removeClass('btn-default').addClass('btn-success');
        });
    </script>
    <%@include file="../adminInclude/footer.jsp" %>
