<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>좋아하는 페이지</title>


<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">



<script type="text/javascript">


</script>

</head>



<body>

	<table class="changeT">
	<c:forEach items="${likepagesearch}" var="likepage">
		<tr>
		<th>페이지 이름 : </th>
			<th>
			<a href="${likepage.likepage }">${likepage.title }</a>		
			</th>			
		</tr>		
	</c:forEach>	
	</table>




	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>