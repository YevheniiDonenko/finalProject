<%@ page import="models.UserData" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 10.06.2021
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="users_jsp.title"/></title>
    <link rel="stylesheet" href="css/table.css">
</head>
<body>
<%
    List<UserData> list = UserDao.getInstance().getAllUsers();
    request.setAttribute("users", list);
%>

<jsp:include page="header.jsp"/>

<table border="1">
    <caption>
        <fmt:message key="users_jsp.table.caption"/>
    </caption>
    <tr>
        <th><fmt:message key="users_jsp.table.name"/></th>
        <th><fmt:message key="users_jsp.table.login"/></th>
        <th><fmt:message key="users_jsp.table.email"/></th>
        <th><fmt:message key="users_jsp.table.role"/></th>
        <th><fmt:message key="users_jsp.table.delete"/></th>
        <th><fmt:message key="users_jsp.table.select_role"/></th>
        <th><fmt:message key="users_jsp.table.confirm"/></th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                    ${user.name}
            </td>
            <td>
                    ${user.login}
            </td>
            <td>
                    ${user.email}
            </td>
            <td>
                    ${user.role}
            </td>
            <form method="post" action="DeleteUserServlet">
                <input name="id" type="hidden" value="${user.id}">
                <td>
                    <input type="submit" value="<fmt:message key="users_jsp.table.delete"/>">
                </td>
            </form>
            <form method="post" action="UpdateUserServlet">
                <input name="id" type="hidden" value="${user.id}">
                <td>
                    <select name="role" required>
                        <option hidden disabled selected><fmt:message key="users_jsp.table.select_role"/></option>
                        <option>user</option>
                        <option>admin</option>
                    </select>
                </td>
                <td><input type="submit" value="<fmt:message key="users_jsp.table.confirm"/>"></td>
            </form>

        </tr>
    </c:forEach>


</table>
</body>
</html>
