<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 실패</title>
</head>
<body>

<h1>회원가입 처리 도중 문제가 발생했습니다.</h1>

<table border="1">
	<tr>
		<th>ID</th>
		<th>PW</th>
		<th>PW확인</th>
		<th>닉네임</th>
		<th>연락처 / 메일</th>
		<th>가입 결과</th>
	</tr>
	<tr>
		<th>${ user.user_id }</th>
		<th>${ user.user_pw }</th>
		<th>${ user_password_confirm }</th>
		<th>${ user.user_nick }</th>
		<th>${ user.user_tel } / ${ user.user_mail }</th>
		
	</tr>
	<tr>
		<c:forEach var="result" items="${ resultList } " >
				<td>${ result }</td>		
		</c:forEach>
	</tr>
</table>

<p>ID 는 1~10자리 문자입니다. 중복확인도 필요합니다.</p>
<p>PW 는 1~15자리 문자입니다.</p>
<p>닉네임은 1~8자리 문자입니다. 중복확인도 필요합니다.</p>
<p>연락처는 1~11자리 숫자입니다. 선택사항입니다.</p>
<p>이메일은 1~30자리 문자입니다. 선택사항입니다.</p>


<p><a href="<%= request.getContextPath() %>/main.khs">메인으로</a></p>

</body>
</html>