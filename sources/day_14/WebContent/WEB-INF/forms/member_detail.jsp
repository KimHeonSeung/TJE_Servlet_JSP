<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 확인</title>
</head>
<body>

	<h3>'${ login_member.member_id }' 님의 개인정보 확인 페이지 입니다.</h3>
	
	<table>		
		<tr>
			<th>ID</th>
			<td>${ login_member.member_id }</td>
		</tr>
		<tr>
			<th>NAME</th>
			<td>${ login_member.name }</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>${ login_member.genderString }</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td>${ login_member.age eq 0 ? '나이 입력 안함' : login_member.age }</td>
		</tr>
		<tr>
			<th>BIRTH</th>
			<td>${ login_member.birthString }</td>
		</tr>
		<tr>
			<th>TEL</th>
			<td>${ empty login_member.tel ? '연락처 입력 안함' : login_member.tel }</td>
		</tr>
		<tr>
			<th>ADDRESS</th>
			<td>${ empty login_member.address ? '주소 입력 안함' : login_member.address }</td>
		</tr>
		<tr>
			<th>REGIST_DATE</th>
			<td>${ login_member.regist_dateString }</td>
		</tr>
		<tr>
			<th>LAST_ACCESS_TIME</th>
			<td>${ login_member.last_access_timeString }</td>
		</tr>
		<tr>
			<th colspan="2">
				<a href='${pageContext.request.contextPath}/auth/member_update.do'>개인정보 수정</a>
			</th>
		</tr>

	</table>
	
<p>------------------------------------------------------------------------------------------------------------</p>	
<table border=1>
	<caption><h2>${ login_member.member_id } 님의 게시글</h2></caption>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			
			<c:if test="${ empty articleList }" var="r">
			<tr>
				<th colspan="5"><h4>게시글이 존재하지 않습니다.</h4></th>
			</tr>
			</c:if>
			<c:if test="${ not r }">
				<c:forEach items="${ articleList }" var="article">
				<tr>
					<th>${ article.article_id }</th>
					<th><a href='<%= request.getContextPath() %>/auth/article_detail.do?article_id=${ article.article_id }'>
							${ article.title } 
							[<c:forEach var="comment" items="${ articleIdAndCommentMap }">
								<c:if test="${ article.article_id eq comment.key }">
									${ comment.value }
								</c:if>
							</c:forEach>]
							</a></th>
					<th>${ article.name }</th>
					<th>${ article.write_timeString }</th>
					<th>${ article.read_count }</th>
				</tr>
				</c:forEach>
			</c:if>
			
</table>
	
<table border=1>
	<caption>
		<h2>${ login_member.member_id } 님의 댓글</h2>
		<h4>(댓글 내용을 클릭하면 해당 댓글이 작성된 글로 이동합니다.)</h4>
	
	</caption>
			<tr>
				<th>댓글 내용</th>
				<th>작성시간</th>
			</tr>
			
			<c:if test="${ empty commentList }" var="r">
			<tr>
				<th colspan="5"><h4>댓글이 존재하지 않습니다.</h4></th>
			</tr>
			</c:if>
			<c:if test="${ not r }">
				<c:forEach items="${ commentList }" var="comment">
				<tr>
					
					<th><a href='<%= request.getContextPath() %>/auth/article_detail.do?article_id=${ comment.article_id }'>${ comment.content }</a></th>
					<th>${ comment.write_timeString }</th>
				</tr>
				</c:forEach>
			</c:if>
</table>

<p><a href='${pageContext.request.contextPath}/main.do'>메인화면으로 이동</a></p>

</body>
</html>




