<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<html>
<head>
    <title>Devcom</title>
</head>
<body>
<div class="container">
    <main>
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
        <div id="login">
        <form action="/devcom/login.do" method="post">
            <h2>로그인</h2>
            <div class="row-sm">
                <input type="text" name="id" class="form-control middle" placeholder="아이디를 입력해주세요.">
            </div>
            <div class="row-sm">
                <input type="password" name="pw" class="form-control middle" placeholder="비밀번호를 입력해주세요.">
            </div>
            <div class="row-sm">
                <input type="submit" value="로그인" class="btn btn-primary btn-block">
            </div>
        </form>
        </div>
    </main>
</div>
</body>
</html>
