<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

 <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->


<title>메인 페이지</title>
</head>
<body>

<p> </p>

<p> </p>
<c:forEach var="i" items="${ everyRecentBoard }">
	<c:forEach var="board" items="${boardList}">
		<h3 style="text-align: center;">
		<c:if test="${ board.board_id eq i.key }">${ board.board_name } <span style="font-size: 17px"><a href="<%= request.getContextPath() %>/simple_board.khs?board_id=${ board.board_id }">더보기</a></span></c:if></h3>
	</c:forEach>

	<table border="1" style="margin-left: auto; margin-right: auto;">
			<tr>
				<th>게시글 번호</th>
				<th>작성자</th>
				<th>글 제목</th>
				<th>작성날짜</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
	<c:forEach var="j" items="${ i.value }">
	
		<tr>
			<th>${ j.article_num }</th>
			<th>${ j.writer_nick }</th>
			<th><a href="<%= request.getContextPath() %>/detail_board.khs?article_num=${ j.article_num }">${ j.article_title }
			[<c:forEach var="comment" items="${ boardAndCommentCount }">
						<c:forEach var="k" items="${ comment.value }">
							<c:if test="${ j.article_num eq k.key }">
							${ k.value }</c:if>
						</c:forEach>
				</c:forEach>]</a></th>
			<th>${ j.write_date }</th>
			<th>${ j.read_count }</th>
			<th>${ j.like_count }</th>
		</tr>
	
	</c:forEach>

	</table>
	<p> </p>
</c:forEach>



<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>