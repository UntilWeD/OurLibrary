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
	<div>
		<h1>싱글 포스트 조회수 : ${p.view } 회</h1>
		<h2>제목 : ${p.title}</h2> <h4>작성자 : ${p.nickname }</h4>
		<p> 내용 : ${p.content}</p>
		<p>작성일 : ${p.createdAt }</p>
		<p>좋아요 : ${p.likeCount } 싫어요 : ${p.dislikeCount } </p>
	</div>
	
</body>
	<button onclick="location.href='/'">홈으로</button>
	<button onclick="location.href='/category/list'">글목록</button>
</html>