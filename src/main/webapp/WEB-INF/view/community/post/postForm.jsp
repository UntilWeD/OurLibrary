<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 게시글 작성 폼 입니다.</h1>
	<form method = "post" enctype="multipart/form-data">
		<input type="hidden" name="postId" value="${po.id}" />
		<input type="hidden" name="categoryId" value="${param.c}"/>
		<div>
		    <label for="title">제목:</label><br>
        	<input type="text" id="title" name="title" required value="${po.title}" /><br><br>
		</div>

        <div>
        	<label for="content">내용:</label><br>
        	<textarea id="content" name="content" rows="10" cols="50" required>${po.content}</textarea><br><br>
        </div>
        <div id="existingFilesList">
        	<h3>기존 첨부 파일:</h3>
		    <c:forEach var="att" items="${po.attachments}"> 
		        <div id="file_${att.id}"> 
		            <span>${att.originalFilename}</span>
		            <input type="hidden" name="existingAttachmentIds" value="${att.id}"> <%-- 이 파일의 ID를 hidden 필드로 전송 --%>
		            <button type="button" onclick="removeExistingFile(${att.id})">삭제</button>
		            <br>
		        </div>
		    </c:forEach>
		</div>
		
		<div>
            <label for="file">새 첨부 파일:</label>
            <input type="file" id="file" name="file" multiple>
            <div id="newlySelectedFiles"></div>
        </div>
        
		<button type="submit">작성 완료</button>
	</form>
	    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
<script src="/js/community/attachment-edit.js"></script>
</html>

