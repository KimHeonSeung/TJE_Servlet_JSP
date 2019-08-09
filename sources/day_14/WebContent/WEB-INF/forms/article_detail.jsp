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
<form action="<%= request.getContextPath() %>/auth/article_update.do?article_id=${ detailArticle.article_id }">
	<input type="hidden" name="article_id" value="${ detailArticle.article_id }">
	<input type="hidden" name="read_count" value="${ detailArticle.read_count }">
	<input type="hidden" name="member_id" value="${ detailArticle.member_id }">
	<input type="hidden" name="name" value="${ detailArticle.name }">
	<input type="hidden" name="title" value="${ detailArticle.title }">
	<input type="hidden" name="write_time" value="${ detailArticle.write_timeString }">
	<input type="hidden" name="content" value="${ detailArticle.content }">
<table border=1>
<tr>
	<td> ${ detailArticle.article_id }</td>
	<td> ${ detailArticle.name } (${ detailArticle.member_id })</td>
	<td> ${ detailArticle.title }</td>
	<td> ${ detailArticle.write_timeString }</td>
	<td> ${ detailArticle.read_count }</td>
</tr>

<tr>
	<td colspan="4"> ${ detailArticle.content }</td>
</tr>
</table>

<c:if test="${ detailArticle.member_id eq login_member.member_id }">
	<input type="submit" value="수정">
	<a href="<%= request.getContextPath() %>/auth/article_delete.do?article_id=${ detailArticle.article_id }">삭제</a>
</c:if>
</form>


<h3>댓글 ${ commentSize }</h3>
<form action="<%= request.getContextPath() %>/auth/comment_write.do" method="post">
	<input type="hidden" name="article_id" value="${ detailArticle.article_id }">
	<textarea rows="3" cols="20" name="content"></textarea>
	<input type="submit" value="댓글등록">
</form>

<c:if test="${ not empty commentList }" var="r">
<!-- 댓글 목록을 출력 ... -->
<table border="1">
	<c:forEach items="${ commentList }" var="comment">
		<tr>
			<td>${ comment.member_id }</td>
			<td>${ comment.content }</td>
			<td>${ comment.write_timeString }</td>
			<c:if test="${ comment.member_id eq login_member.member_id }">
				<th><a href="<%= request.getContextPath() %>/auth/comment_delete.do?comment_id=${ comment.comment_id }">삭제</a></th>
			</c:if>
		</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${ not r }">
	<h4>댓글이 존재하지 않습니다.</h4>
</c:if>

<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>
<p><a href='<%= request.getContextPath() %>/auth/article.do'>자유게시판으로 이동</a></p>

</body>
</html>