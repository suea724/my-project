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
          <td>
            <h2>
              ${dto.title}
                <c:if test="${dto.status == '해결'}">
                  <button type="button" class="btn btn-success btn-sm btn-status" id="solved">해결</button>
                </c:if>
                <c:if test="${dto.status == '미해결'}">
                  <button type="button" class="btn btn-secondary btn-sm btn-status" id="unsolved">미해결</button>
                </c:if>
            </h2>
          </td>
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
            <span onclick="location.href='/devcom/qna/update.do?seq=${dto.seq}'">[수정]</span>
            <span onclick="deletePost()">[삭제]</span>
          </td>
        </tr>
        </c:if>
      </table>
    </section>

    <section>
      <table class="table table-bordered" id="answerList">
        <c:forEach var="answer" items="${answers}">
          <tr class="answer">
            <td>
              <div class="answer-info"><span>${answer.name}</span>&nbsp;<span>${answer.regdate}</span></div>
              <div>${answer.content}</div>
              <div class="update-comment">
                <c:if test="${auth == answer.id}">
                  <input type="hidden" name="aseq" value="${answer.seq}">
                  <span class="update-answer">[수정]</span>
                  <span onclick="deleteAnswer('${answer.seq}')">[삭제]</span>
                </c:if>
              </div>
            </td>
          </tr>
        </c:forEach>
      </table>
      <table id="input-answer" class="table table-borderless">
        <tr>
          <td><textarea class="form-control" id="answer-content"></textarea></td>
          <td><input type="button" value="답변 등록" class="btn btn-primary" id="addAnswer"></td>
        </tr>
      </table>
    </section>
  </main>
</div>

<script>
  let seq = "<c:out value='${dto.seq}' />";
  let id = "<c:out value='${auth}' />";

  deletePost = function () {
    if (confirm('삭제하시겠습니까?')) {
        location.href = '/devcom/qna/delete.do?seq=' + seq;
    }
  }

  $('#addAnswer').click(function() {

    if (id == '' || id == null) {
      alert('로그인 후 답변을 작성해주세요.');
      location.href = '/devcom/login.do';
      return false;
    }

    $.ajax({
      type: 'POST',
      url: '/devcom/qna/view/addanswer.do',
      data: 'qseq=' + seq + '&content=' + $('#answer-content').val() + '&id=' + id,
      dataType: 'json',
      success: function(result) {
          location.reload();
      },
      error: function(a, b, c) {
        console.log(a, b, c);
      }
    });
  });

  <c:if test="${auth == dto.id}">
  $('#unsolved').click(function() {

    if (confirm('해결 상태로 변경 시 미해결 상태로 변경할 수 없습니다. \n계속 하시겠습니까?')) {
        $.ajax({
          type: 'GET',
          url: '/devcom/qna/view/updatestatus.do',
          data: 'qseq=' + seq,
          dataType: 'json',
          success: function(result) {
            if (result.result == 1) {
              location.reload();
            }
          },
          error: function(a, b, c) {
            console.log(a, b, c);
          }
        });
      };
    });
  </c:if>

  function deleteAnswer(seq) {
    if (confirm('댓글을 삭제하시겠습니까?')) {
      $.ajax({
        type: 'GET',
        url: '/devcom/qna/view/deleteanswer.do',
        data: 'seq=' + seq,
        dataType: 'json',
        success: function(result) {
          if (result.result == 1) {
            location.reload();
          }
        },
        error: function(a, b, c) {
          console.log(a, b, c);
        }
      });
    }
  }

  let isEditing = false;

  $(document).on('click', '.update-answer', function() {

      if (!isEditing) {

        aseq = $(this).prev().val();
        isEditing = true;

        let temp = '';
        temp += '<table class="table table-borderless"><tr>';
        temp += '<td><textarea style="resize: none; width:700px; height: 100px;" class="form-control">';
        temp += $(this).parent().prev().html();
        temp += '</textarea></td>';
        temp += `<td style="vertical-align: middle;"><input type="button" value="수정 완료" onclick="updateAnswer('\${aseq}')" class="btn btn-primary"></td>`;
        temp += '</tr></table>';

        $(this).parent().parent().html(temp);
      }

  });



</script>
</body>
</html>
