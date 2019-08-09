<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이페이지</title>






<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css?as=d">

<script type="text/javascript">
	$(function() {
		$(".child a").on("click", function(e) {
			if ($(this).parent().find(".parent").css("display") == "none") {
				$(this).parent().find(".parent").css("display", "block");
			} else {
				$(this).parent().find(".parent").css("display", "none");
			}
		});
	});
	// 개인정보 수정
	$(function() {
		$("#updateme").on("click", function(e) {
			if ($("#updateme_iframe").css("display") == "none") {
				$("#updateme_iframe").css("display", "block");
				// 나머지 iframe 기능 정지
				
				// 개인정보 리스트
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
				
				// 좋아요 페이지
				$("#like_page_iframe").css("display", "none");
				//////////////
			} else {
				$("#updateme_iframe").css("display", "none");
				// 개인정보 리스트
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
				// 좋아요 페이지
				$("#like_page_iframe").css("display", "none");
				//////////////
				// 게시글 페이지
				$("#article_write_iframe").css("display", "none");
				//////////////
			}
		});
	});
	// 게시글 리스트
	$(function() {
		$("#article_list").on("click", function(e) {
			if ($("#article_list_iframe").css("display") == "none") {
				$("#article_list_iframe").css("display", "block");
				// 나머지 iframe 기능 정지
				
				// 개인정보 리스트
				$("#updateme_iframe").css("display", "none");
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
				
				// 좋아요 페이지
				$("#like_page_iframe").css("display", "none");
				//////////////
			} else {
				$("#article_list_iframe").css("display", "none");
				// 개인정보 리스트
				$("#updateme_iframe").css("display", "none");
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
			}
		});
	});
	// 좋아하는 페이지
	$(function() {
		$("#like_page").on("click", function(e) {
			if ($("#like_page_iframe").css("display") == "none") {
				$("#like_page_iframe").css("display", "block");
				// 나머지 iframe 기능 정지
				
				// 개인정보 리스트
				$("#updateme_iframe").css("display", "none");
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
			} else {
				$("#like_page_iframe").css("display", "none");
				// 개인정보 리스트
				$("#updateme_iframe").css("display", "none");
				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "none");
				//////////////
			}
		});
	});
	// 게시글 작성 페이지
	function write_article_iframe() {
		if ($("#article_write_iframe").css("display") == "none") {
			$("#article_write_iframe").css("display", "block");
			// 나머지 iframe 기능 정지
			
			// 개인정보 리스트
			$("#updateme_iframe").css("display", "none");
			$("#updateme_next_iframe").css("display", "none");
			$("#detail_update_iframe").css("display", "none");
			//////////////
			$("#article_list_iframe").css("display", "none");
			
		} else {
			$("#article_write_iframe").css("display", "none");
			// 개인정보 리스트
			$("#updateme_iframe").css("display", "none");
			$("#updateme_next_iframe").css("display", "none");
			$("#detail_update_iframe").css("display", "none");
			//////////////
		}
	}
	// 게시글 작성
	function write_article() {
		var form_write = $("#article_write_iframe").contents().find("#write_form").serialize();
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/article_write.do',
			type:"post",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			dataType:"text",
			data:form_write ,
			success:function(result){			
				if( eval(result) ) {
					
					$("#article_list_iframe").css("display","block");
					$("#article_write_iframe").css("display","none");
					
				} else {
					alert('작성 실패');	
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
		        
				alert('댓글 작성에서 문제 발생');					
			}
		});
	}
	
	function updateme() {
		var password = $("#updateme_iframe").contents().find("#password").val();
//	 	var comment_data = $("#comment_form").serialize();
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/updateme.do',
			type:"post",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data:"password=" +password,
			dataType:"text",
			success:function(result){			
				if( eval(result) ) {
					
					// 이동
					// 아이프레임 show
					$("#updateme_next_iframe").css("display","block");
					$("#updateme_iframe").css("display","none");
					$("#updateme_iframe").contents().find("#password").val("");
					
				} else {
					alert('비밀번호 불일치');	
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
		        
				alert('댓글 작성에서 문제 발생');					
			}
		});
	}
	// 게시글 디테일
	function detail_article() {
		var article_id = $("#article_list_iframe").contents().find("#article_id").val();
		$("#article_detail_iframe").attr("src","<%=request.getContextPath()%>/auth/article_detail.do?article_id="+article_id);
		
		$("#article_list_iframe").css("display", "none");
		$("#article_detail_iframe").css("display", "block");
		
	}
	
	function detail_member() {
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/detail_member.do',
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "text",
			success : function() {

				alert('접속성공');

				$("#updateme_next_iframe").css("display", "none");
				$("#detail_update_iframe").css("display", "block");

				// 					$("#updateme_iframe").contents().find("#password").val("");

			},
			error : function(jqXHR, textStatus, errorThrown) {

				alert('댓글 작성에서 문제 발생');
			}
		});
	}
</script>


<style type="text/css">
iframe {
	display: none;
	width: 100%;
	height: 100%;
}
</style>

</head>



<body>


	<section>

		<div>
			<span> <img
				src="<%=request.getContextPath()%>/images/profile.jpg"
				alt="profile.jpg" class="img-circle" style="margin-left: 80px"
				width="100px" height="100px"> <br> <br>
				<ul id="list">
					<li class="child"><a href="#">개인정보</a>
						<ul>
							<li class="parent"><a href="#" id="updateme">개인정보 수정</a></li>
							<li class="parent"><a href="#" id="like_page">내가 좋아하는
									페이지</a></li>
							<li class="parent"><a href="#">프로필 수정</a></li>
							<li class="parent"><a href="#">나의 레시피</a></li>
						</ul></li>
					<li class="child"><a href="#" id="current">게시글</a>
						<ul>
							<li class="parent"><a href="#" id="article_list">전체 게시글</a></li>
							<li class="parent"><a href="#">내가올린 게시글</a></li>
							<li class="parent"><a href="#">내가 단 댓글</a></li>
							<li class="parent"><a href="#">좋아요 누른 게시판</a></li>
							<li class="parent"><a href="#">작성중인 게시판</a></li>
						</ul></li>
					<li class="child"><a href="#">비밀</a></li>
				</ul>
			</span>
		</div>

		<!-- 	개인정보 -->
		<iframe id="updateme_iframe" src="<%=request.getContextPath()%>/auth/updateme.do" frameborder="0"></iframe>
		<!-- 개인정보 수정 -->
		<iframe id="updateme_next_iframe" src="<%=request.getContextPath()%>/auth/updateme_next.do" frameborder="0"></iframe>
		<!-- 개인정보 리스트 -->
		<iframe id="detail_update_iframe" src="<%=request.getContextPath()%>/auth/detail_member.do" frameborder="0"></iframe>

		<!-- 좋아요페이지 -->
		<iframe id="like_page_iframe" src="<%=request.getContextPath()%>/auth/like_page.do" frameborder="0"></iframe>
		<!-- 게시글리스트 -->
		<iframe id="article_list_iframe" src="<%=request.getContextPath()%>/article.do" frameborder="0"></iframe>
		<!-- 게시글 작성 -->
		<iframe id="article_write_iframe" src="<%=request.getContextPath()%>/auth/article_write.do" frameborder="0"></iframe>
        <!-- 게시글  -->
		<iframe id="article_detail_iframe"  frameborder="0"></iframe>

	</section>

	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</body>
</html>