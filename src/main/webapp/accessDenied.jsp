<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 05.06.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="accessDenied_jsp.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 style="text-align: center"> <fmt:message key="accessDenied_jsp.message"/></h1>
</body>
</html>
