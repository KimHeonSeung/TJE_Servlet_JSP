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



</head>



<body>

<h3>'${ login_member.name }' 님의 개인정보 확인 페이지 입니다.</h3>
	
	<form id="member_form" action="<%= request.getContextPath() %>/regist.do" method="post">
	<table class="changeT">
		<caption><h2>Create account</h2></caption>
		<tr>
			<th>ID</th>
			<td>${ login_member.member_id }</td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" name="password" class="form-control" placeholder="Password" size="20" required></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" name="name" class="form-control" value="${ login_member.name }" placeholder="Name" size="20"  required></td>
		</tr>
		<tr>
			<th>GENDER</th>
			<th>
			<label>M<input type="radio" name="gender" value="1" ${ empty login_member.gender or login_member.gender eq 1 ? 'checked' : '' }></label>
			<label>W<input type="radio" name="gender" value="2" ${ not empty login_member.gender and login_member.gender eq 2 ? 'checked' : '' }></label>
			</th>
		</tr>
		<tr>
			<th>AGE</th>
			<td><input type="text" name="age" class="form-control" placeholder="Age" value="${ login_member.age }" size="20"></td>
		</tr>
		<tr>
			<th >BIRTH</th>
			<th >
			YEAR<input type="text" size="4" name="year" class="form-control" placeholder="Year" value="${ empty login_member.birthStringToken?'날짜가 입력되지않았습니다.': login_member.birthStringToken.get(1)}" size="6">
			MONTH<input type="text" size="2" name="month" class="form-control" placeholder="Month" value="${ empty login_member.birthStringToken?'날짜가 입력되지않았습니다.': login_member.birthStringToken.get(2) }" size="6">
			DAY<input type="text" size="2" name="day" class="form-control" placeholder="Day" value="${ empty login_member.birthStringToken?'날짜가 입력되지않았습니다.': login_member.birthStringToken.get(3) }" size="6">
			</th>
		</tr>
		<tr>
			<th>TEL</th>
			<td><input type="text" name="tel" class="form-control" placeholder="Tel" size="20" value="${ login_member.tel }"></td>
		</tr>
		<tr>
			<th>ADDRESS</th>
			<td><input type="text" name="address" class="form-control" placeholder="Address" size="20" value="${ login_member.address }"></td>		
		</tr>		
		<tr>
			<th colspan="2"><input type="submit" class="btn btn-default" value="update"></th>
		</tr>
	</table>
</form>




	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>