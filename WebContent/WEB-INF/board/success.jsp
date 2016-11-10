<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <h1>success!</h1>
  
  <script
  src="https://code.jquery.com/jquery-2.2.4.js"
  integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
  crossorigin="anonymous"></script>  
  
  <script>
  
  var msg = '${result}';
	var rePage = "listSearch?page=${param.page}&sType=${param.sType}&keyword=${param.keyword}";
  
  if(msg == 'DEL_SUCCESS'){
	  $("h1").html("Delete Success");

				window.history.replaceState('', '',rePage);

				setTimeout(function() {
							self.location = rePage;
						}, 2000);

			}
		</script>
</body>
</html>