<%@ page import="models.HallData" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.HallDao" %><%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 10.06.2021
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="halls_jsp.title"/></title>
    <link rel="stylesheet" href="css/table.css">
</head>
<body>
<%
    List<HallData> list = HallDao.getInstance().getAllHalls();
    request.setAttribute("halls", list);
%>

<jsp:include page="header.jsp"/>

<table border="1">
    <caption>
        <fmt:message key="halls_jsp.table.caption"/>
    </caption>
    <tr>
        <th><fmt:message key="halls_jsp.table.name"/></th>
        <th><fmt:message key="halls_jsp.table.delete"/></th>
        <th><fmt:message key="halls_jsp.table.update"/></th>
            <form action="NewHallServlet" method="post">
                <th><input name="name" placeholder="<fmt:message key="halls_jsp.table.new_hall_name"/>"></th>
                <th><input type="submit" value="<fmt:message key="halls_jsp.table.submit.new_hall"/>"></th>
            </form>
    </tr>

    <c:forEach var="hall" items="${halls}">
        <tr>
            <td>
                    ${hall.name}
            </td>
            <form method="post" action="DeleteHallServlet">
                <input name="id" type="hidden" value="${hall.id}">
                <td>
                    <input type="submit" value="<fmt:message key="halls_jsp.table.delete"/>">
                </td>
            </form>
            <form method="post" action="UpdateHallServlet">
                <input name="id" type="hidden" value="${hall.id}">
                <td>
                    <input type="submit" value="<fmt:message key="halls_jsp.table.update"/>">
                </td>
                <td><input name="name" placeholder="<fmt:message key="halls_jsp.table.new_name_for_hall"/>"></td>
            </form>

        </tr>
    </c:forEach>


</table>

</body>
</html>
