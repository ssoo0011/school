<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1. 클라이언트와 서버의 환경 정보 읽기</h2>
	<ul>
		<!-- 요청이 어떤 방식(post, get)으로 온 건지 알려주는 메소드-->
		<li>데이터 전송 방식 : <%= request.getMethod()%></li>
		<!-- 요청 주소를 반환해주는 것 -->
		<li>URL : <%= request.getRequestURL()%></li>
		<li>URI : <%= request.getRequestURI()%></li>
		
		<li>프로토콜 : <%= request.getProtocol()%></li>
		<li>서버명 : <%= request.getServerName()%></li>
		<li>서버 포트 : <%= request.getServerPort()%></li>
		
		<!-- 클라이언트으 ip 주소를 반환해주는 것 -->
		<li>클라이언트ip주소 : <%= request.getRemoteAddr()%></li>
		<!-- 주소 뒷부분 매개변수 전달을 위한 쿼리스트링 전체 반환
		get 방식에서는 ? 뒤의 값들, post방식에는 name과 value값-->
		<li>쿼리 스트링 : <%= request.getQueryString()%></li>
		
		<li>전송 값1 : <%= request.getParameter("eng")%></li>
		<li>전송 값2 : <%= request.getParameter("han")%></li>
	</ul>

</body>
</html>