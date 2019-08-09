<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>개인정보 수정</title>


<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">



<script type="text/javascript">


</script>

<style type="text/css">
iframe{
display:none;
 width: 100%;
 height: 100%;

}

</style>
</head>



<body>

<h3 style="text-align: center;">'${ login_member.name }' 패스워드 확인</h3>
<form action="<%= request.getContextPath() %>/auth/updateme.do" method="post">
	<table class="changeT">
		<tr>
			<th>PW</th>	
			<td>
			<input type="password" id="password" name="password" required>			
			</td>			
		</tr>		
		<tr>
			<th colspan="2" style="text-align: center;"><button type="button" id="password_check" class="btn btn-default" onclick=" parent.updateme();" >확인</button></th>
		</tr>
	</table>
</form>












<!-- 	<div style="margin: 50px;"> -->
<!-- 		<table class="table table-striped"> -->
<%-- 			<caption> --%>
<!-- 				<span style="font-size: 30px">YOUR FAVORITE</span> -->
<%-- 			</caption> --%>
<!-- 			<tr> -->
<!-- 				<th>AT_id</th> -->
<!-- 				<th>Title</th> -->
<!-- 				<th>Date of creation</th> -->
<!-- 				<th>Writer</th> -->
<!-- 				<th>hits</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 				<th>...</th> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->

	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>