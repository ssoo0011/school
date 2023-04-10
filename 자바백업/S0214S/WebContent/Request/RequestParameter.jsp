<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체  - request</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		// 괄호 안의 파라미터 불러오기
		String id = request.getParameter("id");
		String sex = request.getParameter("sex");
		//다중선택 체크박스라서 여러개를 배열에 집어넣음.
		//여러개라 getParameterValues 사용
		// 선택된 값들만 value로 넘어옴!
		String[] favo = request.getParameterValues("favo");
		String favoStr = ""; // 배열을 하나씩 출력하려고 !
		
		if(favo != null){
			for(int i = 0; i < favo.length; i++){
				favoStr += favo[i] + " ";
			}
		}
		
		//replace 사용해서 바꿔줘야 함
		//\r\n(엔터키) => <br>로 해줘야함
		String intro = request.getParameter("intro").replace("\r\n", "<br>");
	%>
	<ul>
		<li> 아이디 : <%= id %></li>
		<li> 성별 : <%= sex %></li>
		<li> 관심사항 : <%= favoStr %></li>
		<li> 자기소개 : <%= intro %></li>
	</ul>
</body>
</html>