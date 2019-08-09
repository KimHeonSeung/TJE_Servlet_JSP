<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>



<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">



</head>
<body>



	<form action="<%=request.getContextPath()%>/login.do" method="post">
		<table class="changeT">
			<tr>
				<th style="text-align: center; font-size: 30px" height="100px"><a
					href="<%=request.getContextPath()%>/main.do" id="login_home">HOFAW</a></th>
				<th rowspan="5"><img src="./images/loginPage.jpg"
					alt="login.jpg" width="280px" height="400px"></th>
			</tr>

			<tr>

				<th><label>User Id</label><input type="text"
					class="form-control" placeholder="User Id" size="20"
					name="member_id" style="margin-bottom: 10px;"
					value="${ not empty cookie.save_login_id ? cookie.save_login_id.value : '' }"
					required></th>

			</tr>
			<tr>
				<th><label>Password</label><input type="password"
					class="form-control" placeholder="Password" size="20"
					name="password" style="margin-bottom: 10px" required></th>

			</tr>
			<tr>
				<th style="font-size: 12px; color: #969696;" height="50px"><label>
						<input type="checkbox" name="checkbox"
						${ not empty cookie.save_login_id ? 'checked' : '' }>
						Remember me
				</label></th>

			</tr>
			<tr>
				<th><input type="submit" class="btn btn-default"
					value="Sing in"></th>

			</tr>

		</table>
	</form>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</body>
</html>