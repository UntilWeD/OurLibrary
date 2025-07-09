<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
<!-- 	<script src="/js/user/user-delete.js"></script> -->
</head>
<body>
	<h1> 정말로 계정을 삭제하시겠습니까? </h1>
	<c:if test="${not empty error}">
		<p style="color:red;">${error}</p>
	</c:if>
	<form action="/user/delete" id="userDeleteForm" method="post">
		<label for="password" >탈퇴를 진행하기 위해 비밀번호를 입력해주세요. </label>
		<input type="password" id="password" name="password" />
		
		<button type="submit" >회원탈퇴</button>
	</form>
	<button onclick="location.href='/'">홈으로</button>
</body>
</html>