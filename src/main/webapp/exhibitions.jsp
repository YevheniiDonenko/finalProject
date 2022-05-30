<%@ page import="models.ExhibitionData" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ExhibitionDao" %><%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 05.06.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="exhibitions_jsp.title"/></title>
    <link rel="stylesheet" href="css/table.css">
    <style>
        input {
            width: 50px;
        }
        input[type=submit] {
            width: 100px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<table border="1">
    <caption>
        <fmt:message key="exhibitions_jsp.table.caption"/>
    </caption>
    <tr>
        <th><a href="${pageContext.request.contextPath}/exhibitionservlet?order=name"><fmt:message
                key="exhibitions_jsp.table.name"/></a></th>
        <th><fmt:message key="exhibitions_jsp.table.theme"/></th>
        <th><fmt:message key="exhibitions_jsp.table.start_time"/></th>
        <th><fmt:message key="exhibitions_jsp.table.finish_time"/></th>
        <th><fmt:message key="exhibitions_jsp.table.hall"/></th>
        <th>
            <a href="exhibitionservlet?order=price"><fmt:message key="exhibitions_jsp.table.price"/></a>
            <form method="get" action="exhibitionservlet">
                <input type="hidden" name="order" value="pricefilter">
                <input type="number" name="start" placeholder="<fmt:message key="exhibitions_jsp.table.filter.from"/>">
                <input type="number" name="finish" placeholder="<fmt:message key="exhibitions_jsp.table.filter.to"/>"><br>
                <input type="submit" value="<fmt:message key="exhibitions_jsp.table.filter.submit"/>">
            </form>
        </th>
        <c:if test="${role == 'admin'}">
            <th><fmt:message key="exhibitions_jsp.table.tickets_sold"/></th>
            <th><a class="create__exhibition" href="createExhibition.jsp">
                <fmt:message key="exhibitions_jsp.table.new_exhibition"/></a></th>
        </c:if>
    </tr>

    <c:forEach var="exhibition" items="${exhibitions}">
        <tr>
            <td>
                    ${exhibition.name}
            </td>
            <td>
                    ${exhibition.theme}
            </td>
            <td>
                    ${exhibition.start}
            </td>
            <td>
                    ${exhibition.finish}
            </td>
            <td>
                    ${exhibition.hall_name}
            </td>
            <td>
                    ${exhibition.price}
            </td>
            <c:if test="${role == 'admin'}">
                <td>${exhibition.quantity}</td>
                <form method="post" action="DeleteExhibitionServlet">
                    <input name="id" type="hidden" value="${exhibition.id}">
                    <td>
                        <input type="submit" value="<fmt:message key="exhibitions_jsp.table.form.delete"/>">
                    </td>
                </form>
                <form method="post" action="updateExhibition.jsp">
                    <input name="id" type="hidden" value="${exhibition.id}">
                    <td>
                        <input type="submit" value="<fmt:message key="exhibitions_jsp.table.form.update"/>">
                    </td>
                </form>
            </c:if>
            <c:if test="${role == 'user'}">

                <form method="post" action="ticketBuy">
                    <input name="id" type="hidden" value="${exhibition.id}">
                    <td>
                        <input type="number" name="quantity" value="1">
                    </td>
                    <td>
                        <input type="submit" value="<fmt:message key="exhibitions_jsp.table.buy"/>">
                    </td>
                </form>

            </c:if>
        </tr>
        <tr>
            <td class="description" colspan="6">
                    ${exhibition.description}
            </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
