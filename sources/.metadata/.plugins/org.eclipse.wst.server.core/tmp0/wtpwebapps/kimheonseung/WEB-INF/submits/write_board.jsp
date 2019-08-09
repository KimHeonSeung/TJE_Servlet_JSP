<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
</head>
<body>

<h1>게시글 작성 완료</h1>


<a href="<%= request.getContextPath() %>/simple_board.khs?board_id=${ board_id }">게시판으로 이동</a>

</body>
</html>