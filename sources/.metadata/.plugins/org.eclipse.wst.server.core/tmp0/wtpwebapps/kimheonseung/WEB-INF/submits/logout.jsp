<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 결과</title>
</head>
<body>

<h1>${ logout_user.user_id } 님의 로그아웃이 완료되었습니다..</h1>
<p><a href="<%= request.getContextPath() %>/main.khs">메인으로</a></p>


</body>
</html>