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
			System.out.println("���� ���!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inputList() { //������ ������
		
		Statement stmt = null;
		ResultSet rs = null; //����� ��ƿ���, next�޼ҵ�� Ȯ��(list�ҷ������ ���)
				
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM MEMBER";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { //�����Ͱ� �ִ� ���¶��! ����Ʈ �ȿ� ��� ����ֱ�
				
				mem.add(new Member(rs.getString("���̵�"), rs.getString("��й�ȣ"), 
						rs.getString("�̸�"), rs.getInt("����"), rs.getString("����"),
						rs.getString("�̸���")));	
				
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
				System.out.println("ȸ�������մϴ�.");
				System.out.print("���̵� :");
				String id = scanner.nextLine();		
				System.out.print("�̸� :");
				String name = scanner.nextLine();		
				System.out.print("�н����� :");
				String passwd = scanner.nextLine();
				System.out.print("���� :");
				int age = scanner.nextInt();
				scanner.nextLine();
				System.out.print("���� :");
				String gender = scanner.nextLine();
				System.out.print("�̸��� :");
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
							System.out.println("�����ϴ� ���̵�");
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
		
		Statement stmt = null; //�������̽� ȣ��
		
		try {

			stmt = con.createStatement();	
			String input = sc();
			int count = stmt.executeUpdate(input);
			
			if (count > 0) {
				System.out.println("ȸ������ �Ϸ�");

			}else {
				System.out.println("�ٽ� �Է��ϼ���");
			}
			
			
		} catch (SQLException e) {
			System.out.println("ȸ������ ����");
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
