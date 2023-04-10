<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");
		
		//equalsIgnoreCase == 대소문자 구분 없이 비교
		if(id.equalsIgnoreCase("must") && pwd.equals("1234")){
			response.sendRedirect("ResponseWelcome.jsp");
			//sendRedirect페이지 이동
	
		}else{
			
			request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
			.forward(request, response);
			//재처리가 필요할 때 사용
			//ResponseMain으로 넘어간다! Dispatcher를 이용해서 수정된 값을 넘김.
			//페이지 자체가 넘어간게 아니라 제어만 넘어가서 주소는 여기로 뜸, 메인으로 안뜸
			//response, request 포함해서 같이 넘어감
		}
		%>
	
</body>
</html>