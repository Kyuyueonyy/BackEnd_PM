<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>
<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>
    <%-- JSTL : 자바의 여러 기본 문법들을 코딩할 때 tag로 쓸 수 있는 기능 --%>
    <%-- taglib 지시자가 있어야함 --%>
    <c:forEach var="board" items="${list}">
        <%-- board는 dto--%>
        <tr>
            <td>${board.no}</td>
            <%-- <%= board.getNo()%> 와 같은 코드임. 따라서 get메서드가 없으면 오류가 날 것--%>
            <td>
                <a href="get?no=${board.no}">${board.title}</a>
            </td>
            <td>${board.writer}</td>
            <td>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-end">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>
<%@include file="../layouts/footer.jsp"%>