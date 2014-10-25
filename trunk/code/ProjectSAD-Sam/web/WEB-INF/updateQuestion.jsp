<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : questionAdminView
    Created on : Oct 19, 2014, 11:34:10 AM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminInclude/header.jsp" %>
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
                    <a class="active-menu"  href="QuestionServlet?action=show&type=chooseSubject"><i class="fa fa-square-o fa-3x"></i>Question Management</a>
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
            <form method="POST" action="QuestionServlet" role="form">
                <input type="hidden" name="type" value="mergeQuestion" />
                <input type="hidden" name="questionId" value="${requestScope.question.questionId}" />
                Content : <textarea class="form-control custom-control" rows="4" style="resize:none;width: 700px;" name="questionContent">${requestScope.question.content}</textarea>
                <div id="answers" >
                    <c:forEach var="answer" items="${requestScope.question.tblAnswerList}" varStatus="index">
                        <input type="hidden" name="answerId" value="${answer.answerId}" />
                        <div class="answer-item" id="delete-${index.count}">
                            <label>Answer : </label>
                            <div class="form-group input-group">
                                <input type="text" name="answerContent" value="${answer.content}" class="form-control" style="width: 500px;height: 34px"/>
                                <c:if test="${answer.point > 0}">
                                    <span class="input-group-lg">
                                        <select name="point"  style="width: 100px;height: 34px"><option value="false">False</option><option value="true" selected="true">True</option></select>
                                    </span>
                                </c:if>
                                <c:if test="${answer.point == 0}">
                                    <span class="input-group-lg">
                                        <select name="point"  style="width: 100px; height: 34px"><option value="false" selected="true">False</option><option value="true" >True</option></select>
                                    </span>
                                </c:if>
                                <span class="input-group-lg">
                                    <input onclick="deleteAnswer(${index.count})" type="button" value="delete answer" class="btn btn-danger"/>
                                </span>
                            </div>
                        </div>
                    </c:forEach>
                </div>  
                <input onclick="addAnswer($('.answer-item').length + 1)" type="button" value="insert answer" class="btn btn-success"/>
                <input type="submit" value="updateQuestion" name="action" class="btn btn-primary"/>
            </form>
            <script>

            function addAnswer(index) {
                var newAnswer = '<div class="answer-item" id="delete-' + index + '">' + '<label>Answer : </label> <div class="form-group input-group"> <input type="text" name="answerContent" value="" class="form-control" style="width: 500px;height: 34px"/><span class="input-group-lg"><select name="point"  style="width: 100px;height: 34px"><option value="false">False</option><option value="true" selected="true">True</option></select></span><span class="input-group-lg">' +
                        '<input onclick="deleteAnswer(' + index + ')" type="button" value="delete answer" class="btn btn-danger"/></span></div></div>';
                var node = document.createElement('div');
                node.innerHTML = newAnswer;
                document.getElementById("answers").appendChild(node);

            }

            </script>
            <script>

                function deleteAnswer(id) {
                    $('#delete-' + id).remove();
                }

            </script>
        </div>
    </div>
</div>  
<!-- /. PAGE INNER  -->
<!--</div>
 /. PAGE WRAPPER  
</div>
</div>-->

<%@include file="adminInclude/footer.jsp" %>
