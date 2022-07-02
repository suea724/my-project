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
        <div id="register">
            <form action="/devcom/register.do" method="post" enctype="multipart/form-data">
                <h2>회원가입</h2>
                <table class="table table-bordered">
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" name="id" class="form-control" placeholder="아이디를 입력해주세요."></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" name="pw" class="form-control" placeholder="비밀번호를 입력해주세요."></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" name="name" class="form-control" placeholder="이름을 입력해주세요."></td>
                    </tr>
                    <tr>
                        <th>프로필 사진</th>
                        <td><input type="file" name="pic" class="form-control"></td>
                    </tr>
                </table>
                <div class="row">
                    <input type="submit" value="회원 가입" class="btn btn-primary">
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
