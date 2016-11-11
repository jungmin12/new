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
  
  <button id='listBtn'>���</button>
  <button id='modBtn'>����</button>
  <button id='delBtn'>����</button>
  
  <form id='f1' action="listSearch" method='get'>
    <input type='hidden' name='page' value='${param.page}'>
    <input type='hidden' name='sType' value='${param.sType}'>
    <input type='hidden' name='keyword' value='${param.keyword}'>
    <input type='hidden' name='bno' value='${param.bno}'>
  </form>
  
  <button id="addReplyBtn">����߰�</button>

<div class="replyDiv">
    <p>����<input type="text" name="reply"></p>
    <p>�ۼ���<input type="text" name="replyer"></p>
    <button id ="addBtn">�߰�</button>
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

  //��� ���� �Լ�
  //replyManager�� �ѹ� �� �����
  // replyManager.addreply -> �� ȣ���̵�

  var replyMannager = (function () {

      //���⼭ Ajax�� ����
      var addReply = function (data, fn) {//1. data�� �ݹ��Լ��� �Ѱܹ���
         
       $.post("../reply/add",data, function(result){
    	   fn(result); //3. ajax���� ����� ������ �ݹ��Լ��� ������� �Ѱ���.
       });
      }; //  URL, data, �ݹ��Լ�(������� ����� �ѱ�� )

      return {addReply: addReply};
      //Ű�� addReply ���� �Լ�

  })();

  //��ü����.
  replyMannager.addReply(
		  {bno:123,reply:'AAA',replyer:'u00'}, //4.�Է°�
      function (result) { alert(result);//5. �ݹ��Լ� ����� ������ �˷��� ����ּ���!
       });

</script>

  
</body>
</html>