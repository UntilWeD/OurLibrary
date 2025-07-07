<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>커뮤니티 </title>
	<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<h1>환영합니다!</h1>
	
	
	<div class="container">
	
		<c:choose>
			<%-- Case 1 : 사용자가 로그인하여 세션에 loggedInUser정보가 존재할 때 (로그인된 경우) --%>
			<form action="login" method="post">
				<label>아이디: <input type="text" name="username"></label>
				<label>비밀번호: <input type="password" name="password"></label>
				<button type="submit">로그인</button>
			
			</form>
		<a href="/register">회원가입</a>
		
		</c:choose>
		
		
		
		
	</div>

</body>
</html>