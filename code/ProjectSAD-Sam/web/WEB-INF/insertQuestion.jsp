<%-- 
    Document   : insertQuestion
    Created on : Oct 19, 2014, 2:19:20 PM
    Author     : khangtnse60992
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="adminInclude/header.jsp" %>
<div class="box-center">
    <h1>Insert Question</h1>
    Insert Question to Subject : ${param.subjectName}
    <form method="POST" action="QuestionServlet">
        <input type="hidden" name="subjectId" value="${param.subjectId}" />
        <input type="hidden" name="type" value="persit" />

        <div class="input-group">
            Content : <textarea class="form-control custom-control" rows="4" style="resize:none" name="questionContent"></textarea>     
        </div></br> 
        <div id="answers">
            Answer : <input type="text" name="answerContent" value="" /><select name="point" class="form-control"><option value="false">False</option><option value="true">True</option></select></br>
            Answer : <input type="text" name="answerContent" value="" /><select name="point" class="form-control"><option value="false">False</option><option value="true">True</option></select></br>
            Answer : <input type="text" name="answerContent" value="" /><select name="point" class="form-control"><option value="false">False</option><option value="true">True</option></select></br>
            Answer : <input type="text" name="answerContent" value="" /><select name="point" class="form-control"><option value="false">False</option><option value="true">True</option></select></br>
        </div>
        <script>
            $("select").selectBoxIt({
                // Sets default text to appear for the drop down
                nativeMousedown: true

            });
        </script>
        <input onclick="addAnswer()" type="button" value="add answer" class="btn btn-small"/>
        <input type="submit" value="insertQuestion" name="action" class="btn btn-primary"/>
    </form>
</div>
<script>
            function addAnswer() {
                var newAnswer = 'Answer : <input type="text" name="answerContent" value="" /><select name="point" class="form-control"><option value="false">False</option><option value="true">True</option></select></br>';
                var node = document.createElement('div');
                node.innerHTML = newAnswer;
                document.getElementById("answers").appendChild(node);
                $("select").selectBoxIt({
                    // Sets default text to appear for the drop down
                    nativeMousedown: true

                });
            }

</script>
<%@include file="adminInclude/footer.jsp" %>