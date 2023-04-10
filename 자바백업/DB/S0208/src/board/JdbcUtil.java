package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class JdbcUtil {
	
	static { //����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		}
		catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					"JAVA", "1234");
			con.setAutoCommit(false); //Ư���� ������ ���¤� ���� commit��Ŵ �װ� ����.
//			System.out.println("���Ἲ����");
			
		}catch (Exception e) {
			e.printStackTrace();
//			System.out.println("����ȴ碌");
		}
		return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void commit(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void rollback(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}































