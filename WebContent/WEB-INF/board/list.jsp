<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<style>
.list li {
	list-style: none;
}

.paging li {
	list-style: none;
	float: left;
	margin: 0.2em;
	border: 1px dotted gray;
	border-radius: 5px;
}

.paging .prev {
	background-color: pink;
}

.paging .next {
	background-color: pink;
}
</style>


	<form method="get" id='f1'>
		<input id='pageHidden' type='hidden' name='page' value=${param.page}>
		<input id='bnoHidden' type='hidden' name='bno'> <select
			id='sType' name='sType'>
			<option value='n' ${param.sType =="n" ? "selected" : "" }>--</option>
			<option value='t' ${param.sType =="t" ? "selected" : "" }>����</option>
			<option value='c' ${param.sType =="c" ? "selected" : "" }>����</option>
			<option value='w' ${param.sType =="w" ? "selected" : "" }>�ۼ���</option>
		</select> <input type='text' name='keyword' id='keyword'
			value='${param.keyword }'>
		<button id='sBtn'>�˻�</button>

	</form>


	<ul class="list">
		<c:forEach items="${list}" var="vo">
			<li>${vo.bno}<a href='${vo.bno}'> ${vo.title}</a> ${vo.regdate}
			</li>
			<br>
		</c:forEach>
	</ul>


	<ul class="paging">
		<li class='prev'>${pm.prev == true ? pm.start - 1 : ''}</li>

		<c:forEach begin="${pm.start}" end="${pm.end}" var="idx">
			<li>${idx}</li>
			<!-- idx ������ش� -->
		</c:forEach>

		<li class='next'>${pm.next == true ? pm.end + 1 : ''}</li>
	</ul>




	<!-- ���� jquer ��ũ �־� -->
	<script src="https://code.jquery.com/jquery-2.2.4.js"
		integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
		crossorigin="anonymous"></script>
	<script>
	
	var result = '${param.result}';

	if(result == 's'){
		alert("ó���Ǿ����ϴ�.");
		 window.history.replaceState('', '', "list?page=1");
	}
	</script>
	
	<script>
	
		$(document).ready(function() {

			$("#sBtn").on("click", function(event) {

				event.preventDefault();
				$("#pageHidden").val(1); // �˻� ������ �� pageHidden���� ������ 1�̿�����
				$("#f1").submit();

			});

			$(".paging li").on("click", function(event) {

				var obj = $(event.target);

				$("#pageHidden").val(obj.html());
				$("#f1").submit();

			});

			$(".list li a").on("click", function(event) {
				event.preventDefault();
				var bno = $(this).attr("href");
				console.log(bno);
				$("#bnoHidden").val(bno);
				$("#f1").attr("action", "view").submit(); //form�±�
			});

		});
	</script>

</body>
</html>