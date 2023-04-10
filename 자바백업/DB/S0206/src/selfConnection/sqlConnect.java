package selfConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class sqlConnect {
	Scanner scanner = new Scanner(System.in);
	Connection con;
	List<Member>mem = new ArrayList<Member>();
	int num = 0;
	
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
	
	public void inputList() { //데이터 들고오기
		
		Statement stmt = null;
		ResultSet rs = null; //결과값 담아오기, next메소드로 확인(list불러오기랑 비슷)
				
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM MEMBER";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { //데이터가 있는 상태라면! 리스트 안에 얘네 집어넣기
				
				mem.add(new Member(rs.getString("아이디"), rs.getString("비밀번호"), 
						rs.getString("이름"), rs.getInt("나이"), rs.getString("성별"),
						rs.getString("이메일")));	
				
			}
			System.out.println(mem.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public String sc() {
		
		boolean confirm = true;
		String input = "";
		
			while (confirm) {
				System.out.println(mem.size());
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
				scanner.nextLine();
				
				if (mem.size() == 0) {	
					input = String.format("INSERT INTO MEMBER VALUES('%s', '%s', '%s', '%d', '%s', '%s')",
							id, name, passwd, age, gender, email);
					Member member = new Member(id, passwd, name, age, gender, email);
					mem.add(member);
					confirm = false;
					
				}else {
					
					Member member = new Member();
					for (int i = 0; i < mem.size(); i++) {
						if (mem.get(i).id.equals(member.getId())) {
							System.out.println("존재하는 아이디");
							break;
						}else {
							input = String.format("INSERT INTO MEMBER VALUES('%s', '%s', '%s', '%d', '%s', '%s')",
									id, name, passwd, age, gender, email);
							mem.add(new Member(id, passwd, name, age, gender, email));
							confirm = false;
						}
					}
	
				}

		}
			return input;
	}
	
	public void insert() {
		
		Statement stmt = null; //인터페이스 호출
		
		try {

			stmt = con.createStatement();	
			String input = sc();
			int count = stmt.executeUpdate(input);
			
			if (count > 0) {
				System.out.println("회원가입 완료");

			}else {
				System.out.println("다시 입력하세요");
			}
			
			
		} catch (SQLException e) {
			System.out.println("회원가입 실패");
			mem.remove(num);
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
