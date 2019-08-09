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
<link href="signin.css" rel="stylesheet">

<title>로그인 페이지</title>
</head>
<body class="text-center">


<!-- 중단에 표시 -->
<div id="m" align="center">
<form class="form-sign" id="loginform" action="<%= request.getContextPath() %>/login.khs" method="post">
	<table name="login_table">
		<tr>
			<td>ID</td>
			<td><input type="text" name="user_id" value="${ not empty cookie.save_user_id ? cookie.save_user_id.value : '' }" required></td>
			<td>
			<div class="checkbox mb-3">
			<label>
			<input type="checkbox" name="save_user_id"
			${ not empty cookie.save_user_id ? 'checked' : '' } value="true">(ID 저장)
			</label>
			</div>
			</td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="user_pw" required></td>
		</tr>
		<tr>
			<td><input class="btn btn-lg btn-primary btn-block" type="submit" value="로그인"></td>
			<td><button class="btn btn-lg btn-primary btn-block" type="button" onclick="location.href='<%= request.getContextPath() %>/regist.khs' ">회원가입</button></td>
		</tr>
	</table>
</form>
</div>

<p align="center"><a href="<%= request.getContextPath() %>/main.khs">메인으로</a></p>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>