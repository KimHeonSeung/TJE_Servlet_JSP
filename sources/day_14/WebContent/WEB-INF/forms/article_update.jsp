<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 내용 확인 화면</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/auth/article_update.do" method="post">
	<input type="hidden" name="article_id" value="${ param.article_id }">
	<input type="hidden" name="member_id" value="${ param.member_id }">
	<input type="hidden" name="name" value="${ param.name }">
	<input type="hidden" name="title" value="${ param.title }">
	<input type="hidden" name="write_time" value="${ param.write_time }">
	<input type="hidden" name="read_count" value="${ param.read_count }">
<table border=1>
<tr>
	<td> ${ param.article_id }</td>
	<td> ${ param.name } (${ param.member_id })</td>
	<td> ${ param.title }</td>
	<td> ${ param.write_time }</td>
	<td> ${ param.read_count }</td>
</tr>

<tr>
	<td colspan="4"> <textarea rows="30" cols="30" name="content" required>${ param.content }</textarea></td>
</tr>
<tr>
	<td colspan="4"><input type="submit" value="완료"></td>
</tr>
</table>
</form>



<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>
<p><a href='<%= request.getContextPath() %>/auth/article.do'>자유게시판으로 이동</a></p>

</body>
</html>