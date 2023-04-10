package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	
	public JDBConnect() {
	
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "market";
			String pw = "1234";
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("연결 성꽁!(기본생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결성ㅅ공 생성자1");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs !=null) rs.close();
			if(stmt !=null) stmt.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
			
			System.out.println("연결 해제");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		
		try {
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			String url= application.getInitParameter("OracleURL");
			String id= application.getInitParameter("OracleId");
			String pwd= application.getInitParameter("OraclePwd");
			
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("연결성ㅅ공 생성자2!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
