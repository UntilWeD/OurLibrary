<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>유저 페이지</title>
		<script src="/js/user-edit.js"></script>
	</head>
	<body>
		<h1>유저 정보</h1>
		<div>
			<form id="userForm" action="update" method="post">
				<strong>아이디:</strong><input name="username" value="${u.username}" readonly /> 
				<br/>
				<strong>비밀번호:</strong><input name="password" value="${u.password}" readonly /> 
				<br/>
				<strong>이메일:</strong><input name="email" value="${u.email}" readonly /> 
				<br/>
				<strong>닉네임:</strong><input name="nickname" value="${u.nickname}" readonly /> 
				<br/>
				<button type="button" id="editBtn">수정</button>
				<button type="submit" id="saveBtn" style="display:none;">수정완료</button>
			</form>
			<button onclick="location.href='/'">홈으로</button>
		</div>
	</body>
</html>