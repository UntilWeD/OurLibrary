<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/includes/header.jsp" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>단일 Post</title>
</head>
<body>
	<div class="container my-5">
		<div id="post" class="row justify-content-center mx-auto border">
			<div class="d-flex justify-content-between align-items-center my-3 px-4" style="width: 100%;">
				<h2>제목 : ${po.title}</h2>
				<h4>작성자 : ${po.nickname }</h4>
			</div>
	     	<div class="d-flex justify-content-around align-items-center border-top border-bottom">
	            <h5>첨부 파일</h5>
	            <ul>
	                <c:forEach var="attachment" items="${po.attachments}">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/category/list/post/file/download?filename=${attachment.uniqueFilename}&originalName=${attachment.originalFilename}">
	                            ${attachment.originalFilename} (<fmt:formatNumber value="${attachment.fileSize / 1024}" maxFractionDigits="2"/> KB)
	                        </a>
	                    </li>
	                </c:forEach>
	            </ul>
	           	<h5>조회수 : ${po.view} 회</h5>
	           	<h5>작성일 : ${po.createdAt}</h5>
	        </div>
	        <div class="align-items-center text-center border-bottom p-5 align-items-center">
	        	<p>${po.content}</p>
	        </div>
			<div class="d-flex justify-content-center align-items-center rounded gap-3 p-2">
				<button class="vote-button btn btn-primary" data-post-id="${po.id}" data-vote-type="LIKE">
				<i class="bi bi-hand-thumbs-up-fill"></i> (<span id="likeCount-${po.id}" class="like-count">${po.likeCount}</span>)</button>
				<button class="vote-button btn btn-warning" data-post-id="${po.id}" data-vote-type="DISLIKE">
				<i class="bi bi-hand-thumbs-down-fill"></i>(<span id="dislikeCount-${po.id}" class="dislike-count">${po.dislikeCount}</span>)</button>
			</div>
			<c:if test="${sessionScope.userId == po.userId}">
			    <button onclick="location.href='/category/list/post/edit?c=${po.categoryId}&po=${po.id}'">수정</button>
				<form id="deleteForm" action="/category/list/post/delete" method="post" style="display:inline;">
				    <input type="hidden" name="po" value="${po.id}">
				    <input type="hidden" name="c" value="${param.c}">
				    <button type="submit">삭제</button>
				</form>
			</c:if>
		</div>
		
		<div>
			<h2>댓글목록</h2>
		<c:forEach var="comment" items="${commentList}">
			<div class="comment">
				<p><strong>${comment.username}</strong> (${comment.createdAt}) </p>
				<p id="content-${comment.id}" >${comment.content}</p>
			</div>
			
			<c:if test="${sessionScope.userId == comment.userId}">
				<button class="commentEditButton" onclick="enableEdit(${comment.id}, '${comment.content}', ${po.id}, ${param.c })">수정</button>
				<form class="commentEditButton" id="deleteCommentForm" action="/category/list/post/comment/delete" method="post" style="display:inline;">
				    <input type="hidden" name="po" value="${po.id}">
				    <input type="hidden" name="c" value="${param.c}">
				    <input type="hidden" name="commentId" value="${comment.id}">
				    <button type="submit">삭제</button>
				</form>
			</c:if>
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
		
		<div id="commentPage">
			<c:set var="page" value="${(empty param.p) ? 1 : param.p }" />
			<c:set var="startNum" value="${(page <= 6)?1:(page-5)}"/>
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(po.commentCount/20), '.')}"/>
			
			<ul>
			<c:forEach var="i" begin="0" end="9">
				<c:if test="${(startNum+i) <= lastNum}">
				<li><a href="?c=${param.c}&cp=${i+1}&po=${po.id}">${startNum + i}</a></li>
				</c:if>
				
			</c:forEach>
			</ul>
			
		</div>
	</div>
	<div>
		<button onclick="location.href='/'">홈으로</button>
		<button onclick="location.href='/category/list?c=${param.c}&p=${param.p}&f=${param.f}&q=${param.q}'">글목록</button>
	</div>
		
	</div>
	

	
	
</body>
	<script src="/js/community/comment-edit.js"></script>
	<script src="/js/community/vote.js"></script>
</html>