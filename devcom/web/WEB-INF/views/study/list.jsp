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
            <h2>Community</h2>
            <h6>자유롭게 소통하는 게시판</h6>
        </div>
        <c:if test="${not empty auth}">
            <input type="button" value="글 작성" class="btn btn-primary right" onclick="location.href='community/add.do'">
        </c:if>
        <table class="table table-bordered horizontal" id="communityList">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성일자</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="5">게시글이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="dto" items="${list}">
                <tr>
                    <td>${dto.seq}</td>
                    <td class="text-left">
                        <a href="/devcom/community/view.do?seq=${dto.seq}">${dto.title}</a>
                        <c:if test="${dto.isnew < 0.3}">
                            <span class="badge badge-warning" style="color:white">new</span>
                        </c:if>
                    </td>
                    <td>${dto.regdate}</td>
                    <td>${dto.name}</td>
                    <td>${dto.viewcnt}</td>
                </tr>
            </c:forEach>
        </table>
    </main>
</div>
</body>
</html>
