<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form action="regist" method='post' id="regist">
		제목:<br/>
		<input type='text' id="title"><br/>
		내용:<br/>
		<input type='text' id="content"><br/>
		작성자:<br/>
		<input type='text' id="writer"><br/>
		<br/>
		<button>등록</button>
	</form>

 <script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
  
  <script>
    
  $("button").on("click", function(){
	  $("#regist").submit();
  });
  
  
  </script>

</body>
</html>