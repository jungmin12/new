<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-2.2.4.js"
		integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$("#listBtn").on("click", function(event) {
				$("#f1").submit(); //form태그
			});
		});
	</script>

	<ul>
		<li>${vo.title}</li>
		<li>${vo.writer}</li>
		<li>${vo.content}</li>
		<li>${vo.regdate}</li>
	</ul>
	<button id='listBtn'>목록</button>
	<button id='modBtn'>수정</button>
	<button id='deltBtn'>삭제</button>

	<form method="get" id='f1' action="listSearch">
	<input type='hidden' name='page' value='${param.page}'> 
	<input type='hidden' name='sType' value='${param.sType}'> 
	<input type='hidden' name='keyword' value='${param.keyword}'> 
	<input type='hidden' name='bno' value='${param.bno}'>
	</form>
</body>
</html>