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
    <title>게시글 목록</title>
    
</head>
<body>
	<div class="container mt-5 mb-3">
  		<div class="d-flex align-items-center justify-content-between flex-wrap">
	    
	    <!-- 제목 -->
	    <div>
	    	<h3 class="mb-0">카테고리 : ${category.name}</h3>
	    </div>

	
	    <!-- 검색 폼 -->
	    <form class="d-flex gap-2 flex-wrap justify-content-between mb-0">
	      <input type="hidden" name="c" value="${param.c}" />
	      <input type="hidden" name="p" value="1" />
	      
	      <select name="f" class="form-select w-auto">
	        <option ${(param.f == "title")?"selected":""} value="title">제목</option>
	        <option ${(param.f == "writer_id")?"selected":"" } value="nickname">작성자</option>
	      </select>
	      
	      <input type="text" name="q" value="${param.q}" class="form-control w-auto" placeholder="검색어"/>
	      
	      <input class="btn btn-primary" type="submit" value="검색" />
	    </form>
	
	  	</div>
	  	
	  	<hr class="border border-dark border-2 opacity-50 mb-4">
		<div id="post-list">
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
			  <c:forEach var="post" items="${list}">
			  	<tr>
	                   <td>${post.id}</td>
	                   <td><a href="/category/list/post?c=${post.categoryId}&po=${post.id}&p=${param.p}" class="text-reset text-decoration-none">${post.title} [<span>${post.commentCount}</span>] </a></td>
	                   <td>${post.likeCount}</td>
	                   <td>${post.view}</td>
	                   <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
	               </tr>
			  </c:forEach>
			    <tr>
			  </tbody>
			</table>
		</div>	
		<div class="text-end">
			<button class="btn btn-primary" onclick="location.href='/category/list/post/save?c=${param.c}'">글쓰기</button>
		</div>
		
		
		<c:set var="page" value="${(empty param.p) ? 1 : param.p }" />
		<c:set var="startNum" value="${(page <= 6)?1:(page-5)}"/>
		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/20), '.')}"/>
		
		<div class="container my-5">
			<div class="d-flex justify-content-center align-items-center gap-2">
				<c:if test="${startNum > 5}">
					<a href="?c=${param.c}&p=${startNum - 5}&f=${param.f}&q=${param.q}" class="btn btn-prev"><i class="bi bi-caret-left-fill"></i></a>
				</c:if>
				<c:if test="${startNum <= 5}">
					<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');"><i class="bi bi-caret-left-fill"></i></span>
				</c:if>

			   <ul class="pagination mb-0 ">
		       <c:forEach var="i" begin="0" end="9">
			       <c:if test="${(startNum+i) <= lastNum}">
			          <li class="page-item ">
			            <a class="page-link" href="?c=${param.c}&p=${startNum + i}&f=${param.f}&q=${param.q}">${startNum + i}</a>
			          </li>
			        </c:if>
		        </c:forEach>
			   </ul>
			
				
				
				<c:if test="${startNum + 9 < lastNum}">
					<a href="?c=${param.c}&p=${startNum + 10}&f=${param.f}&q=${param.q}" class="btn btn-next"><i class="bi bi-caret-right-fill"></i></a>
				</c:if>
				<c:if test="${startNum + 9 >= lastNum}">
					<span class="btn btn-prev" onclick="alert('다음 페이지가 없습니다.');"><i class="bi bi-caret-right-fill"></i></span>
				</c:if>
			</div>	
		</div>
	</div>
	
	

</body>
</html>