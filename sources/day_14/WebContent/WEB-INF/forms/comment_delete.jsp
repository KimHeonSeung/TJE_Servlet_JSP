<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 삭제</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/auth/comment_delete.do" method="post">
<h2>정말 삭제하시겠습니까?</h2>
<input type="hidden" name="comment_id" value="${ requestScope.comment_id }">
<p><input type="submit" value="네"></p>
</form>
<p><a href='${pageContext.request.contextPath}/main.do'>메인화면으로 이동</a></p>

</body>
</html>