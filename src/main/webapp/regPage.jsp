<%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 05.06.2021
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="regPage_jsp.title"/></title>
    <style>

        /* Add padding to containers */
        .container__reg {
            margin: auto;
            width: 50%;
            height: 70%;
            padding: 16px;
        }

        /* Full-width input fields */
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
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: #000103;
        }
        .error {
            color: red;
        }

    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<form method="post" action="RegistrationServlet">
    <div class="container__reg">
        <h1><fmt:message key="regPage_jsp.caption"/></h1>
        <p><fmt:message key="regPage_jsp.message"/>.</p>
        <hr>
        <c:if test="${used_login == true}">
            <p class="error"><fmt:message key="regPage_jsp.error.used_login"/></p>
        </c:if>
        <label for="login"><b><fmt:message key="regPage_jsp.form.login"/></b></label>
        <input name="login" placeholder="<fmt:message key="regPage_jsp.input.login"/>" required> <br>
        <label for="psw"><b><fmt:message key="regPage_jsp.form.password"/></b></label>
        <input name="password" type="password" placeholder="<fmt:message key="regPage_jsp.input.password"/>" required> <br>
        <label for="name"><b><fmt:message key="regPage_jsp.form.name"/></b></label>
        <input name="name" placeholder="<fmt:message key="regPage_jsp.input.name"/>" required> <br>
        <c:if test="${used_email == true}">
            <p class="error"><fmt:message key="regPage_jsp.error.used_email"/></p>
        </c:if>
        <label for="email"><b><fmt:message key="regPage_jsp.form.email"/></b></label>
        <input name="email" type="email" placeholder="<fmt:message key="regPage_jsp.input.email"/>" required> <br>
        <hr>
        <%--    <input type="submit" value="Register"> <br>--%>


        <p><fmt:message key="regPage_jsp.privacy"/> <a href="terms.jsp" target="_blank"><fmt:message key="regPage_jsp.link.privacy"/></a>.</p>
        <button type="submit" class="registerbtn"><fmt:message key="regPage_jsp.submit"/></button>

    </div>
</form>

</body>
</html>
