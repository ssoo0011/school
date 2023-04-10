package selfConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dpql {
	Scanner scanner = new Scanner(System.in);
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
					("jdbc:oracle:thin:@localhost:1521:XE", "SOYEONG", "1234");
			System.out.println("연결 썽꽁!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		
		Statement stmt = null; //인터페이스 호출
		
		try {

			stmt = con.createStatement();	
	
			System.out.println("회원가입합니다.");
			System.out.print("아이디 :");
			String id = scanner.nextLine();		
			System.out.print("이름 :");
			String name = scanner.nextLine();		
			System.out.print("패스워드 :");
			String passwd = scanner.nextLine();
			System.out.print("나이 :");
			int age = scanner.nextInt();
			scanner.nextLine();
			System.out.print("성별 :");
			String gender = scanner.nextLine();
			System.out.print("이메일 :");
			String email = scanner.nextLine();
	
			String input = String.format("INSERT INTO MEMBER VALUES('%s', '%s', '%s', '%d', '%s', '%s')",
					id, name, passwd, age, gender, email);
			
			int count = stmt.executeUpdate(input);
			
			if (count > 0) {
				System.out.println("회원가입 완료");
			}else {
				System.out.println("다시 입력하세요");
			}
			
		} catch (SQLException e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
