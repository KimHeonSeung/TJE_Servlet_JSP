<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 활용 - 반복문</title>
</head>
<body>

<h3>1 ~ 10까지 출력</h3>

<!-- 시작값은 1, 끝은 10까지 반복을 돌겠다. -->
<c:set var="num">1</c:set>
<c:forEach begin="1" end="10" >

	<h4 style=color:red>${ num }</h4>
	${ num = num + 1; '' }
	
</c:forEach>

</body>
</html>