<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav>
        <img src="/devcom/asset/logo/logo.png" width="150px">
        <ul>
            <li><a href="/devcom/index.do">HOME</a></li>
            <li><a href="/devcom/qna.do">Q&A</a></li>
            <li><a href="/devcom/study.do">STUDY</a></li>
            <li><a href="/devcom/community.do">COMMUNITY</a></li>
            <c:if test="${empty auth}">
            <li><a href="/devcom/login.do">LOGIN</a></li>
            <li><a href="/devcom/register.do">REGISTER</a></li>
            </c:if>
            <c:if test="${not empty auth}">
            <li><a href="/devcom/logout.do">LOGOUT</a></li>
            <li><a href="/devcom/logout.do">MY PAGE</a></li>
            </c:if>
        </ul>
    </nav>
</header>