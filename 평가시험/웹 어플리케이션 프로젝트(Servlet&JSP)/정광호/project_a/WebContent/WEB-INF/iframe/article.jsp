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


<link href="<%=request.getContextPath()%>/css/bootstrap.css?asd=a"
	rel="stylesheet">
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
	
	
	
</head>
<body>

<h3>자유게시판</h3> 
<table style="border-collapse: collapse;  margin: auto; width: 70%"  border="1" >

<tr>
	<th>게시글번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성날자</th>
	<th>조회수</th>
</tr>

<c:if test="${ empty articleList }" var="r" >
	<tr>
	<th colspan="5"><h4>게시글이 존재하지 않습니다.</h4></th>
	</tr>
</c:if>

<c:if test="${ not r }">
	
	<c:forEach items="${ articleList }" var="article">
		<tr>
		<th>${ article.article_id }</th>
		<th><a onclick="parent.detail_article();">${ article.title } [${ article.comment_count }]</a></th>
		<th>${ article.name }</th>
		<th>${ article.write_timeString }</th>
		<th>${ article.read_count }</th>
		<th style="border: none; display: none;"><input type="hidden" id="article_id" value="${ article.article_id }"></th>
		</tr>
	</c:forEach>
</c:if>
</table>
 <c:set var="pre_page" value="${ now_page }"></c:set>

<nav style="margin: auto;">
  <ul class="pagination" >
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
   
    
	    <c:forEach begin="${ pre_page }" end="${page_divide + (pre_page-1)}" var="num">
	    
	    
		    <c:if test="${ (num-1) !=0 }" >
		    
		   		
			    
			    
			     <c:if test="${page_number != pre_page  }">
			      	<li><a href='<%=request.getContextPath()%>/article.do?now_page=${ num -1 }'><c:out value="${ num-1  }"/></a></li>
			      </c:if>
		     </c:if>
		   
	 
	    
	    </c:forEach>
	      <c:if test="${page_number == pre_page  }" var="s">
			     	<li><a href='<%=request.getContextPath()%>/article.do?now_page=${ pre_page -2 }'><c:out value="${ pre_page-2  }"/></a></li>
			     	<li><a href='<%=request.getContextPath()%>/article.do?now_page=${ pre_page -1 }'><c:out value="${ pre_page-1  }"/></a></li>
			     	<li><a href='<%=request.getContextPath()%>/article.do?now_page=${ pre_page  }'><c:out value="${ pre_page  }"/></a></li>
			    </c:if>
	    
	     <c:if test="${ now_page ==1}" >
		     <li><a href='<%=request.getContextPath()%>/article.do?now_page=3'><c:out value="3"/></a></li>
		     </c:if>
    <li>
      <a href="<%=request.getContextPath()%>/article.do?now_page=${ now_page+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
 



<p>[<a id="article_move" onclick="parent.write_article_iframe();">게시글 작성</a>]</p>

<form action="<%= request.getContextPath() %>/auth/article.do" method="post">
<select name="searchItem">
	<option value="title" selected>글제목</option>
	<option value="name">작성자</option>
</select>
<input type="text" size="20" name="searchValue">
<input type="submit" value="검색">
</form>




<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>


	<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>

</body>
</html>









