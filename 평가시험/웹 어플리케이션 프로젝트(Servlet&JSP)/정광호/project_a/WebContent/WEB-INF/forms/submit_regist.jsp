<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입 완료</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
	
	
	
	
</head>
<body>

<h1>CONGRATULATION..</h1>
<br>

<span style="font-size: 25px">From now on, You can use our service</span>

<form action="<%=request.getContextPath()%>/main.do">
<table class="changeT">
<tr><td><input type="submit" class="form-control" value="Home" ></td></tr>
</table>

</form>

	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</body>
</html>