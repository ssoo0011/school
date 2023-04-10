package CRUDTEST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class CRUDTest {
	
	Scanner scanner = new Scanner(System.in);
	Connection con;	
	User user = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; // jdbc에 돌아오는 값 담아오기
	Statement stmt = null;
	List<User> list = new Vector<User>();
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		}
		catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}
	
	
	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					"JAVA", "1234");

			System.out.println("연결성공ㅇ");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User login(String id, String passwd) {
		
		try {
			connect();
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWD = ?";
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
				
				user = new User(id, passwd, name, age, gender, email);
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
	
	public void insert() { //데이터 입력 메소드
		connect();

		try {
//
//			System.out.println("회원가입합니다.");
//			System.out.print("아이디 :");
//			String id = scanner.nextLine();		
//			System.out.print("이름 :");
//			String name = scanner.nextLine();		
//			System.out.print("패스워드 :");
//			String passwd = scanner.nextLine();
//			System.out.print("나이 :");
//			int age = scanner.nextInt();
//			scanner.nextLine();
//			System.out.print("성별 :");
//			String gender = scanner.nextLine();
//			System.out.print("이메일 :");
//			String email = scanner.nextLine();
//			scanner.nextLine();
//			
			String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "iz");
			pstmt.setString(2, "name");
			pstmt.setString(3, "passwd");
			pstmt.setInt(4, 12);
			pstmt.setString(5, "gender");
			pstmt.setString(6, "email");
//			
//			pstmt.setString(1, id);
//			pstmt.setString(2, name);
//			pstmt.setString(3, passwd);
//			pstmt.setInt(4, age);
//			pstmt.setString(5, gender);
//			pstmt.setString(6, email);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("지대로 드갔다!");

			}else {
				System.out.println("다시 넣어주세옹!");
			}
			
		} catch (SQLException e) {
			System.out.println("못넣었다 임마!");
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(String id){  
		
		connect();
		String sql = null;
		
		try {
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1. 비밀번호    2. 성별     3. 이메일");
			
			int num = scanner.nextInt();
			scanner.nextLine();
			
			switch (num) {
			case 1:
				System.out.println("수정할 비밀번호");
				String passwd = scanner.nextLine();				
				sql = "UPDATE MEMBER SET PASSWD = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, passwd);			
				break;
				
			case 2:
				System.out.println("수정할 성별");
				String gender = scanner.nextLine();
				sql = "UPDATE MEMBER SET GENDER = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gender);
				break;
				
			case 3:
				System.out.println("수정할 이메일");
				String email = scanner.nextLine();
				sql = "UPDATE MEMBER SET EMAIL = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				break;

			default:
				System.out.println("잘못된 항목입니다. 다시 입력하세요.");
				break;
			}
			pstmt.setString(2, id);
			boolean count = pstmt.execute();
			
			if (count = true) {
				System.out.println("수정완료");				
				System.out.println("수정내용 확인");
				
				sql = "SELECT * FROM MEMBER WHERE id = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				System.out.println("어디까지되는겨");
				rs = pstmt.executeQuery();
				
				while (rs.next()) { 
					System.out.println("아이디 : " + rs.getString("id")  
									+ "\t비밀번호  : " + rs.getString("passwd") 
									+ "\t이름  : " + rs.getString("name")
									+ "\t나이 : " + rs.getInt("age")
									+ "\t성별  : " + rs.getString("gender")
									+ "\temail  : " + rs.getString("email"));
				}
			}else {
				System.out.println("수정안댐");
			}
			
		} catch (SQLException e) {
			System.out.println("못바꿨따!");
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete() {
		connect();
		Scanner scanner = new Scanner(System.in);
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("삭제할 아이디");
			String id = scanner.nextLine();
			String sql = "DELETE FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			boolean count = pstmt.execute();

			
			if(count = true) {
				System.out.println("삭제 썽꽁!");
			}else {
				System.out.println("다시 넣어주세옹!");
			}
			
		} catch (SQLException e) {
			System.out.println("삭제못해!!");
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<User> select() { 
		
		connect();
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM MEMBER";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { 
				User user = new User(rs.getString("name")
						, rs.getString("passwd") 
						, rs.getString("name")
						, rs.getInt("age")
						, rs.getString("gender")
						, rs.getString("email"));
				
				list.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public User select(String id) { 

				
		try {
			connect();
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString("id");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				
				user = new User(id, passwd, name, age, gender, email);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
		
	}

}
