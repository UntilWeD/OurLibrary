<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단일 Post</title>
</head>
<body>
	<div>
		<h1>싱글 포스트 조회수 : ${po.view} 회</h1>
		<h2>제목 : ${po.title}</h2> <h4>작성자 : ${po.nickname }</h4>
		<p> 내용 : ${po.content}</p>
		<p>작성일 : ${po.createdAt }</p>
		<p>좋아요 : ${po.likeCount } 싫어요 : ${po.dislikeCount } </p>
	</div>
	
</body>
<c:if test="${sessionScope.userId == po.userId}">
    <button onclick="location.href='/category/list/post/edit?c=${po.categoryId}&po=${po.id}'">수정</button>
	<form id="deleteForm" action="/category/list/post/delete" method="post" style="display:inline;">
	    <input type="hidden" name="po" value="${po.id}">
	    <input type="hidden" name="c" value="${param.c}">
	    <button type="submit">삭제</button>
	</form>
</c:if>
	<button onclick="location.href='/'">홈으로</button>
	<button onclick="location.href='/category/list?c=${param.c}&p=${param.p}&f=${param.f}&q=${param.q}'">글목록</button>
</html>