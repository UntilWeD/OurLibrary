<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 등록 페이지</title>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" />
</head>
<body>	
	<div class="row justify-content-center mt-5 mx-auto">
		<div class="col-md-4"> <!-- col-12로 하면 100% 차지, col-md-4 정도가 적당 -->
		
			<div class="error">
				<c:if test="${not empty requestScope.message}">
				<p style="color: red;">${requestScope.message}</p>
				</c:if>
			</div>

			<div class="my-5 text-center " id="loginLogo">
				<h1>Our Library</h1>
			</div>

			<form action="/register" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">아이디:</label>
					<input type="text" id="username" name="username" class="form-control" required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">비밀번호:</label>
					<input type="password" id="password" name="pwd" class="form-control" required>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">이메일:</label>
					<input type="email" id="email" name="email" class="form-control" required>
				</div>
				<div class="mb-3">
					<label for="nickname" class="form-label">닉네임:</label>
					<input type="text" id="nickname" name="nickname" class="form-control" required>
				</div>
				
				<button type="submit" class="btn btn-primary w-100 mt-4">회원가입</button>
			</form>
		</div>
	</div>
</body>
</html>