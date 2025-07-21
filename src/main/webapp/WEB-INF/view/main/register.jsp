<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 등록 페이지</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</head>
<body>
	<h1>회원가입 페이지 입니다.</h1>
	<form action="register" method="POST">
		<label>아이디   :<input type="text" name="username" /> </label>
		<label>비밀번호 :<input type="password" name="pwd" /></label>
		<label>이메일   :<input type="email" name="email" /> </label>
		<label>닉네임 :<input type="text" name="nickname" /></label>
		<button type="submit">로그인</button>
	</form>
	
</body>
</html>