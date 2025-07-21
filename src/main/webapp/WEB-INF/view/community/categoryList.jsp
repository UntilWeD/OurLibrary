<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>카테고리 리스트</title>
	<link rel="stylesheet" href="css/style.css">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" />
</head>
<body>
	<header>
	  <nav class="custom-nav">
	    <div class="nav-item">
	      <a class="nav-link active" href="user/profile">
	        <i class="bi bi-person-fill"></i>
	      </a>
	    </div>
	    <div class="nav-item">
	      <a class="nav-link" href="category">
	        <i class="bi bi-card-list"></i>
	      </a>
	    </div>
	    <div class="nav-item">
	      <a class="nav-link" href="category/list?c=1&p=1">
	        <i class="bi bi-chat-dots"></i>
	      </a>
	    </div>
	    <div class="nav-item">
	      <a class="nav-link" href="logout">
	        <i class="bi bi-box-arrow-right"></i>
	      </a>
	    </div>
	  </nav>
	</header>
	<div>
		
	</div>
	
</body>
</html>