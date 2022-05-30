<%@ page import="dao.ExhibitionDao" %>
<%@ page import="models.ExhibitionData" %>
<%@ page import="dao.HallDao" %>
<%@ page import="models.HallData" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 05.06.2021
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

    ExhibitionData exhibition = ExhibitionDao.getInstance().getExhibitionById(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("exhibition", exhibition);
%>
<html>
<head>
    <title>Update</title>
    <style>
        table {
            border-collapse: collapse;
            color: #686461;
            margin: auto;
            text-align: center;
        }
        caption {
            padding: 10px;
            color: white;
            background: #747373;
            font-size: 18px;
            text-align: left;
            font-weight: bold;
        }
        th {
            border-bottom: 3px solid #717070;
            padding: 10px;
            text-align: left;
        }
        td {
            padding: 10px;
        }
        tr:nth-child(odd) {
            background: white;
        }
        tr:nth-child(even) {
            background: #c7c7c7;
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
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Theme</th>
            <th>Start time</th>
            <th>Finish time</th>
            <th>Hall</th>
            <th>Price</th>
        </tr>

        <tr>
            <td>${exhibition.name}</td>
            <td>${exhibition.theme}</td>
            <td>${exhibition.start}</td>
            <td>${exhibition.finish}</td>
            <td>${exhibition.hall_name}</td>
            <td>${exhibition.price}</td>
        </tr>
        <tr>
        <form action="UpdateExhibitionServlet" method="post">
            <input name="id" value="${exhibition.id}" type="hidden">
            <td><input name="name" value="${exhibition.name}"></td>
            <td><input name="theme" value="${exhibition.theme}"></td>
            <td><input name="description" value="${exhibition.description}"></td>
            <td><input name="start" value="${exhibition.start}"></td>
            <td><input name="finish" value="${exhibition.finish}"></td>
            <td><select name="hall_id">
                <c:forEach var="hall" items="${halls}">
                    <option value="${hall.id}">${hall.name}</option>
                </c:forEach>
            </select></td>
            <td><input name="price" type="number" value="${exhibition.price}"></td>
            <td><input type="submit" value="Confirm changes"></td>
        </form>
        </tr>

    </table>


</body>
</html>
