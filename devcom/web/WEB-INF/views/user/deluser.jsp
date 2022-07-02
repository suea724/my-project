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
    <section id="deluser">
      <h2>주의하세요!</h2>
      <p>
        탈퇴 시 동일 아이디로 재가입이 불가능하며 회원님의 모든 개인정보가 삭제됩니다. 그래도 탈퇴하시겠습니까?
        <ul>
          <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, necessitatibus.</li>
          <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid amet animi aperiam commodi consequatur incidunt, magnam quam rem ut voluptates!</li>
          <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A asperiores debitis impedit iure repellendus voluptate.</li>
        </ul>
      </p>
      <form method="post" action="/devcom/deluser.do">
        <input type="submit" class="btn btn-danger" value="그래도 탈퇴하기">
      </form>
    </section>
  </main>
</div>
</body>
</html>
