<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 등록 페이지</title>
</head>
<body>
	<h1>회원가입 페이지 입니다.</h1>
	<form action="register" method="POST">
		<label>아이디   :<input type="text" name="username" /> </label>
		<label>비밀번호 :<input type="password" name="pwd" /></label>
		<button type="submit">로그인</button>
	</form>
</body>
</html>