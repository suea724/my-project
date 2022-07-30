<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<html>
<head>
  <title>Devcom</title>
  <style>
    #info {
      text-align: right;
    }

    #info span, #info div {
      color: gray;
    }
  </style>
</head>
<body>
<div class="container">
  <main>
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <section>
        <table class="table table-borderless" id="study-view">
          <tr>
            <td colspan="2">
              <h2>${dto.title}</h2>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div id="delete-update">
                <c:if test="${auth == dto.id}">
                  <span onclick="location.href='/devcom/study/update.do?seq=${dto.seq}'">[수정]</span>
                  <span onclick="deletePost()">[삭제]</span>
                </c:if>
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="2" id="info">
                <span>${dto.name}</span>
                &nbsp;
                <span>${dto.regdate}</span>
                <div>조회 : ${dto.viewcnt}</div>
            </td>
          </tr>
          <tr>
            <td>
              <h5>모집 유형</h5>
              <hr>
              <span>${dto.category}</span>
            </td>
            <td>
              <h5>진행 방식</h5>
              <hr>
              <span>${dto.progressway}</span>
            </td>
          </tr>
          <tr>
            <td>
              <h5>모집 인원</h5>
              <hr>
              <span>${dto.recruitment}명</span>
            </td>
            <td>
              <h5>시작 날짜</h5>
              <hr>
              <span>${dto.startdate}</span>
            </td>
          </tr>
          <tr>
            <td>
              <h5>진행 기간</h5>
              <hr>
              <c:if test="${dto.duration == '미정'}">
                <span>${dto.duration}</span>
              </c:if>
              <c:if test="${dto.duration != '미정'}">
              <span>${dto.duration}개월</span>
              </c:if>
            </td>
            <td>
              <h5>연락 방법</h5>
              <hr>
              <span>${dto.contact}</span>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <c:forEach items="${dto.langs}" var="lang">
                <span class="lang">${lang}</span>
              </c:forEach>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div id="study-content-box">${dto.content}</div>
            </td>
          </tr>
        </table>
      <input type="button" class="btn btn-secondary right" value="목록으로" onclick="location.href='/devcom/study.do'">
    </section>
  </main>
</div>
<script>
  let seq = "<c:out value='${dto.seq}' />"

  deletePost = function () {
    if (confirm('삭제하시겠습니까?')) {
      location.href = '/devcom/study/delete.do?seq=' + seq;
    }
  }
</script>
</body>
</html>
