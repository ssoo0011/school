package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public DBConnPool() {
		try {

			//준비
			Context initCtx = new InitialContext();
			//루트가서 찾기
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			//이름으로 찾기
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
			//lookup의 리턴타입은 object이기 때문에 형변환 해줘야함
			
			con = source.getConnection();
			System.out.println("DB커넥션 풀 연결 썽꽁!");
			
			
		} catch (Exception e) {
			System.out.println("연결 실패 ㅠㅠ");
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
}
