<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script> 
  function submit2(frm) { 
    frm.action="../Process/haveIdProcess.jsp"; 
    frm.submit(); 
    return true; 
  } 
</script> 
<head>
<meta charset="UTF-8">
<title>회원가ㅂ</title>
<link href="../../css/Regist.css" type = "text/css" rel = "stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
       <%
	       	String id = "";
	       	if(request.getParameter("newid") != null){
	       		id = request.getParameter("newid");
	       	}
       %>
<body>
	<%@ include file ="../include/menu.jsp" %>
	<%!String greeting = "회원가입";%>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
		</div>
	</div>
<body>
	<form action="../Process/RegistProcess.jsp" method="post">
	    <div id="wrap">
       
	        <span>아이디</span>
			
	        <input type="text" name="id" id="id" placeholder="아이디" value = '<%=id%>'><br>
	        
	        <span>중복확인</span>
	        <button  id="btn" value = '중복확인' onclick='return submit2(this.form);'>중복확인</button><br>
	        
	        <span>비밀번호</span>
	        <input type="password" name="pw" id="pw_1" class = 'pw' placeholder="비밀번호"><br>	
	        	        
	        <span>이름</span>
	        <input type="text" name="name" id="name" placeholder="이름"><br>
	        
	        <span id="em">이메일</span>
	        <input type="text" name="email" id="email" placeholder="이메일">
	        
	        <select name = 'email2'>
	            <option value="naver.com">naver.com</option>
	            <option value="nate.com">nate.com</option>
	            <option value="daum.net">daum.net</option>
	            <option value="google.com">google.com</option>
	        </select><br>
	
	        <span>생년월일</span>
	        <select id="year" name = 'year'>
	            <script>
	
	                for(var i = 2023; i >= 1940; i--){
	                    document.write("<option>", i+ "년", "</option>")
	                }
	
	            </script>
	        </select>
	        <select id="month" name = 'month'>
	            <script>
	
	                for(var i = 1; i <= 12; i++){
	                    document.write("<option value= ", i , ">", i + "월", "</option>")
	                }
	
	            </script>
	        </select>
	        <select id="day" name = 'day'>
	            <script>
	
	                for(var i = 1; i <= 31; i++){
	                    document.write("<option value= ", i , ">", i + "월", "</option>")
	                }
	
	            </script>
	        </select>
	    	<button type ='submit' id="rgstbtn" >회원가입</button> <br>
	    </div>
    </form>
    
</body>
</body>
</html>
