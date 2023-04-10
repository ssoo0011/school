<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../Link.jsp"></jsp:include>
	<h2>로그인페이지</h2>
	<span style="color : red; font-size: 1.2em;">
	<%=
		request.getAttribute("LoginErrMsg") == null? "" : request.getAttribute("LoginErrMsg")
	
	%>
	<%
		if(session.getAttribute("UserId") == null){
			
	%>
	<script>
		function validateForm(form) {
			if(!form.user_id.value){
				alert("아이디를 입력하세요");
				return false;
			}
			if(form.user_pw.value){
				alert("비밀번호를 입력하세요");
				return false;				
			}
		}
	</script>

	</span>
	<form action="LoginProcess.jsp" method="post" name = "loginFrm"
		onsubmit="retrun validateForm(this)">
		아이디 : <input type="text" name = "user_id"/>
		패스워드 : <input type="password" name = "user_pw">
		<input type = "submit" value = "로그인">
	</form>
	<%
		}else{
	%>
		<%= session.getAttribute("UserNmae") %>회원님, 로그인하셨습니다.
		<a href="Logout.jsp">[로그아웃]</a>
	<%
		} 
	%>
</body>
</html>