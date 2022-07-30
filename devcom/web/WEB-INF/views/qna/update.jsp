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
      <h2 class="text-center">글 수정하기</h2>
      <form method="post" action="/devcom/qna/update.do">
      <table class="table table-borderless">
        <tr>
          <td>
            <input type="text" name="title" class="form-control" value="${dto.title}" required>
          </td>
        </tr>
        <tr>
          <td>
            <textarea name="content" class="form-control" style="height: 400px;" required>${dto.content}</textarea>
          </td>
        </tr>
      </table>
        <input type="submit" value="수정 완료" class="btn btn-primary right mb-5">
        <input type="hidden" name="seq" value="${dto.seq}">
      </form>
    </section>
  </main>
</div>
</body>
</html>
