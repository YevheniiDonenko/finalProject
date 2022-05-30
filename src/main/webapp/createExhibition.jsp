<%@ page import="dao.HallDao" %>
<%@ page import="java.util.List" %>
<%@ page import="models.HallData" %><%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 05.06.2021
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="createExhibition_jsp.title"/></title>
    <style>

        input {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit/register button */
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .rbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }
        .container {
            width: 500px;
        }
    </style>
</head>
<body>
<%
    HallDao hallDao = HallDao.getInstance();
    List<HallData> list = hallDao.getAllHalls();
    request.setAttribute("halls", list);;
%>
<jsp:include page="header.jsp"/>
<div class="container">
<form action="new-exhibition" method="post">
    <fmt:message key="exhibitions_jsp.table.name"/>: <input name="name" required>
    <fmt:message key="exhibitions_jsp.table.theme"/>: <input name="theme" required>
    <fmt:message key="exhibitions_jsp.create.description"/>: <input name="description" required>
    <fmt:message key="exhibitions_jsp.table.start_time"/>:<input name="start" type="datetime-local" required>
    <fmt:message key="exhibitions_jsp.table.finish_time"/>:<input name="finish" type="datetime-local" required>
    <fmt:message key="exhibitions_jsp.table.price"/>: <input name="price" type="number" required>
    <fmt:message key="exhibitions_jsp.create.select_hall"/>: <select name="hall_id">
        <c:forEach var="hall" items="${halls}">
            <option value="${hall.id}">${hall.name}</option>
        </c:forEach>
    </select>
    <input class="btn" type="submit" value="<fmt:message key="exhibitions_jsp.create.submit"/>">
</form>
</div>
</body>
</html>
