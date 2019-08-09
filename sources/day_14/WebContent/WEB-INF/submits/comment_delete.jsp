<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 삭제 완료</title>
</head>
<body>
<h1>댓글 삭제가 완료되었습니다.</h1>
<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>
<p><a href='<%= request.getContextPath() %>/auth/article.do'>자유게시판으로 이동</a></p>

</body>
</html>