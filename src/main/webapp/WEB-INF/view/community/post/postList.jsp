<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
</head>
<body>

    <h1>게시글 목록</h1>

    <p>총 게시글 수: ${count}</p>
    <p>현재 페이지: ${page}</p>
    <p>검색어: ${query}</p>
    <p>검색 필드: ${field}</p>

	<div>
	    <table class="table" border="1">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회수</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="post" items="${list}">
	                <tr>
	                    <td>${post.id}</td>
	                    <td><a href="list/post?cid=1&pid=${post.id}">${post.title}</a></td>
	                    <td>${post.nickname}</td>
	                    <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
	                    <td>${post.view}</td>
	                </tr>
	            </c:forEach>
	        </tbody>
    	</table>
	</div>
	<div>
		<h3 class="hidden">현재 페이지</h3>
		
	</div>
	<div>
		<button onclick="location.href='/'">홈으로</button>
	</div>

</body>
</html>