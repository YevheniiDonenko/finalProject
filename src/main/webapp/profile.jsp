<%@ page import="dao.UserDao" %>
<%@ page import="models.UserData" %>
<%@ page import="dao.OrderDao" %>
<%@ page import="models.OrderData" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 06.06.2021
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="profile_jsp.title"/></title>
    <style>
        .profile__info {
            font-size: 20px;
        }

        .profile__container {
            text-align: center;
            margin-top: 50px;
            margin-bottom: 50px;
        }
    </style>
    <link rel="stylesheet" href="css/table.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<%
    List<OrderData> orders = OrderDao.getInstance().getOrderByUserId((Integer) session.getAttribute("user_id"));
    int sum = 0;
    for (OrderData o : orders) {
        sum += o.getSumForExhibition();
    }
    request.setAttribute("orders", orders);
    request.setAttribute("sum", sum);
%>
<div class="profile__container">
    <div class="profile__info">
        <fmt:message key="profile_jsp.name"/>: ${user.name}<br>
        <fmt:message key="profile_jsp.login"/>: ${user.login}<br>
        <fmt:message key="profile_jsp.email"/>: ${user.email}<br>
    </div>
</div>

<table border="1">
    <caption><fmt:message key="profile_jsp.table.caption"/>:</caption>
    <th><fmt:message key="profile_jsp.table.name"/></th>
    <th><fmt:message key="profile_jsp.table.quantity"/></th>
    <th><fmt:message key="profile_jsp.table.sumForOrder"/></th>
    <c:forEach var="order" items="${orders}">
    <tr>

        <td>
                ${order.exhibitionName}
        </td>
        <td>
                ${order.quantity}
        </td>
        <td>
                ${order.sumForExhibition}
        </td>

        </c:forEach>
    </tr>
    <tr>
        <td colspan="2"><fmt:message key="profile_jsp.table.sum"/></td>
        <td>${sum}</td>
    </tr>
</table>

</body>
</html>