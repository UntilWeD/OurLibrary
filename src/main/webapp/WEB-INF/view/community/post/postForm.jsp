<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">게시글 작성 폼</h2>

		<form method="post" enctype="multipart/form-data" class="border border-dark border-2 rounded p-4">
			<input type="hidden" name="postId" value="${po.id}" />
			<input type="hidden" name="categoryId" value="${param.c}" />

			<!-- 제목 입력 -->
			<div class="mb-3">
				<label for="title" class="form-label">제목</label>
				<input type="text" id="title" name="title" class="form-control" required value="${po.title}" />
			</div>

			<!-- 내용 입력 -->
			<div class="mb-4">
				<label for="content" class="form-label">내용</label>
				<textarea id="content" name="content" rows="8" class="form-control" required>${po.content}</textarea>
			</div>

			<!-- 기존 첨부파일 목록 -->
			<c:if test="${not empty po.attachments}">
				<div class="mb-4">
					<h5>기존 첨부 파일</h5>
					<ul class="list-group">
						<c:forEach var="att" items="${po.attachments}">
							<li class="list-group-item d-flex justify-content-between align-items-center" id="file_${att.id}">
								<span>${att.originalFilename}</span>
								<div>
									<input type="hidden" name="existingAttachmentIds" value="${att.id}">
									<button type="button" class="btn btn-sm btn-danger" onclick="removeExistingFile(${att.id})">삭제</button>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<!-- 새 첨부파일 업로드 -->
			<div class="mb-4">
				<label for="file" class="form-label">새 첨부 파일</label>
				<input type="file" id="file" name="file" class="form-control" multiple>
				<div id="newlySelectedFiles" class="form-text mt-2"></div>
			</div>

			<!-- 오류 메시지 -->
			<c:if test="${not empty error}">
				<p class="text-danger">${error}</p>
			</c:if>

			<!-- 작성 완료 버튼 -->
			<div class="d-flex justify-content-end">
				<button type="submit" class="btn btn-success">작성 완료</button>
			</div>
		</form>
	</div>
</body>
<script src="/js/community/attachment-edit.js"></script>
</html>

