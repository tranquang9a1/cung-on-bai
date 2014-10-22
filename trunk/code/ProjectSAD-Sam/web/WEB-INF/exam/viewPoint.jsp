<%-- 
    Document   : viewPoint
    Created on : Oct 21, 2014, 9:20:11 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<%@include file="../include/sidebar.jsp" %>
<div class="center-content">
    <div class="exam-session" style="width: 400px; margin: 0 auto;">
        <h1>Bạn đã làm xong bài</h1>
        <table>
            <tr>
                <td>
                    Số câu hỏi mà bạn đã làm là :
                </td>
                <td>
                    ${numberQuestion}
                </td>
            </tr>
            <tr>
                <td>
                    Số điểm bạn đạt được là:
                </td>
                <td>
                    ${point}
                </td>
            </tr>
        </table>
    </div>
</div>
<%@include file="../include/footer.jsp" %>