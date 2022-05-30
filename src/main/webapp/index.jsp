<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmp" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmp:message key="index_jsp.title"/></title>
    <style>
        h1, h3 {
            text-align: center;
            margin-top: 200px;
            padding-top: 200px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1> <fmp:message key="index_jsp.welcome_message1"/> <br>
    <fmp:message key="index_jsp.welcome_message2"/></h1>
<h3><fmp:message key="index_jsp.welcome_message3"/> </h3>

</body>
</html>