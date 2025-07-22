<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty sessionScope.loggedInUser}">
	<%@ include file="/WEB-INF/view/includes/header.jsp" %>
</c:if>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>커뮤니티 </title>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" />
</head>
<body>
	<div class="container">

	<c:choose>

		<%-- Case 1 : 사용자가 로그인하여 세션에 loggedInUser정보가 존재할 때 (로그인된 경우) --%>
		<c:when test="${not empty sessionScope.loggedInUser}">

		<div class="row justify-content-center mt-5 mx-auto border rounded shadow">
			<div class="col-md-8">
				<div class="my-5 text-center">
					<h1> 환영합니다, <c:out value="${sessionScope.loggedInUser}"></c:out>님 </h1>
				</div>
				<div id="best" class="text-center">
					<div class="text-center fw-bold">
						<h2>주간 재밌는 게시물들</h2>
					</div>
					<hr class="border border-dark border-2 opacity-50 mb-4">
					<div id="bestPostList">
						<table class="table table-fixed">
						    <thead>
						        <tr>
						      <th style="width: 10%;">글 ID</th>
						      <th style="width: 45%;">제목</th>
						      <th style="width: 10%;">추천수</th>
						      <th style="width: 10%;">조회수</th>
						      <th style="width: 20%;">작성일</th>
						    	</tr>
						    </thead>
						  <tbody>
						  <c:forEach var="post" items="${bestPostList}">
						  	<tr>
			                    <td>${post.id}</td>
			                    <td><a href="category/list/post?c=${post.categoryId}&po=${post.id}&p=${param.p}" class="text-reset text-decoration-none">${post.title} [<span>${post.commentCount}</span>] </a></td>
			                    <td>${post.likeCount}</td>
			                    <td>${post.view}</td>
			                    <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
			                </tr>
						  </c:forEach>
						    <tr>
						  </tbody>
						</table>
					</div>
				</div>
				
				<div id="favoriteCategories" class="my-5">
					<div class="text-center fw-bold">
						<h2>자주 가는 카테고리들</h2>
					</div>
					<hr class="border border-dark border-2 opacity-50 mb-4">
					<div class="d-flex flex-wrap gap-2 justify-content-center p-3">
					  <a href="/category/1" class="btn btn-outline-primary rounded-pill px-3">IT 뉴스</a>
					  <a href="/category/2" class="btn btn-outline-success rounded-pill px-3">게임</a>
					  <a href="/category/3" class="btn btn-outline-danger rounded-pill px-3">운동</a>
					</div>
				</div>
		
			</div>
		</div>	
		</c:when>
		
		<%-- Case 2: 사용자가 로그인하지 않았거나 로그인에 실패하여 세션이 없는 경우 --%>
		<c:otherwise>
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

			<form action="login" method="post">
				<div class="mb-3">
				<label for="username" class="form-label">아이디:</label>
				<input type="text" id="username" name="username" class="form-control" required>
				</div>
				<div class="mb-3">
				<label for="password" class="form-label">비밀번호:</label>
				<input type="password" id="password" name="password" class="form-control" required>
				</div>
				<button type="submit" class="btn btn-primary w-100">로그인</button>
			</form>

			<p class="mt-3 text-center">
				아직 회원이 아니신가요? <a href="/register">회원가입</a>
			</p>

			</div>
		</div>
		</c:otherwise>
	
	</c:choose>
		
	</div>

</body>
</html>