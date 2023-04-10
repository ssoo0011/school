<%@page import="management.CookieManager" %>
<%@page import="management.JSFunction" %>
<%@page import="java.util.ArrayList"%>
<%@page import="product.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
	#infor{
		margin : auto;
	}
</style>
<html>
    <link href="../../css/style.css" type = "text/css" rel = "stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<%!String greeting = "상품정보";
	String tagline = "Welcome to Web Market!";%>

    <div id="title">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
    </div>
	<%
		ArrayList<Product>list = (ArrayList<Product>)session.getAttribute("list");
		int i = Integer.parseInt(request.getParameter("index"));
		//static 이라 생성자 안만들고 해도 됨
		loginId = CookieManager.readCookie(request, "loginId");
		cookieCheck = "";
		String url = "";
		if(!loginId.equals("")){
			cookieCheck = "checked";
			url = "saleProduct.jsp";
		}else{
			url = "logIn.jsp?index=" + i;
		}
	%>
		<form action= <%=url%> method="post">
			<div id = "infor">
				<h2><%=list.get(i).getPrdctName() %></h2>
					<p><%=list.get(i).getDetails() %>
					<ul>
					<li> 상품 코드 : <%=list.get(i).getCode() %>
					<li> 제조사 : <%=list.get(i).getPrfrm() %>
					<li> 분류 : <%=list.get(i).getCategory() %>
					<li> 재고 수 : <%=list.get(i).getStock() %>
					</ul>
					<h3><%=list.get(i).getPrice() %>원</h3>
				 <button type="submit"
					 class="btn btn-primary">상품 주문</button>
				 <button type="button" class="btn btn-secondary"
					 onclick='location.href = "product.jsp"'>상품 목록</button>
			</div>
		</form>

</body>
</html>