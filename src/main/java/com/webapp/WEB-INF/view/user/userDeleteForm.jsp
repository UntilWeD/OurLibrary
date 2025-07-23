<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" />
<!-- 	<script src="/js/user/user-delete.js"></script> -->
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
	 <div class="card shadow-lg p-4" style="max-width: 500px; width: 100%;">
	   <h2 class="text-center mb-4 text-danger">정말로 계정을 삭제하시겠습니까?</h2>
	
	   <c:if test="${not empty error}">
	     <div class="alert alert-danger" role="alert">
	       ${error}
	     </div>
	   </c:if>
	
	   <form action="/user/delete" id="userDeleteForm" method="post">
	     <div class="mb-3">
	       <label for="password" class="form-label fw-bold">탈퇴를 진행하기 위해 비밀번호를 입력해주세요.</label>
	       <input type="password" class="form-control" id="password" name="password" required placeholder="비밀번호 입력">
	     </div>
	
	     <div class="d-flex justify-content-between">
	       <button type="submit" class="btn btn-danger">
	         <i class="bi bi-person-dash-fill"></i> 회원탈퇴
	       </button>
	       <button type="button" class="btn btn-secondary" onclick="location.href='/'">
	         <i class="bi bi-house-fill"></i> 홈으로
	       </button>
	     </div>
	   </form>
	 </div>
</body>
</html>