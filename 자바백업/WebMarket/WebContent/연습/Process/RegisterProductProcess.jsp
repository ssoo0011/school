<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.PreparedStatement" %>
<%@ page import ="java.sql.Statement" %>
<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.ResultSet" %>
<%@ page import ="management.CookieManager" %>
<%@ page import ="management.JSFunction" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="DB.JDBConnect"%>
<%@ page import = "product.Product" %>

<%
	request.setCharacterEncoding("UTF-8"); 

	String prdctName = request.getParameter("prdctName");
	String details = request.getParameter("details");
	String code = request.getParameter("code");
	String prfrm = request.getParameter("prfrm");
	String category = request.getParameter("category");
	int stock = Integer.parseInt(request.getParameter("stock"));
	int price = Integer.parseInt(request.getParameter("price"));
	
	JDBConnect jdbc = new JDBConnect();
	String sql = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	PreparedStatement pstmt = jdbc.con.prepareStatement(sql);
	
	pstmt.setString(1, prdctName);
	pstmt.setString(2, details);
	pstmt.setString(3, code);
	pstmt.setString(4, prfrm);	
	pstmt.setString(5, category);
	pstmt.setInt(6, stock);
	pstmt.setInt(7, price);
	
	ArrayList<Product> list = new ArrayList<>();

	list.add(new Product(prdctName, details, code, prfrm, category, stock, price));

	
	int count = pstmt.executeUpdate();
	
	if(count > 0){
  %>  
 	<script> 
		alert("상상품등록 성공")
		location.href="../UI/ProductList.jsp";
  	</script> 
 <%	 
 	}else{
  %>  
	<script>
		alert("상품등록 실패")
		location.href="../UI/RegistProduct.jsp";
  	</script>		  
<%	
	}
	
	jdbc.close();
 %> 