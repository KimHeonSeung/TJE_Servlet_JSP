<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 완료</title>
</head>
<body>
<c:if test="${ articleWrite }" var="r">
<h1>'${ param.title }' 게시글의 작성이 완료되었습니다.</h1>
</c:if>
<c:if test="${ not r }" var="r">
<h1>게시글의 등록에 실패했습니다. (관리자에게 문의하세요.)</h1>
</c:if>
<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>
<p><a href='<%= request.getContextPath() %>/auth/article.do'>자유게시판으로 이동</a></p>

</body>
</html>