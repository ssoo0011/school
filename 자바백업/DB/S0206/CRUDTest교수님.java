package t0206;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;

public class CRUDTest {

	Connection con;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
					"JAVA", "1234");
			System.out.println("Connection success.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO MEMBER VALUES('AAA', '1234', '자바', 20, '여자', 'A@A.COM')";
			int count = stmt.executeUpdate(sql);
			if(count > 0) {
				System.out.println("Insert success.");
			}else {
				System.out.println("Insert fail.");
			}
		}catch(SQLException e) {
			System.out.println("Insert Fail.");
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = " UPDATE MEMBER SET GENDER = '남자' where ID = 'AAA' ";
			int count = stmt.executeUpdate(sql);
			if(count > 0) {
				System.out.println("update success.");
			}else {
				System.out.println("update fail.");
			}
		}catch(SQLException e) {
			System.out.println("Update fail.");
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete() {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = " DELETE MEMBER where ID = 'AAA' ";
			int count = stmt.executeUpdate(sql);
			if(count > 0) {
				System.out.println("DELETE success.");
			}else {
				System.out.println("DELETE fail.");
			}
		}catch(SQLException e) {
			System.out.println("DELETE fail.");
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void select() {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql = " SELECT * FROM MEMBER ";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("아이디 : " + rs.getString(1) + ", 비밀번호 : "
							        + rs.getNString("passwd") + ", 이름 : " 
						            + rs.getString("name") + ", 나이 : "
						            + rs.getInt("age") + ", 성별 : "
						            + rs.getString("gender") + ", 이메일 : "
						            + rs.getString("email") );
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		CRUDTest st = new CRUDTest();
		st.connect();
		st.insert();
		
		System.out.println("insert 후");
		st.connect();
		st.select();
				
		st.connect();
		st.update();
		System.out.println("update 후");
		st.connect();
		st.select();
				
		st.connect();
		st.delete();
		System.out.println("delete 후");
		st.connect();
		st.select();

	}

}
