<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
    .replyDiv{
        border: 1px solid pink;
        width: 300px;
        height: 100px;
        position: absolute;
        top:50%;
        left: 50%;
        margin-left:  -150px;
        margin-top: -50px;
        background-color:  lightpink;
        display: none;
    }


    </style>
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
  
  <button id="addReplyBtn">댓글추가</button>

<div class="replyDiv">
    <p>내용<input type="text" name="reply"></p>
    <p>작성자<input type="text" name="replyer"></p>
    <button id ="addBtn">추가</button>
</div>
  
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
  
  
 
  $("#addReplyBtn").on("click",function () {
      $(".replyDiv").show('slow');
  });

  //즉시 실행 함수
  //replyManager가 한번 꼭 실행됨
  // replyManager.addreply -> 가 호출이됨

  var replyMannager = (function () {

      //여기서 Ajax를 날림
      var addReply = function (data, fn) {//1. data와 콜백함수를 넘겨받음
         
       $.post("../reply/add",data, function(result){
    	   fn(result); //3. ajax에서 결과가 나오면 콜백함수에 결과값을 넘겨줌.
       });
      }; //  URL, data, 콜백함수(결과값을 여기다 넘기는 )

      return {addReply: addReply};
      //키가 addReply 값이 함수

  })();

  //객체지향.
  replyMannager.addReply(
		  {bno:123,reply:'AAA',replyer:'u00'}, //4.입력값
      function (result) { alert(result);//5. 콜백함수 결과가 나오면 알럿을 띄어주세용!
       });

</script>

  
</body>
</html>