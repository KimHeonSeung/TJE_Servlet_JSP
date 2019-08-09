<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인 페이지</title>




<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">





</head>
<body>





	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img class="d" src="./images/wine.jpg" alt="wine" width="300px"
					height="300px">
				<div class="carousel-caption">wine</div>
			</div>
			<div class="item">
				<img class="d" src="./images/wine2.jpg" alt="wine" width="300px"
					height="300px">
				<div class="carousel-caption">wine2</div>
			</div>
			<div class="item">
				<img class="d" src="./images/wine3.jpg" alt="wine" width="300px"
					height="300px">
				<div class="carousel-caption">wine3</div>
			</div>

		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">prev</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">next</span>
		</a>
	</div>


	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>