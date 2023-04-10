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
	ResultSet rs = null; // jdbc�� ���ƿ��� �� ��ƿ���
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

			System.out.println("���Ἲ����");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User login(String id, String passwd) {
		
		try {
			connect();
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWD = ?";
			pstmt = con.prepareStatement(sql); // �����ͺ��̽��� ������ ������ 
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
	
	public void insert() { //������ �Է� �޼ҵ�
		connect();

		try {
//
//			System.out.println("ȸ�������մϴ�.");
//			System.out.print("���̵� :");
//			String id = scanner.nextLine();		
//			System.out.print("�̸� :");
//			String name = scanner.nextLine();		
//			System.out.print("�н����� :");
//			String passwd = scanner.nextLine();
//			System.out.print("���� :");
//			int age = scanner.nextInt();
//			scanner.nextLine();
//			System.out.print("���� :");
//			String gender = scanner.nextLine();
//			System.out.print("�̸��� :");
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
				System.out.println("����� �尬��!");

			}else {
				System.out.println("�ٽ� �־��ּ���!");
			}
			
		} catch (SQLException e) {
			System.out.println("���־��� �Ӹ�!");
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
			System.out.println("������ �׸��� �����ϼ���");
			System.out.println("1. ��й�ȣ    2. ����     3. �̸���");
			
			int num = scanner.nextInt();
			scanner.nextLine();
			
			switch (num) {
			case 1:
				System.out.println("������ ��й�ȣ");
				String passwd = scanner.nextLine();				
				sql = "UPDATE MEMBER SET PASSWD = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, passwd);			
				break;
				
			case 2:
				System.out.println("������ ����");
				String gender = scanner.nextLine();
				sql = "UPDATE MEMBER SET GENDER = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gender);
				break;
				
			case 3:
				System.out.println("������ �̸���");
				String email = scanner.nextLine();
				sql = "UPDATE MEMBER SET EMAIL = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				break;

			default:
				System.out.println("�߸��� �׸��Դϴ�. �ٽ� �Է��ϼ���.");
				break;
			}
			pstmt.setString(2, id);
			boolean count = pstmt.execute();
			
			if (count = true) {
				System.out.println("�����Ϸ�");				
				System.out.println("�������� Ȯ��");
				
				sql = "SELECT * FROM MEMBER WHERE id = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				System.out.println("�������Ǵ°�");
				rs = pstmt.executeQuery();
				
				while (rs.next()) { 
					System.out.println("���̵� : " + rs.getString("id")  
									+ "\t��й�ȣ  : " + rs.getString("passwd") 
									+ "\t�̸�  : " + rs.getString("name")
									+ "\t���� : " + rs.getInt("age")
									+ "\t����  : " + rs.getString("gender")
									+ "\temail  : " + rs.getString("email"));
				}
			}else {
				System.out.println("�����ȴ�");
			}
			
		} catch (SQLException e) {
			System.out.println("���ٲ��!");
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
			
			System.out.println("������ ���̵�");
			String id = scanner.nextLine();
			String sql = "DELETE FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			boolean count = pstmt.execute();

			
			if(count = true) {
				System.out.println("���� ���!");
			}else {
				System.out.println("�ٽ� �־��ּ���!");
			}
			
		} catch (SQLException e) {
			System.out.println("��������!!");
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
