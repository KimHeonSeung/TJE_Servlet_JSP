<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 화면</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">

<script type="text/javascript" src='<%=request.getContextPath()%>/js/jquery.js'></script>

<script type="text/javascript">
	var comment_count = ${commentSize};
	
	var article_id = ${ detailArticle.article_id };
	var thumb_count = ${ thumb };
	function thumb_button() {
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/thumb_button.do',
			type:"post",
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data:"article_id=" + article_id,
			dataType:"json",
			success:function(strResult){			
				if( eval(strResult.result) ) {	
// 					$("#thumb_count").text(strResult.thumb_count);
					
					if( strResult.thumb_search == 0 ) {
						// 이미지 변경지점(기본이미지)
						$("#thumb_image").attr("src","<%=request.getContextPath()%>/images/like_none_color.jpg");
						$("#thumb_count").text(strResult.thumb_count);
					}else{
						// 이미지 변경지점(색깔 이미지)
						$("#thumb_image").attr("src","<%=request.getContextPath()%>/images/like_color.png");
						$("#thumb_count").text(strResult.thumb_count);
					}
				} else {
				}
					
			},
			error:function(strResult){
				alert('좋아요 오류 발생');					
			}
		});
	}
	function delete_comment(comment_id) {
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/delete_comment.do',
			type:"post",
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data:"comment_id=" + comment_id,
			dataType:"text",
			success:function(result){			
				if( eval(result) ) {						
					var selector = '#comment_' + comment_id
					$(selector).remove();
					alert('댓글(' + comment_id + ') 삭제 완료!');	
					
					comment_count -= 1;
					$("#comment_count").text(comment_count);
					
					if( comment_count == 0 ) {
						$("#comment_table").remove();
						$("#comment_area").append("<h4>댓글이 존재하지 않습니다.</h4>");
					}
				} else {
					alert('댓글 삭제과정에서 문제 발생');		
				}
					
			},
			error:function(result){
				alert('댓글 삭제과정에서 문제 발생');					
			}
		});
	}
	
	function insert_comment() {
		var comment_data = $("#comment_form").serialize();
		alert(comment_data)
		
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/write_comment.do',
			type:"post",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data: comment_data,
			dataType:"json",
			success:function(result){			
				var commentTag = "" ;
				if(comment_count == 0){
					alert(10);
					commentTag += "<table border='1' id='comment_table'>"
				}
				if( eval(result.result) ) {
					commentTag += "<tr id='comment_" + result.comment_id + "''>";
					commentTag += "<td>" + result.member_id + "</td>";
					commentTag += "<td>" + result.content + "</td>";					
					commentTag += "<td>" + result.date + "</td>";
					commentTag += "<td>";
					commentTag += "<button onclick='delete_comment(" + result.comment_id + ");'>삭제</button>";					
					commentTag += "</td>";
					commentTag += "</tr>";
					if(comment_count== 0){
						commentTag += "</table>"
							$("#sp").append(commentTag);
						comment_count += 1;
						$("#comment_count").text(comment_count);
						return;
					}
					$("#comment_table").append(commentTag);
					comment_count += 1;
					$("#comment_count").text(comment_count);
					
				} else {
					alert('댓글 작성에서 문제 발생');	
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("error "+(new Date()));
		        var errorMsg = 'status(code): ' + jqXHR.status + '\n';
		        errorMsg += 'statusText: ' + jqXHR.statusText + '\n';
		        errorMsg += 'responseText: ' + jqXHR.responseText + '\n';
		        errorMsg += 'textStatus: ' + textStatus + '\n';
		        errorMsg += 'errorThrown: ' + errorThrown;

		        console.log(errorMsg);
		        
				alert('댓글 작성에서 문제 발생');					
			}
		});
	}
</script>

</head>
<body>

<table border="1" >		
	<tr>
		<td>${ detailArticle.article_id }</td>
		<td>${ detailArticle.name }(${ detailArticle.member_id })</td>
		<td>${ detailArticle.title }</td>
		<td>${ detailArticle.write_timeString }</td>			
		<td>${ detailArticle.read_count }</td>
	</tr>
	<tr>
		<td colspan="5">${ detailArticle.content }</td>			
	</tr>		
</table>

<c:if test="${ login_member.member_id eq detailArticle.member_id }">
	<div>
	<a href='<%=request.getContextPath()%>/auth/update_article.do?article_id=${ detailArticle.article_id }'>[수정]</a>
	<a href='<%=request.getContextPath()%>/auth/delete_article.do?article_id=${ detailArticle.article_id }'>[삭제]</a>
	</div>
</c:if>

<div>
	<table style="border: 1px;">
		<tr>
			<th>
			<a onclick="thumb_button();">
<!-- 			카운트가 0 이면 색없는 이미지 -->
			<c:if test="${ thumb ==0}" var="a">
				<img src="<%=request.getContextPath()%>/images/like_none_color.jpg" id="thumb_image" alt="not found" height="20px" width="20px"><span id="thumb_count"> ${ thumb }</span>
			</c:if>
<!-- 			카운트가 0 이상이면 색있는 이미지 -->
			<c:if test="${not a}" >
				<img src="<%=request.getContextPath()%>/images/like_color.png" id="thumb_image" alt="not found" height="20px" width="20px"><span id="thumb_count"> ${ thumb }</span>
			</c:if>
			</a>
			</th>
		</tr>
	</table>
</div>

<h3>댓글 <span id="comment_count">${commentSize}</span></h3>
<form id="comment_form" action="<%= request.getContextPath() %>/auth/write_comment.do" method="post">
	<input type="hidden" name="article_id" value="${ detailArticle.article_id }">
	<textarea rows="3" cols="20" name="content"></textarea>	
	<input type="button" value="댓글등록" onclick="insert_comment();">
</form>

<div id="comment_area">
<c:if test="${ not empty commentList }" var="r">
<!-- 댓글 목록을 출력.... -->
<div id="sp">
<table border="1" id="comment_table">	
	<c:forEach items="${ commentList }" var="comment">
		<tr id="comment_${comment.comment_id}">
			<td>${ comment.member_id }</td>
			<td>${ comment.content }</td>
			<td>${ comment.write_timeString }</td>
			<td>
			<c:if test="${ login_member.member_id eq comment.member_id }">
			
<button onclick="delete_comment(${comment.comment_id});">삭제</button>
				
			</c:if>
			</td>
		</tr>
	</c:forEach>	
</table>
</div>
</c:if>
<c:if test="${ not r }">
	<h4>댓글이 존재하지 않습니다.</h4>
</c:if>
</div>

<p><a href='<%=request.getContextPath()%>/main.do'>메인화면</a></p>
<p><a href='<%=request.getContextPath()%>/auth/article.do'>자유게시판</a></p>




	<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>

</body>
</html>







