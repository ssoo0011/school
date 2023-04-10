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
			System.out.println("���� ���!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		
		Statement stmt = null; //�������̽� ȣ��
		
		try {

			stmt = con.createStatement();	
	
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
	
			String input = String.format("INSERT INTO MEMBER VALUES('%s', '%s', '%s', '%d', '%s', '%s')",
					id, name, passwd, age, gender, email);
			
			int count = stmt.executeUpdate(input);
			
			if (count > 0) {
				System.out.println("ȸ������ �Ϸ�");
			}else {
				System.out.println("�ٽ� �Է��ϼ���");
			}
			
		} catch (SQLException e) {
			System.out.println("ȸ������ ����");
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
