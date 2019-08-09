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
	
	<table class="changeT">
		<tr>
			<th>ID</th>
			<td>${ login_member.member_id }</td>
		</tr>		
		<tr>
			<th>NAME</th>
			<td>${ login_member.name }</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>${ login_member.genderString }</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td>${ login_member.age eq 0 ? '나이 입력안함' : login_member.age }</td>
		</tr>
		<tr>
			<th>BIRTH</th>
			<td>${ login_member.birthString }</td>
		</tr>
		<tr>
			<th>TEL</th>
			<td>${ empty login_member.tel ? '연락처 입력안함' : login_member.tel }</td>
		</tr>
		<tr>
			<th>ADDRESS</th>
			<td>${ empty login_member.address ? '주소 입력안함' : login_member.address }</td>
		</tr>
		<tr>
			<th>REGIST_DATE</th>
			<td>${ login_member.regist_dateString }</td>
		</tr>
		<tr>
			<th>LAST_ACCESS_TIME</th>
			<td>${ login_member.last_access_timeString }</td>
		</tr>
		<tr>
			<th colspan="2">
				<button type="button" id="detail_member" onclick="parent.detail_member();">개인정보 수정화면 이동</button> 
			</th>
		</tr>
		<tr>
			<th colspan="2">
			<form action="<%=request.getContextPath()%>/main.do">
				<input type="submit" value="홈으로">
				</form>
			</th>
		</tr>
	</table>




	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>