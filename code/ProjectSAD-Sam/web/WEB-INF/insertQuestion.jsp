<%-- 
    Document   : insertQuestion
    Created on : Oct 19, 2014, 2:19:20 PM
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
        Insert Question to Subject : ${requestScope.subject.subjectName}
        <form method="POST" action="QuestionServlet">
            <input type="hidden" name="subjectId" value="${requestScope.subject.subjectId}" />
            <input type="hidden" name="type" value="persit" />
            
            Content : <input type="text" name="questionContent" value="" /></br>
            <div id="answers">
            Answer1 : <input type="text" name="answerContent" value="" /></br>
            Point1  : <input type="text" name="point" value="" /></br>
            Answer2 : <input type="text" name="answerContent" value="" /></br>
            Point2  : <input type="text" name="point" value="" /></br>
            Answer3 : <input type="text" name="answerContent" value="" /></br>
            Point3  : <input type="text" name="point" value="" /></br>
            Answer4 : <input type="text" name="answerContent" value="" /></br>
            Point4  : <input type="text" name="point" value="" /></br>
            </div>
            <input onclick="addAnswer()" type="button"/>
            
            
            <input type="submit" value="insertQuestion" name="action" />
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
