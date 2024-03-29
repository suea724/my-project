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
        <div class="text-center">
            <h2>Q&A</h2>
            <h6>궁금증을 해결해보세요.</h6>
        </div>
            <c:if test="${not empty auth}">
            <input type="button" value="글 작성" class="btn btn-primary right" onclick="location.href='qna/add.do'">
            </c:if>
            <table class="table table-bordered horizontal" id="qnalist">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일자</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>상태</th>
                </tr>
                <c:if test="${empty list}">
                <tr>
                    <td colspan="6">게시글이 없습니다.</td>
                </tr>
                </c:if>
                <c:forEach var="dto" items="${list}" begin="">
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

            <table id="qnaSearch">
                <form method="get" action="/devcom/qna.do">
                <tr>
                    <td>
                        <select class="form-control" name="type" id="type">
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                            <option value="name">작성자</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="keyword" id="keyword" class="form-control">
                    </td>
                    <td>
                        <input type="button" class="btn btn-primary" value="검색" onclick="search();" required>
                    </td>
                </tr>
                    <input type="hidden" name="isSearch" value="true">
                </form>
            </table>

            <c:if test="${isSearch == true}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pagination.prev}">
                        <li class="page-item">
                            <a class="page-link" href="/devcom/qna.do?page=${pagination.currentPage - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="${pagination.beginPage}" end="${pagination.endPage}" var="index">
                        <li class="page-item"><a class="page-link" href="/devcom/qna.do?isSearch=true&type=${type}&keyword=${keyword}&page=${index}">${index}</a></li>
                    </c:forEach>
                    <c:if test="${pagination.next}">
                        <li class="page-item">
                            <a class="page-link" href="/devcom/qna.do?page=${pagination.currentPage + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            </c:if>

            <c:if test="${empty isSearch}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pagination.prev}">
                    <li class="page-item">
                        <a class="page-link" href="/devcom/qna.do?page=${pagination.currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    </c:if>
                    <c:forEach begin="${pagination.beginPage}" end="${pagination.endPage}" var="index">
                    <li class="page-item"><a class="page-link" href="/devcom/qna.do?page=${index}">${index}</a></li>
                    </c:forEach>
                    <c:if test="${pagination.next}">
                    <li class="page-item">
                        <a class="page-link" href="/devcom/qna.do?page=${pagination.currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    </c:if>
                </ul>
            </nav>
            </c:if>
        </div>
    </main>
</div>

<script>

    <c:if test="${isSearch.equals('true')}">
    $('select[name=type]').val('${type}');
    $('input[name=keyword]').val('${keyword}');
    </c:if>

    function search() {
        let type = $('#type').val();
        let keyword = $('#keyword').val();

        location.href = '/devcom/qna.do?&type='+ type +'&keyword=' + keyword + '&isSearch=true';
    }

</script>
</body>
</html>
