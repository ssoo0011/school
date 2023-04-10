<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! 
	public int add(int num1, int num2){
		return num1 + num2;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		int result = add(10, 20);
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		
	%>
	
	덧셈 결과 1 : <%= result %><br>
	덧셈 결과 2 : <%= add(a, b) %>
	
</body>
</html>