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
			<c:when test="${not empty sessionScope.loggedInUser}">
				<h2> 안녕하세요, <c:out value="${sessionScope.loggedInUser}"></c:out>님 </h2>
				<p>환영합니다. 여기에 로그인 후 보여줄 콘텐츠를 작성하세요.</p>
				
				<div>
					<a href="category/list/post/save?cid=1" class="btn btn-info">글쓰기</a>
				
					<a href="user/profile" class="btn btn-info">마이 페이지</a>
					
					<a href="logout" class="btn btn-warnindg">로그아웃 </a>
				</div>
				
			</c:when>
			
			<%-- Case 2: 사용자가 로그인하지 않았거나 로그인에 실패하여 세션이 없는 경우 --%>
			<c:otherwise>
				<c:if test="${not empty requestScope.message}">
					<p style="color: red;">${requestScope.message}</p>
				</c:if>
				
				<form action="login" method="post">
                    <div>
                        <label for="username">아이디:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div>
                        <label for="password">비밀번호:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit">로그인</button>
				</form>
				<p>아직 회원이 아니신가요? <a href="/register">회원가입</a></p>
			</c:otherwise>
		
		</c:choose>
		
		
		
		
	</div>

</body>
</html>