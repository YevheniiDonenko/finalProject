<%--
  Created by IntelliJ IDEA.
  User: shooa
  Date: 22.05.2021
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="login_jsp.title"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,700;1,300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .error__message {
            color: #e50b0b;
            text-align: center;
        }
        .login__message{
            color: greenyellow;
            text-align: center;
        }
        .container__login {

            margin-left: auto;
            margin-right: auto;
            margin-top: 50px;
            width: 400px;
            height: 400px;
            padding:50px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        .login__form {
            padding-top: 20px;
            height: 300px;
            margin:auto;
            background-color: white;
        }

        .submit {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }
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
    </style>
</head>
<body>
<div class="container__login">
    <div class="login__form">
        <h1><fmt:message key="login_jsp.caption"/></h1>
        <p><fmt:message key="login_jsp.message"/> </p>
        <hr>
        <form action="login" method="post">
            <c:if test="${errorLogin == true}">
            <p class="error__message"><fmt:message key="login_jsp.error"/></p>
            </c:if>
            <input class="login" name="login" placeholder="<fmt:message key="login_jsp.input.login"/>"> <br>
            <input class="password" name="password" type="password" placeholder="<fmt:message key="login_jsp.input.password"/>"><br>
                <hr>
            <input class="submit" type="submit" value="<fmt:message key="login_jsp.submit"/>">

    </div>
</div>
</form>
</body>
</html>
