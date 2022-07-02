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
    <section id="mypage">
    <h2 class="text-center mb-3">마이페이지</h2>
    <table class="table table-bordered">
      <tr>
        <td rowspan="4" id="profilepic"><img src="/devcom/pic/${dto.pic}" width="126px;"></td>
        <td>아이디: ${dto.id}</td>
      </tr>
      <tr>
        <td>이름: ${dto.name}</td>
      </tr>
      <tr>
        <td>레벨: Lv.${dto.grade}</td>
      </tr>
      <tr>
        <td>가입날짜: ${dto.regdate}</td>
      </tr>
    </table>
      <input type="button" value="회원 탈퇴" class="btn btn-danger" onclick="location.href='/devcom/deluser.do'">
    </section>

  </main>
</div>
</body>
</html>
