<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <ul>
  <li>${vo.bno}</li>
  <li>${vo.title}</li>
  <li>${vo.writer}</li>
  <li>${vo.content}</li>
  
  </ul>
  
  <button id='listBtn'>목록</button>
  <button id='modBtn'>수정</button>
  <button id='delBtn'>삭제</button>
  
  <form id='f1' action="listSearch" method='get'>
    <input type='hidden' name='page' value='${param.page}'>
    <input type='hidden' name='sType' value='${param.sType}'>
    <input type='hidden' name='keyword' value='${param.keyword}'>
    <input type='hidden' name='bno' value='${param.bno}'>
  </form>
  
  <script
  src="https://code.jquery.com/jquery-2.2.4.js"
  integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
  crossorigin="anonymous"></script>
  
  <script>
  
  $("#listBtn").on("click", function(){
	 	  $("#f1").submit();  
  });
  
  $("#delBtn").on("click", function(){
	  $("#f1").attr("action", "delete").attr("method", "post").submit();
  });
  
  
  </script>
  
</body>
</html>