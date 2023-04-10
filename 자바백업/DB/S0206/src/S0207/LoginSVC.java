package S0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class LoginSVC {
	Connection con;
	static {		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}
	public void connect() {	
		try {
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:XE", "JAVA", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UserEx login(String id, String passwd) {
		
		UserEx user = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connect();			
			String sql = String.format("SELECT * FROM MEMBER WHERE ID = ? AND PASSWD = ?");
			pstmt = con.prepareStatement(sql); // 데이터베이스로 전송할 쿼리문 
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("id");
				passwd = rs.getString("passwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				
				user = new UserEx(id, passwd, name, age, gender, email);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
}
