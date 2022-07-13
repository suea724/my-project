<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<html>
<head>
    <title>Devcom</title>
    <style>
        .tagify__input {
            min-height: 30px;
        }

        .tags-look .tagify__dropdown__item{
            display: inline-block;
            border-radius: 3px;
            padding: .3em .5em;
            border: 1px solid #CCC;
            background: #F3F3F3;
            margin: .2em;
            font-size: .85em;
            color: black;
            transition: 0s;
        }

        .tags-look .tagify__dropdown__item--active{
            color: black;
        }

        .tags-look .tagify__dropdown__item:hover{
            background: lightyellow;
            border-color: gold;
        }

        input[name=input-custom-dropdown] {
            padding: 100px;
        }
    </style>
</head>
<body>
<div class="container">
    <main>
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
        <section>
            <form method="post" action="/devcom/study/update.do">
                <table class="table table-borderless" id="study-add">
                    <tr>
                        <td>
                            <h6>모집 유형</h6>
                            <select class="form-control" name="category">
                                <option value="프로젝트" <c:if test="${dto.category == '프로젝트'}">selected</c:if>>프로젝트</option>
                                <option value="스터디" <c:if test="${dto.category == '스터디'}">selected</c:if>>스터디</option>
                            </select>
                        </td>
                        <td>
                            <h6>진행 방식</h6>
                            <select class="form-control" name="progressway">
                                <option value="온라인" <c:if test="${dto.progressway == '온라인'}">selected</c:if>>온라인</option>
                                <option value="오프라인" <c:if test="${dto.progressway == '오프라인'}">selected</c:if>>오프라인</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h6>모집 인원</h6>
                            <input type="number" min="1" max="100" class="form-control" placeholder="입력" name="recruitment" value="${dto.recruitment}" />
                        </td>
                        <td>
                            <h6>시작 날짜</h6>
                            <input type="date" class="form-control" name="startdate" value="${dto.startdate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h6>진행 기간</h6>
                            <select class="form-control" name="duration">
                                <option value="미정">미정</option>
                                <c:forEach begin="1" end="24" var="month" step="1">
                                    <option value="${month}" <c:if test="${month == dto.duration}">selected</c:if> >${month}개월</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <h6>연락 방법</h6>
                            <input type="text" class="form-control" placeholder="입력" name="contact" value="${dto.contact}" />
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
                            <h6>제목</h6>
                            <input type="text" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요." value="${dto.title}">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h6>내용</h6>
                            <textarea class="form-control" name="content" placeholder="내용을 입력해주세요." style="height: 300px">${dto.content}</textarea>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="수정완료" class="btn btn-primary right">
                <input type="hidden" name="seq" value="${dto.seq}">
            </form>

        </section>
    </main>
</div>
</body>
</html>
