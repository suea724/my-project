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
      <form method="post" action="/devcom/study/add.do">
      <table class="table table-borderless" id="study-add">
        <tr>
          <td>
            <h6>모집 유형</h6>
            <select class="form-control" name="category">
              <option value="" disabled selected>선택</option>
              <option value="프로젝트">프로젝트</option>
              <option value="스터디">스터디</option>
            </select>
          </td>
          <td>
            <h6>진행 방식</h6>
            <select class="form-control" name="progressway">
              <option value="" disabled selected>선택</option>
              <option value="온라인">온라인</option>
              <option value="오프라인">오프라인</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <h6>모집 인원</h6>
            <input type="number" min="1" max="100" class="form-control" placeholder="입력" name="recruitment" />
          </td>
          <td>
            <h6>시작 날짜</h6>
            <input type="date" class="form-control" name="startdate"/>
          </td>
        </tr>
        <tr>
          <td>
          <h6>진행 기간</h6>
          <select class="form-control" name="duration">
            <option value="미정">미정</option>
            <c:forEach begin="1" end="24" var="month" step="1">
              <option value="${month}">${month}개월</option>
            </c:forEach>
          </select>
          </td>
          <td>
            <h6>연락 방법</h6>
            <input type="text" class="form-control" placeholder="입력" name="contact" />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>기술 스택</h6>
            <input name='basic' placeholder="" value="">
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>제목</h6>
            <input type="text" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요.">
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>내용</h6>
            <textarea class="form-control" name="content" placeholder="내용을 입력해주세요." style="height: 300px"></textarea>
          </td>
        </tr>
      </table>
        <input type="submit" value="등록하기" class="btn btn-primary right">
      </form>

    </section>
  </main>
</div>
</body>

<script>
  var input = document.querySelector('input[name=basic]');

  new Tagify(input)

</script>
</html>
