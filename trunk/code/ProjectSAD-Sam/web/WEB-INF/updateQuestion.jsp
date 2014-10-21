<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : updateQuestion
    Created on : Oct 20, 2014, 11:25:52 AM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="POST" action="QuestionServlet">
            <input type="hidden" name="type" value="mergeQuestion" />
            <input type="hidden" name="questionId" value="${requestScope.question.questionId}" />
            Content : <input type="text" name="questionContent" value="${requestScope.question.content}" /></br>
            <c:forEach var="answer" items="${requestScope.question.tblAnswerList}">
                <input type="hidden" name="answerId" value="${answer.answerId}" />
                <div id="answers">
                Answer${index.count} : <input type="text" name="answerContent" value="${answer.content}" /></br>
                Point${index.count}  : <input type="text" name="point" value="${answer.point}" /></br>
                </div>
            </c:forEach>
                <input onclick="addAnswer()" type="button"/>
            <input type="submit" value="updateQuestion" name="action" />
        </form>
            <script>
                var i = 5;
                function addAnswer() {
                    var newAnswer = 'Answer'+i+' : <input type="text" \n\
            name="answerContent" value="" /></br>Point'+i+' : \n\
<input type="text" name="point" value="" /></br>';
                    var node = document.createElement('div');
                    node.innerHTML = newAnswer;
                    document.getElementById("answers").appendChild(node);
                    i++;
                }
                
            </script>
    </body>
</html>
