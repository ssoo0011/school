<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="membership.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../../css/Login.css" type = "text/css" rel = "stylesheet">
</head>
<%
	MemberDAO dao = new MemberDAO();
	String uid = (String)session.getAttribute("loginId");
	
	List<MemberDTO> memberLists = dao.getInforMemberDTO(uid);
	dao.close();
	
	for(MemberDTO dto : memberLists){

%>
<body>
		<span>아이디</span><input type = 'text' value = <%= dto.getId() %> disabled > <br>
		<span>이름</span><input type = 'text' value = <%= dto.getName() %> disabled><br>
		<span>이메일</span><input type = 'text' value = <%= dto.getEmail() %> disabled><br>
		<span>생년월일</span><input type = 'text' value = <%= dto.getBirth() %> disabled><br>
<%
	}

%>
</body>
</html>