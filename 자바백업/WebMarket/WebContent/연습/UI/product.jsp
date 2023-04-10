<%@page import="java.util.ArrayList"%>
<%@ page import = "java.util.Map" %>
<%@ page import = "java.util.Set" %>
<%@ page import = "product.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
#wrap{
	
	width : 100%;
	height: 10000px;
}
#all{
	width : 100%;
	display : inline-block;
}
</style>
<head>
<meta charset="UTF-8">
<title>제품목록</title>

<link href="../../css/style.css" type = "text/css" rel = "stylesheet">
</head>
<%
	
	ArrayList<Product> list = new ArrayList<>();

	list.add(new Product("iPhone6", "4.7inch, 1334X750 Renina HD display<br/>" + 
			"8-megapixel iSight Camera", null, "Apple", "smartprhone", 1000, 8000000));
	list.add(new Product("LG PC 그램", "13.3-inch, IPS LED display,<br/> 5rd Generation"+
			 "Intel Core processors", null, "LG", "notebook", 1000, 15000000));
	list.add(new Product("Galaxy Tab S", "4.7inch, 1334X750 Renina HD display <br/>" + 
			"8-megapixel iSight Camera", null, "Samsung", "tablet", 1000, 9000000));

	
	session.setAttribute("list", list);
	
%>
<body>
	<%@ include file ="../include/Header.jsp" %>
	<div id = 'all'>
		<div id = "wrap">
			<form action="./salePage.jsp" method="post">
		    <div id="sale">
	       	    <%
	      	    list = (ArrayList<Product>)session.getAttribute("list");
				for(int i = 0; i < list.size(); i++){
					
				%>
			        	<div class="product" onclick ='location.href = "salePage.jsp?index=<%=i%>"'>	
	
				            <div class = "name">
								<%= list.get(i).getPrdctName()%>
				            </div>
								
				            <div>
				               <%= list.get(i).getDetails() %>
				            </div>
				            <div class="price">
				                <%= list.get(i).getPrice() %>원
				            </div>
				        <input type = "button" class="btn btn-light" onclick='location.href = "salePage.jsp?index=<%=i%>"'value = "구매하기>"></input>	           
			        </div>
			    <%} %>
		    	</div>
			</form>
	    </div>
	    </div>
		<div id = "footer_area">
		<hr>
			<%@ include file = "../include/footer.jsp" %>
		</div>
		
</body>
</html>
