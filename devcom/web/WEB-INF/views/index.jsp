<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc/asset.jsp" %>

<html>
  <head>
    <title>Devcom</title>
  </head>

  <body>
    <div class="container">
      <main>
        <%@ include file="inc/header.jsp" %>
          <table id="index-layout">
            <tr>
              <td>
                <h3 style="margin-bottom: 30px;">Community 인기글 <i class="fa-solid fa-fire"></i></h3>
              </td>
            </tr>
            <tr>
              <td>
                <table class="table table-bordered" id="communityList">
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일자</th>
                    <th>작성자</th>
                    <th>조회수</th>
                  </tr>
                  <c:forEach var="dto" items="${clist}">
                    <tr>
                      <td>${dto.seq}</td>
                      <td class="text-left">
                        <a href="/devcom/community/view.do?seq=${dto.seq}">${dto.title}</a>
                      </td>
                      <td>${dto.regdate}</td>
                      <td>${dto.name}</td>
                      <td>${dto.viewcnt}</td>
                    </tr>
                  </c:forEach>
                </table>
              </td>
            </tr>
            <tr style="height:100px;"></tr>
            <tr>
              <td>
                <h3>Study 인기글 <i class="fa-solid fa-fire"></i></h3>
              </td>
            </tr>
            <tr>
              <td>
                <div class="row row-cols-1 row-cols-md-3 g-4" id="study-list">
                <c:forEach var="dto" items="${slist}">
                  <div class="col mt-4">
                    <div class="card" style="width: 18rem;">
                      <div class="card-body">
                        <h5 class="card-title text-center"><a href="/devcom/study/view.do?seq=${dto.seq}">${dto.title}</a></h5>
                        <h6 class="card-subtitle mb-2 text-muted text-right">${dto.name}<p class="card-text text-muted" style="font-size: 0.8rem">조회 : ${dto.viewcnt}</p></h6>
                        <div class="card-section">
                          <p class="card-text"> 시작 일자 | ${dto.startdate}</p>
                          <p class="card-text">유형 | ${dto.category}</p>
                          <c:forEach var="lang" items="${dto.langs}" begin="0" end="4">
                            <span class="lang">${lang}</span>
                          </c:forEach>
                        </div>
                      </div>
                    </div>
                  </div>
                </c:forEach>
                </div>
              </td>
            </tr>
            <tr style="height:100px;"></tr>
            <tr>
              <td>
                <h3 style="margin-bottom: 30px;">Q&A 인기글 <i class="fa-solid fa-fire"></i></h3>
              </td>
            </tr>
            <tr>
              <td>
                <table class="table table-bordered" id="qnaList">
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일자</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>상태</th>
                  </tr>
                  <c:forEach var="dto" items="${qlist}">
                    <tr>
                      <td>${dto.seq}</td>
                      <td class="text-left">
                        <a href="/devcom/qna/view.do?seq=${dto.seq}">${dto.title}</a>
                        <c:if test="${dto.isNew < 0.3}">
                          <span class="badge badge-warning" style="color:white">new</span>
                        </c:if>
                      </td>
                      <td>${dto.regdate}</td>
                      <td>${dto.name}</td>
                      <td>${dto.viewcnt}</td>
                      <td>
                        <c:if test="${dto.status == '해결'}">
                          <span class="badge badge-success">${dto.status}</span>
                        </c:if>
                        <c:if test="${dto.status == '미해결'}">
                          <span class="badge badge-secondary">${dto.status}</span>
                        </c:if>
                      </td>
                    </tr>
                  </c:forEach>
                </table>
              </td>
            </tr>
          </table>
      </main>
    </div>
  </body>
</html>
