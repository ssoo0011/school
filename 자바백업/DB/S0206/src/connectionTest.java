
import java.sql.Connection; //����Ŭ ������ ����
import java.sql.DriverManager; // ������ �����͸� �ϳ��� ��ü�� �ؼ� ������ ��������
import java.sql.SQLException; // ����ó��

public class connectionTest {
	
	Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //Ŭ���� ���
		}
		catch(ClassNotFoundException cne){
			cne.printStackTrace();
		}
	}
	
	public void connect(){
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
					"java", "1234"); //���, �����, ��й�ȣ
			System.out.println("Connection Success.");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}
	public static void main(String[] args) {

		connectionTest ct = new connectionTest();
		ct.connect();
	}

}
