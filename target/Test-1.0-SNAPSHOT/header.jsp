<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="user">
                <%--                <div class="user__avatar">--%>
                <%--                    <img src="https://cdn.dribbble.com/users/24078/screenshots/14804530/media/d7f135171e8baf492eb67e7175ecda31.jpg?compress=1&resize=1600x1200" alt="">--%>
                <%--                </div>--%>
                <div class="user__content">
                    <div class="user__name"><fmt:message key="header_jsp.caption1"/><br><fmt:message key="header_jsp.caption2"/></div>
                    <div class="user__prof"><fmt:message key="header_jsp.caption3"/></div>
                </div>
            </div>

            <nav class="nav">
                <a class="nav__link" href="index.jsp"><fmt:message key='header_jsp.link.main'/></a>
                <a class="nav__link" href="${pageContext.request.contextPath}/exhibitionservlet"><fmt:message key='header_jsp.link.exhibitions'/></a>
                <c:if test="${role == 'admin'}">
                    <a class="nav__link" href="halls.jsp"><fmt:message key='header_jsp.link.halls'/></a>
                    <a class="nav__link" href="users.jsp"><fmt:message key='header_jsp.link.users'/></a>
                </c:if>

                <c:choose>
                    <c:when test="${role != null}">
                        <a class="nav__link" href="profile.jsp"><fmt:message key='header_jsp.link.profile'/></a>
                        <a class="nav__link transition" href="logout"><fmt:message key='header_jsp.link.logout'/></a>
                    </c:when>
                    <c:when test="${role == null}">
                        <a class="nav__link" href="login.jsp"><fmt:message key='header_jsp.link.login'/></a>
                        <a class="nav__link nav__link--btn" href="regPage.jsp"><fmt:message key='header_jsp.link.register'/></a>
                    </c:when>
                </c:choose>
<%--                <form action="changeLocale.jsp" method="post">--%>
<%--                    <select name="locale">--%>
<%--                        <c:forEach items="${applicationScope.locales}" var="locale">--%>
<%--                            <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>--%>
<%--                            <option value="${locale.key}" ${selected}>${locale.value}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                    <c:forEach items="${applicationScope.locales}" var="locale">--%>
<%--                    </c:forEach>--%>
<%--                    <input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">--%>
                    <a class="nav__link" href="ru.jsp">RU</a>
                    <a class="nav__link" href="en.jsp">EN</a>
<%--                </form>--%>
            </nav>
        </div>
    </div>
</header>

