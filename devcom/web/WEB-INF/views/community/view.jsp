<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<html>
<head>
  <title>Devcom</title>
</head>
<style>
</style>
<body>
<div class="container">
  <main>
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <section id="communityView">
      <table class="table table-borderless">
        <tr>
          <td><h2>${dto.title}</h2></td>
        </tr>
        <tr>
          <td>
            <div>
              <span style="font-weight: bold; color: gray;">${dto.name}</span>
              &nbsp;
              <span style="color: gray;">${dto.regdate}</span>
              <br>
              <span style="color: gray;">조회 ${dto.viewcnt}회</span>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div id="contentBox">
            ${dto.content}
            </div>
          </td>
        </tr>
        <c:if test="${auth == dto.id}">
        <tr>
          <td class="text-right" id="change">
            <%--<a href="/devcom/community/update.do?seq=${dto.seq}" onclick>[수정]</a>
            <a href="/devcom/community/delete.do?seq=${dto.seq}">[삭제]</a>--%>
            <span onclick="location.href='/devcom/community/update.do?seq=${dto.seq}'">[수정]</span>
            <span onclick="deletePost()">[삭제]</span>
          </td>
        </tr>
        </c:if>
      </table>
    </section>
  </main>
</div>

<script>
  let seq = "<c:out value='${dto.seq}' />"

  deletePost = function () {
    if (confirm('삭제하시겠습니까?')) {
        location.href = '/devcom/community/delete.do?seq=' + seq;
    }
  }
</script>
</body>
</html>
