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
        <form method="POST" action="QuestionServlet">
            <input type="hidden" name="subjectId" value="1" />
            Content : <input type="text" name="questionContent" value="" /></br>
            Answer1 : <input type="text" name="answerContent" value="" /></br>
            Point1  : <input type="text" name="point" value="" /></br>
            Answer2 : <input type="text" name="answerContent" value="" /></br>
            Point2  : <input type="text" name="point" value="" /></br>
            Answer3 : <input type="text" name="answerContent" value="" /></br>
            Point3  : <input type="text" name="point" value="" /></br>
            Answer4 : <input type="text" name="answerContent" value="" /></br>
            Point4  : <input type="text" name="point" value="" /></br>
            <input type="submit" value="insertQuestion" name="action" />
        </form>
    </body>
</html>
