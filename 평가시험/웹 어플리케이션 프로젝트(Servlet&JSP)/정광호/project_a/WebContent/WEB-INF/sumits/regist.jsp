<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
	
	
	<script type="text/javascript">
	var isCheckID = false;
	$(function() {
		$("#idCheckBtn").on("click", function() {
			var member_id = $("#member_form [name='member_id']").val();
			$.ajax({
				url:'<%=request.getContextPath()%>/check_id.do',
				type:"post",
				contentType: 
					"application/x-www-form-urlencoded; charset=utf-8",
				data:"member_id=" + member_id,
				dataType:"text",
				success:function(result){		
					
					var msg = "";
					if( eval(result) ) {						
						msg = '이미 존재하는 ID 입니다.'
					} else {
						msg = '사용할 수 있는 ID 입니다.'
						isCheckID = true;
					}
					$("#checkIDArea").text(msg);
						
				},
				error:function(result){
					alert('ID 체크 과정에서 문제 발생');					
				}
			});
		});
	});
	
	</script>
	
	
</head>
<body>
<form id="member_form" action="<%= request.getContextPath() %>/regist.do" method="post">
	<table class="changeT">
		<caption><h2>Create account</h2></caption>
		<tr>
			<th>ID</th>
			<td><input type="text" name="member_id" class="form-control" placeholder="ID" size="20" required>			
			<button id="idCheckBtn" class="btn btn-default">ID check</button>
			<span id="checkIDArea">
			</span> 
			</td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" name="password" class="form-control" placeholder="Password" size="20" required></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" name="name" class="form-control" placeholder="Name" size="20"  required></td>
		</tr>
		<tr>
			<th>GENDER</th>
			<th>
			<label>M<input type="radio" name="gender" value="1" checked></label>
			<label>W<input type="radio" name="gender" value="2" ></label>
			</th>
		</tr>
		<tr>
			<th>AGE</th>
			<td><input type="text" name="age" class="form-control" placeholder="Age" size="20"></td>
		</tr>
		<tr>
			<th >BIRTH</th>
			<th >
			YEAR<input type="text" size="4" name="year" class="form-control" placeholder="Year" size="6">
			MONTH<input type="text" size="2" name="month" class="form-control" placeholder="Month" size="6">
			DAY<input type="text" size="2" name="day" class="form-control" placeholder="Day" size="6">
			</th>
		</tr>
		<tr>
			<th>TEL</th>
			<td><input type="text" name="tel" class="form-control" placeholder="Tel" size="20"></td>
		</tr>
		<tr>
			<th>ADDRESS</th>	
			<td><input type="text" name="address" class="form-control" placeholder="Address" size="20"></td>		
		</tr>		
		<tr>
			<th colspan="2"><input type="submit" class="btn btn-default" value="Regist"></th>
		</tr>
	</table>
</form>





	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</body>
</html>