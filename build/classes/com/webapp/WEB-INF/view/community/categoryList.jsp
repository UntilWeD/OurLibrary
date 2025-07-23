<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/view/includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>카테고리 리스트</title>
	<link rel="stylesheet" href="css/style.css">
	<script src="/js/community/category-search.js"></script>
	<script src="/js/community/store-favorite-category.js"></script>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" />
</head>
<body>
	<div class="container">
		<div id="searchCategory" class="d-flex justify-content-center mt-5" >
			<form id="categorySearchForm" class="d-flex gap-2">
			     <input type="text" name="query" value= "${param.query}" class="form-control w-auto" placeholder="카테고리 이름"/>
			      
			     <input class="btn btn-primary" type="submit" value="검색" />
		    </form>
		</div>
		<hr class="border border-dark border-2 opacity-50 mb-4">
		
		<div id="searched-category-list" class="row my-3">
			<div class="row" id="resultRow">
				
			</div>
		</div>
	
		<div id="big-category-list" class="row my-5">
			<c:forEach var="category" items="${bigCategoryList}">
            <div class="col-md-3 mb-3">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title mb-3">${category.name}</h5>
                        <p class="card-text">
                            카테고리 설명
                        </p>
                        <a href="/category/list?c=${category.id}&p=1" class="card-link">링크</a>
                    </div>
                </div>
            </div>
			</c:forEach>
		</div>
		
		<div id="requestCategory">
			<h1 class="text-center">찾으시는 카테고리가 없으신가요?</h1>
			<hr class="border border-dark border-2 opacity-50 mb-4">
			<div class="d-flex justify-content-center">
				<button onclick="location.href='/category/request'" class="btn btn-primary w-25 py-3">
				새로운 카테고리 요청</button>
			</div>
		</div>
		
	</div>
	<script src="/js/community/category-search.js"></script>
	<script src="/js/community/store-favorite-category.js"></script>
</body>
</html>