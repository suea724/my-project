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
        <div class="text-center mb-5">
            <h2>Study</h2>
            <h6>스터디 또는 프로젝트 모집 게시판</h6>
        </div>
        <c:if test="${not empty auth}">
            <div style="text-align: right">
            <input type="button" value="글 작성" class="btn btn-primary" onclick="location.href='/devcom/study/add.do'">
            </div>
        </c:if>
            <div class="row row-cols-1 row-cols-md-3 g-4" id="study-list">
            <c:if test="${empty list}">
                <h5 id="no-posts">게시글이 없습니다.</h5>
            </c:if>

            <c:forEach var="dto" items="${list}">
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

            <c:if test="${not empty list}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pagination.prev}">
                        <li class="page-item">
                            <a class="page-link" href="/devcom/study.do?page=${pagination.currentPage - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="${pagination.beginPage}" end="${pagination.endPage}" var="index">
                        <li class="page-item"><a class="page-link" href="/devcom/study.do?page=${index}">${index}</a></li>
                    </c:forEach>
                    <c:if test="${pagination.next}">
                        <li class="page-item">
                            <a class="page-link" href="/devcom/study.do?page=${pagination.currentPage + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            </c:if>
        </section>
    </main>
</div>
</body>
</html>
