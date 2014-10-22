<%-- 
    Document   : viewPoint
    Created on : Oct 21, 2014, 9:20:11 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xem điểm</title>
    </head>
    <body>
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
    </body>
</html>
