<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>게시글 작성 화면</title>


<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
	
	
</head>
<body>

<form action="<%= request.getContextPath() %>/auth/article_write.do" id="write_form" method="post">
	<table>
		<caption>게시글 작성</caption>
		<tr>
			<th>작성자</th>
			<td>${ login_member.name }(${ login_member.member_id })</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" id="title" required></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="30" cols="30" name="content" id="content" required></textarea></td>
		</tr>
		<tr>
			<th colspan="2"><button type="button" id="write_button" onclick="parent.write_article();">작성</button></th>
		</tr>
	</table>
</form>

<p><a href='<%=request.getContextPath()%>/main.do'>메인화면</a></p>
<p><a href='<%=request.getContextPath()%>/auth/article.do'>자유게시판</a></p>

	<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>

</body>
</html>