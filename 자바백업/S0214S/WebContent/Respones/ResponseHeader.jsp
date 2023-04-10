<%@ page import = 'java.util.Collection' %>
<%@ page import = 'java.text.SimpleDateFormat' %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long add_date = s.parse(request.getParameter("add_date")).getTime();
    
    java.sql.Date date2 = new java.sql.Date(add_date);
    System.out.print(date2);
    
    int add_int = Integer.parseInt(request.getParameter("add_int"));
    String add_str = request.getParameter("add_str");
    
//     response.addHeader("헤더이름", "vlaue");
    response.addDateHeader("myBirthday", add_date);
    response.addIntHeader("myNumber", add_int); // 헤더 만들기
    response.addIntHeader("myNumber", 1004);
    response.addHeader("myName", add_str);
    response.setHeader("myName", "정소영"); //있던 값 수정
    response.addHeader("예비", "예비입니다.");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Http-response</title>
</head>
<body>
	<h2> 응답 헤더 정보 출력하기 </h2>
	<%
		Collection <String> headerNames = response.getHeaderNames();
		//헤더 이름들 컬렉션에 넣기
		for(String hName : headerNames){
			String hValue = response.getHeader(hName);
			//네임에 속하는 헤더 벨류 들고오기
			
		
	%>
		<li><%= hName %> : <%=hValue %></li>
	<%
	}
	%>
	
	<h2> myNumber만 출력하기 </h2>
	<%
		Collection<String>myNumber = response.getHeaders("myNumber");
	for(String myNum : myNumber){
	
	%>
	<li>myNumber : <%= myNum %></li>
	<% 
	}
	%>
	
</body>
</html>