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
	<form method = "post">
		<input type="hidden" name="c" value="${param.c}"/>
        <label for="title">제목:</label><br>
        <input type="text" id="title" name="title" required value="${po.title}" /><br><br>

        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="10" cols="50" required>${po.content}</textarea><br><br>
        
		<button type="submit">작성 완료</button>
	</form>
</body>
</html>