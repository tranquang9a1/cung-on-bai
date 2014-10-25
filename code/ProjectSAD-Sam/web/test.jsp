<%-- 
    Document   : test
    Created on : Oct 25, 2014, 12:21:49 PM
    Author     : computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<%@include file="WEB-INF/include/header.jsp" %>

<html>
    <head>
        <style>
            #amPmCheckbox input[type="checkbox"] {
                display: none;
            }
            #amPmCheckbox input[type="checkbox"] + label {  
                font-size: 20px;
                background: url('img/unfavorite.png') no-repeat;
                background-size: 20px,20px;
                padding-left: 100px;
            }
            #amPmCheckbox input[type="checkbox"]:checked + label {
                background: url('img/favorite.png')  no-repeat;
                background-size: 20px,20px;
                padding-left: 100px;
            }
        </style>
    </head>
    <body>
        <div id="amPmCheckbox">
            <input type="checkbox" id="am_2" value="1" />
            <label for="am_2">AM</label>ddgdgd

        </div>
        <script>
        </script>
    </body>
</html>

