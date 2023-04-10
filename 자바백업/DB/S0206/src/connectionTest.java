
import java.sql.Connection; //오라클 서버의 연결
import java.sql.DriverManager; // 연결한 데이터를 하나의 객체로 해서 데이터 가져오기
import java.sql.SQLException; // 예외처리

public class connectionTest {
	
	Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //클래스 경로
		}
		catch(ClassNotFoundException cne){
			cne.printStackTrace();
		}
	}
	
	public void connect(){
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
					"java", "1234"); //경로, 사용자, 비밀번호
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
