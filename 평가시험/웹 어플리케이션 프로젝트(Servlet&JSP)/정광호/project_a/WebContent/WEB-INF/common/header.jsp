<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src='<%=request.getContextPath()%>/js/jquery.js'></script>

<script type="text/javascript">
var url = window.location.href;
$(function(){
	$("#logouturl").attr("href","${ pageContext.request.contextPath }/auth/logout.do?myurl="+url );
});
$(function(){
	
	$("#btn_title").on("click",function(e){
		var title = $("#title_name").val();
		var url = window.location.href;
		alert(title);
		alert(url);

		if(title != ""&&title != null){
		
		$.ajax({
			url:'<%=request.getContextPath()%>/auth/like_page.do',
			type:"post",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data:"title=" +title +"&url="+url,
			dataType:"text",
			success:function(result){			
				if( eval(result) ) {
					
					alert('저장완료');	
					$("#myModal").modal("hide");
				} else {
					alert('저장실패');	
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
		        
				alert('모달 문제발생');					
			}
		});
		}else{
			alert('타이틀을 반드시 입력해야합니다.');		
		}
	});
	
	
	
	
	
	
});
</script>


<div id="bar">
	<span id="contentLeft">


		<ul class="textup" style="list-style: none;">
			<li><a href="#"><span class="ft">H(harmony)O(of)F(food)A(and)W(wine)란??</span></a></li>
			<li><a href="#"><span class="ft">한국어</span></a></li>
			<c:if test="${not empty sessionScope.login_member }" var="result">
			<li><a href="#"><span class="ft" data-toggle="modal" data-target="#myModal">♡</span></a></li>
			</c:if>
		</ul>
	</span> <a href="<%=request.getContextPath()%>/main.do" id="login_home_bar">HOFAW(오퐈)</a>

	<span id="contentRight">
		<ul class="textup" style="list-style: none">
			<c:if test="${not empty sessionScope.login_member }" var="result">
				<li id="logout">
				<a 	href="#" id="logouturl"><span class="ft">로그아웃</span></a></li>
					<li><a	href="${ pageContext.request.contextPath }/auth/mypage.do" ><span class="ft">MyPage</span></a></li>
			</c:if>
			<c:if test="${not result }">
				<li><a href="${ pageContext.request.contextPath }/login.do"><span class="ft">로그인</span></a></li>
				<li><a href="${ pageContext.request.contextPath }/regist.do"><span class="ft">회원가입</span></a></li>
			</c:if>

			<li><a href="${ pageContext.request.contextPath }/service.do"><span class="ft">고겍센터</span></a></li>
		</ul>


	</span>
</div>


	
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">내가 좋아하는 페이지</h4>
      </div>
      <div class="modal-body">
        <table>
			<tr>
				<th><label>페이지 이름</label>
					<input type="text" id="title_name"
					class="form-control" placeholder="title_name" size="20"
					name="title_name" style="margin-bottom: 10px;"
					required></th>
			</tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="btn_title"  class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>


