<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/includes/header.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>유저 페이지</title>
		<script src="/js/user/user-edit.js"></script>
	</head>
	<body>
	<div class="container">
		<h1>유저 정보</h1>
		<hr class="border border-dark border-2 opacity-50 mb-4">
		<div>
			<form id="userForm" action="update" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">아이디: </label>
					<input name="username" value="${u.username}" readonly class="form-control" /> 
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">비밀번호:</label>
					<input name="password" value="${u.password}" readonly class="form-control"/> 
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">이메일:</label>
					<input name="email" value="${u.email}" readonly class="form-control"/> 
				</div>				
				<div class="mb-3">
					<label for="nickname" class="form-label">닉네임:</label>
					<input name="nickname" value="${u.nickname}" readonly class="form-control"/> 
				</div>				
				
				<div class="my-5">
					<button type="button" id="editBtn" class="btn btn-primary w-100">수정</button>
					<button type="submit" id="saveBtn" style="display:none;" class="btn btn-success w-100">수정완료</button>
				</div>
			</form>
			<div class="d-flex justify-content-end">
				<button onclick="location.href='/user/delete'" class="btn btn-primary" >회원탈퇴</button>
			</div>
		</div>
	</div>
	</body>
</html>