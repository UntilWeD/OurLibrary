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
		<div id="post" class="row justify-content-center mx-auto border border-dark border-2 rounded">
			<div class="d-flex justify-content-between align-items-center py-3 px-4 border-bottom border-black border-2 rounded" style="width: 100%;">
				<h2>제목 : ${po.title}</h2>
				<h4>작성자 : ${po.nickname }</h4>
			</div>
			<!-- 조회수 및 작성일 -->
			<div class="=ol-md-6 text-end mt-3">
				<h6 class="mb-2">조회수: <span class="text-primary">${po.view}</span> 회</h6>
				<h6>작성일: <span class="text-muted">${po.createdAt}</span></h6>
			</div>
			<!-- 첨부파일 -->
			<c:if test="${not empty po.attachments}">
				<div class="mt-4 border rounded p-3 bg-light">
					<h5 class="mb-3"><i class="bi bi-paperclip"></i> 첨부 파일</h5>
					<ul class="list-group">
						<c:forEach var="attachment" items="${po.attachments}">
							<li class="list-group-item d-flex justify-content-between align-items-center">
								<div>
									<i class="bi bi-file-earmark-text me-2 text-primary"></i>
									<a href="${pageContext.request.contextPath}/category/list/post/file/download?filename=${attachment.uniqueFilename}&originalName=${attachment.originalFilename}" class="text-decoration-none text-dark">
										${attachment.originalFilename}
									</a>
							</div>
								<small class="text-muted">
									<fmt:formatNumber value="${attachment.fileSize / 1024}" maxFractionDigits="2"/> KB
								</small>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
	

	        <div class="align-items-center text-center border-bottom p-5 align-items-center border-dark">
	        	<p>${po.content}</p>
	        </div>
			<div class="d-flex justify-content-center align-items-center rounded gap-3 p-2">
				<button class="vote-button btn btn-primary" data-post-id="${po.id}" data-vote-type="LIKE">
				<i class="bi bi-hand-thumbs-up-fill"></i> (<span id="likeCount-${po.id}" class="like-count">${po.likeCount}</span>)</button>
				<button class="vote-button btn btn-warning" data-post-id="${po.id}" data-vote-type="DISLIKE">
				<i class="bi bi-hand-thumbs-down-fill"></i>(<span id="dislikeCount-${po.id}" class="dislike-count">${po.dislikeCount}</span>)</button>
			</div>
		</div>
		<div class="d-flex justify-content-end gap-1 mt-3 mp-3">
			<c:if test="${sessionScope.userId == po.userId}">
			    <button class="btn btn-dark" onclick="location.href='/category/list/post/edit?c=${po.categoryId}&po=${po.id}'">수정</button>
				<form id="deleteForm" action="/category/list/post/delete" method="post" style="display:inline;">
				    <input type="hidden" name="po" value="${po.id}">
				    <input type="hidden" name="c" value="${param.c}">
				    <button class="btn btn-dark" type="submit">삭제</button>
				</form>
			</c:if>				
		</div>
		
		<div class="mt-5 text-center">
			<h2 class="text-start">댓글목록</h2>
			<hr class="border border-dark border-2 opacity-50 mb-4">
			
			<div>
			<c:forEach var="comment" items="${commentList}">
				<div class="comment d-flex justify-content-start">
					<div class="p-3">
						<strong class="md-3">${comment.username} 님</strong>
						<p class="my-3">( ${comment.createdAt} )</p>
						<c:if test="${sessionScope.userId == comment.userId}">
							<button class="commentEditButton" onclick="enableEdit(${comment.id}, '${comment.content}', ${po.id}, ${param.c })">수정</button>
							<form class="commentEditButton" id="deleteCommentForm" action="/category/list/post/comment/delete" method="post" style="display:inline;">
							    <input type="hidden" name="po" value="${po.id}">
							    <input type="hidden" name="c" value="${param.c}">
							    <input type="hidden" name="commentId" value="${comment.id}">
							    <button type="submit">삭제</button>
							</form>
						</c:if>
					</div>
					<p class="pt-3 w-100 text-center align-items-center border border-dark border-2 comment-content" id="content-${comment.id}" >${comment.content}</p>
				</div>
			</c:forEach>		
			</div>

						<!-- 댓글 작성 폼 -->
			<div id="commentForm" class="mt-3">
			  <form method="post" action="/category/list/post/comment/save"
			        class="d-flex justify-content-between align-items-center gap-4 border p-4 rounded border-black">
			
			    <input type="hidden" name="postId" value="${po.id}" />
			    <input type="hidden" name="categoryId" value="${po.categoryId}" />
			
			    <div class="text-center" style="min-width: 100px;">
			      <label for="nickname" class="form-label fw-bold">닉네임</label>
			      <input type="text" name="username" id="nickname" value="${sessionScope.loggedInUser}"
			             class="form-control text-center" readonly />
			    </div>
			
			    <div class="flex-fill">
			        <textarea name="content" id="content" rows="3" class="form-control" required></textarea>
			    </div>
			
			    <div class="text-center">
			      <label style="visibility:hidden;">작성</label>
			      <button type="submit" class="btn btn-outline-dark">작성하기</button>
			    </div>
			  </form>
			</div>

			
			<div id="commentPage">
				<c:set var="page" value="${(empty param.p) ? 1 : param.p }" />
				<c:set var="startNum" value="${(page <= 6)?1:(page-5)}"/>
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(po.commentCount/20), '.')}"/>
				
				<ul class="pagination mb-0 justify-content-center text-center mt-5 gap-2">
				<c:forEach var="i" begin="0" end="9">
					<c:if test="${(startNum+i) <= lastNum}">
					<li class="page-item" ><a class="page-link" href="?c=${param.c}&cp=${i+1}&po=${po.id}">${startNum + i}</a></li>
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