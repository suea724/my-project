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
    <section>
      <h2 class="text-center">글 작성하기</h2>
      <form method="post" action="/devcom/community/add.do">
      <table class="table table-borderless">
        <tr>
          <td>
            <input type="text" name="title" class="form-control" placeholder="제목을 작성해주세요." required>
          </td>
        </tr>
        <tr>
          <td>
            <textarea name="content" class="form-control" placeholder="내용을 작성해주세요." style="height: 400px;" required></textarea>
          </td>
        </tr>
      </table>
        <input type="submit" value="작성 완료" class="btn btn-primary right mb-5">
      </form>
    </section>
  </main>
</div>
</body>
</html>
