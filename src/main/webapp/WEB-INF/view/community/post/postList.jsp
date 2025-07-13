<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
</head>
<body>
    
    <div>
    	<form> 	
    		<fieldset>
    			<legend class="hidden">공지사항 검색 필드</legend>
    			<label class="hidden">검색분류</label>
    			<input type="hidden" name="c" value="${param.c}" />
    			<input type="hidden" name="p" value="1" />
    			<select name="f">
    				<option ${(param.f == "title")?"selected":""} value="title">제목</option>
    				<option ${(param.f == "writer_id")?"selected":"" } value="nickname">작성자</option>
    			</select>
    			<label class="hidden">검색어</label>
    			<input type="text" name="q" value="${param.q}"/>
    			<input class="btn btn-serach" type="submit" value="검색" />
    		</fieldset>
    	</form>
    </div>

	<div>
	    <table class="table" border="1">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회수</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="post" items="${list}">
	                <tr>
	                    <td>${post.id}</td>
	                    <td><a href="list/post?c=${param.c}&po=${post.id}&p=${param.p}&f=${param.f}&q=${param.q}">${post.title}</a></td>
	                    <td>${post.nickname}</td>
	                    <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
	                    <td>${post.view}</td>
	                </tr>
	            </c:forEach>
	        </tbody>
    	</table>
	</div>
	<c:set var="page" value="${(empty param.p) ? 1 : param.p }" />
	<c:set var="startNum" value="${(page <= 6)?1:(page-5)}"/>
	<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/20), '.')}"/>
	
	<div>
	<c:if test="${startNum > 5}">
		<a href="?c=${param.c}&p=${startNum - 5}&f=${param.f}&q=${param.q}" class="btn btn-prev">이전</a>
	</c:if>
	<c:if test="${startNum <= 5}">
		<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
	</c:if>
	</div>
	
	<div>
		<h3 class="hidden">현재 페이지</h3>
		<ul>
		<c:forEach var="i" begin="0" end="9">
			<c:if test="${(startNum+i) <= lastNum}">
			<li><a href="?c=${param.c}&p=${i+1}&f=${param.f}&q=${param.q}">${startNum + i}</a></li>
			</c:if>
			
		</c:forEach>
		</ul>

	</div>
	
	<div>	
	<c:if test="${startNum + 9 < lastNum}">
		<a href="?c=${param.c}&p=${startNum + 10}&f=${param.f}&q=${param.q}" class="btn btn-next">다음</a>
	</c:if>
	<c:if test="${startNum + 9 >= lastNum}">
		<span class="btn btn-prev" onclick="alert('다음 페이지가 없습니다.');">다음</span>
	</c:if>
	</div>
	
	
	<div>
		<button onclick="location.href='/'">홈으로</button>
		<button onclick="location.href='/category/list/post/save?c=${param.c}'">글쓰기</button>
	</div>

</body>
</html>