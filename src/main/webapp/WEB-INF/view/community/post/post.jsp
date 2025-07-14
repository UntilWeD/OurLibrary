<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>단일 Post</title>
	<script src="/js/community/comment-edit.js"></script>
</head>
<body>
	<div>
		<h2>제목 : ${po.title}</h2> <h4>작성자 : ${po.nickname }</h4>
		<p> 내용 : ${po.content}</p>
		<p>작성일 : ${po.createdAt }</p>
		<p>좋아요 : ${po.likeCount } 싫어요 : ${po.dislikeCount } </p>
		<p>조회수 : ${po.view} 회</p>
	</div>
	<div>
		<h2>댓글들</h2>
		<c:forEach var="comment" items="${commentList}">
			<div class="comment">
				<p><strong>${comment.username}</strong> (${comment.createdAt}) </p>
				<p id="content-${comment.id}" >${comment.content}</p>
			</div>
			
			<c:if test="${sessionScope.userId == comment.userId}">
				<button onclick="enableEdit(${comment.id}, '${comment.content}', ${po.id}, ${param.c })">수정</button>
				<form id="deleteComment" action="/category/list/post/comment/delete" method="post" style="display:inline;">
				    <input type="hidden" name="po" value="${po.id}">
				    <input type="hidden" name="c" value="${param.c}">
				    <input type="hidden" name="commentId" value="${comment.id}">
				    <button type="submit">삭제</button>
				</form>
			</c:if>
			<hr/>
		</c:forEach>
		
		<h3>댓글 작성</h3>
		<form method="post" action="/category/list/post/comment/save">
			<input type="hidden" name="postId" value="${po.id}" />
			<input type="hidden" name="categoryId" value="${po.categoryId}" />
			<p>
				<label for="nickname">닉네임:</label>
				<input type="text" name="username" id="nickname" value="${sessionScope.loggedInUser}" readonly/>
			</p>
			<p>
				<label for="content">내용:</label><br>
				<textarea name="content" id="content" rows="4" cols="50" required></textarea>
			</p>
			<button type="submit">작성하기</button>
		</form>
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
<div>
	<button onclick="location.href='/'">홈으로</button>
	<button onclick="location.href='/category/list?c=${param.c}&p=${param.p}&f=${param.f}&q=${param.q}'">글목록</button>
</div>
</html>