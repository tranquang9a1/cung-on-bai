<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : footer
    Created on : Oct 21, 2014, 10:08:20 AM
    Author     : dinhquangtrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${user != null}">
    <%@include file="chatbox.jsp" %>
</c:if>
</body>
</html>
