<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%> <!-- 속성 true 로 지정해줘야 함. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서비스 중 일시적인 오류가 발생했습니다.</h2>
	<p>
		오류명 : <%= exception.getClass().getName() %> <br>
					<!-- 오류 클래스 이름 의 오류 이름 출력-->
		오류 메세지 : <%= exception.getMessage() %>
					<!--오류 메세지 출력 -->
</body>
</html>