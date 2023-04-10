package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class JdbcUtil {
	
	static { //드라이버 로드
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
			con.setAutoCommit(false); //특벼한 문제가 업승ㄹ 때만 commit시킴 그걸 껐음.
//			System.out.println("연결성공ㅇ");
			
		}catch (Exception e) {
			e.printStackTrace();
//			System.out.println("연결안댐ㅋ");
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































