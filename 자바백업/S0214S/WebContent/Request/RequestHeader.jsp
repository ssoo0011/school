<%@ page import = "java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>3. 요청 헤더 정보 출력하기</h2>
	<%
		//이름만 끌어옴!
		Enumeration headers = request.getHeaderNames();
		
		//hasNext()같은 느낌, 뒤에 데이터 더 있는 지 찾기
		while(headers.hasMoreElements()){ 
			//불러온 값 = Enumeration 타입,  String 타이븡로 형변환
			String headerName = (String)headers.nextElement();
			//헤더네임을 불러옴
			String headerValue = request.getHeader(headerName);
			out.print("헤더명 : " + headerName + ", 헤더 값 : "+ headerValue + "<br>");
		}

	%>
	<p> 이 파일을 직접 실행하면 referer 정보는 출력되지 않습니다.</p>
</body>
</html>