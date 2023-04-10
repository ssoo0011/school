package board;

// ��ü���� ��� ����
import static board.JdbcUtil.close;
import static board.JdbcUtil.commit;
import static board.JdbcUtil.getConnection;
import static board.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class BoardSVC {
	
	Scanner sc = new Scanner(System.in);
	Connection con;
	BoardVO bv;
	PreparedStatement pstmt;
	ResultSet rs = null;
	List<BoardVO> list = new Vector<BoardVO>();
	
	public void Result() {
		con = getConnection();
		
	}
	

	public BoardVO getBoardVO(Scanner sc) { //�Խù� ���
		
		System.out.println("��������������������������������������������  �Խù� ���  ��������������������������������������������");
		System.out.print("�ۼ��� :");
		String writer = sc.nextLine();
		System.out.print("��й�ȣ :");
		String passwd = sc.nextLine();
		System.out.print("���� :");
		String subject = sc.nextLine();
		System.out.print("�̸��� :");
		String email = sc.nextLine();

		bv = new BoardVO(0, writer, passwd, subject, email);
		return bv;
	}
	
	public void writerArticle(Scanner sc) {
		
		bv = getBoardVO(sc);
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		
		try {
			String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bv.getWriter());
			pstmt.setString(2, bv.getPasswd());
			pstmt.setString(3, bv.getSubject());
			pstmt.setString(4, bv.getEmail());
			
			int count = pstmt.executeUpdate();

			if (count > 0) {

				System.out.println("�Խñ� �ۼ� ��!!");
				commit(con);
			}else {
				System.out.println("�ȵ尬�µ���?��");
				rollback(con);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				//pstmt �ݴ°� ��� pstmt������ ��ĳ���� �Ͼ�� pstmt�� ���� �� ����!
				close(pstmt);
				close(con);
		}
	}
	
	public void showArticleList() {
		
		System.out.println("��ü �� ���");
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		String sql = "SELECT * FROM BOARD";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
	              System.out.printf("�۹�ȣ : %d, �ۼ��� : %s, ���� : %s, �̸��� : %s\n",
	    	              rs.getInt("id"),
	    	              rs.getString("writer"),
	    	              rs.getString("subject"),
	    	              rs.getString("email"));	
				while (rs.next()) { 

	              System.out.printf("�۹�ȣ : %d, �ۼ��� : %s, ���� : %s, �̸��� : %s\n",
	    	              rs.getInt("id"),
	    	              rs.getString("writer"),
	    	              rs.getString("subject"),
	    	              rs.getString("email"));	
				}

			}else {
				System.out.println("������x");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
				close(rs);
		}
	}
	
	public void showArticle(Scanner sc) { //���� ������ ����ϱ�
		
		boolean stop = true;
		
		while (stop) {
			System.out.println("1. �۹�ȣ�� �˻�    2. �ۼ��ڷ� �˻�  ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1:
				System.out.println("�˻��� �۹�ȣ�� �Է��ϼ���.");
				int id = sc.nextInt();
				sc.nextLine();
				bv = getArticle(id);
				if (bv == null) {
					System.out.println("�� ��ȣ �ٽ� �Է�");
				}
				else{
					System.out.println(bv);
					stop = false;
				}
				break;
				
			case 2:
				System.out.println("�˻��� �ۼ��ڸ� �Է��ϼ���.");
//				showArticleList();
				String writer = sc.nextLine();
				ArrayList<BoardVO> getList = getArticle(writer);
				
				for (BoardVO List : getList) {
					System.out.println(List);
				}
				stop = false;

				break;

			default:
				System.out.println("��ȣ�� �ٽ� �Է��ϼ���");
				break;
			}
		}


	
	}
	
	private BoardVO getArticle(int id) { //��ȣ �޾Ƽ� �� ������
		
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		String sql = "SELECT * FROM BOARD WHERE ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			 if (rs.next()) {
				int dbid = rs.getInt("id");
				String writer = rs.getString("writer");
				String passwd = rs.getString("passwd");
				String subject = rs.getString("subject");
				String email = rs.getString("email");	
				
				bv = new BoardVO(dbid, writer, passwd, subject, email);
			}else {
				System.out.println("���� �۹�ȣ");
				bv = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
				close(rs);
		}
		
		return bv;

	}
	
	
	private ArrayList<BoardVO> getArticle(String writer) { //��ȣ �޾Ƽ� �� ������
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		String sql = "SELECT * FROM BOARD WHERE WRITER = ?";
		ArrayList<BoardVO> getList = new ArrayList<BoardVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { 

			int id = rs.getInt("id");
			writer = rs.getString("writer");
			String passwd = rs.getString("passwd");
			String subject = rs.getString("subject");
			String email = rs.getString("email");				
			BoardVO bv = new BoardVO(id, writer, passwd, subject, email);

			getList.add(bv);
			
			}



		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
				close(rs);
		}return getList;
		
	}
	
	public void deleteArticle(Scanner sc) {
		
		showArticleList();
		System.out.println("1. �۹�ȣ ����     2. �ۼ��� ����");
		int num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1:
			System.out.println("������ �۹�ȣ�� �Է��ϼ���.");
			int id = sc.nextInt();
			sc.nextLine();
			bv = deleteArticle(id);
			break;
		case 2:
			System.out.println("������ �ۼ��ڸ� �Է��ϼ���");
			String writer = sc.nextLine();
			bv = deleteArticle(writer);

			break;
		default:
			break;
		}

	}
	
	private BoardVO deleteArticle(int id) {
		
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		String sql = "DELETE FROM BOARD WHERE ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("���� ����");
				commit(con);
			}else {
				System.out.println("�۹�ȣ ���� ���� �ʽ�! ���� ����!");
				deleteArticle(sc);
				rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
		}
		return bv;
		
	}
	private BoardVO deleteArticle(String writer) {
		
		con = getConnection(); //db����, static import �ؼ� ��ü ������� �ʾƵ� ȣ�� ����
		String sql = "DELETE FROM BOARD WHERE WRITER = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("���� ����");
				commit(con);
				
			}else {
				
				System.out.println("�ش� �ۼ��ڰ� �������� �ʽ��ϴ�.���� ����!");
				deleteArticle(sc);
				rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
		}
		return bv;
		
	}

	public void updateArticle(Scanner sc) {
		
		showArticleList();
		System.out.println("������ �۹�ȣ�� �Է��ϼ���.");
		int id = sc.nextInt();
		sc.nextLine();
		getArticle(id);
		bv = updateArticle(id);
//		showArticleList();
		System.out.println(bv);
		
		
	}
	
	private BoardVO updateArticle(int id) {
		
		con = getConnection();
		String sql = null;
		
		try {
			System.out.println("������ �׸��� �����ϼ���");
			System.out.println("1. ����    2. ��й�ȣ     3. �̸���");
			int num = sc.nextInt();
			sc.nextLine();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				switch (num) {
				case 1:
					System.out.println("������ ����");
					String subject = sc.nextLine();
					bv.setSubject(subject);
					sql = "UPDATE BOARD SET SUBJECT = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, subject);			
					break;
					
				case 2:
					System.out.println("������ ��й�ȣ");
					String passwd = sc.nextLine();
					bv.setPasswd(passwd);
					sql = "UPDATE BOARD SET PASSWD = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, passwd);
					break;
					
				case 3:
					System.out.println("������ �̸���");
					String email = sc.nextLine();
					bv.setEmail(email);
					sql = "UPDATE BOARD SET EMAIL = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					break;

				default:
					System.out.println("�߸��� �׸��Դϴ�. �ٽ� �Է��ϼ���.");
					break;
				}
				pstmt.setInt(2, id);

				
				if (count > 0 ) {
					System.out.println("�����Ϸ�");				
					System.out.println("�������� Ȯ��");
					
					sql = "SELECT * FROM BOARD WHERE id = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();

					if (rs.next()) { 
						id = rs.getInt("id");
						String writer = rs.getString("writer");
						String passwd = rs.getString("passwd");
						String subject = rs.getString("subject");
						String email = rs.getString("email");	
						
						bv = new BoardVO(id, writer, passwd, subject, email);
						
					}
					commit(con);
				}else {
					System.out.println("�۹�ȣ ���� ���� �ʽ�! ���� ����!");
					deleteArticle(sc);
					rollback(con);
				}
			}else {
				System.out.println("�����;���!");
			}
			
		} catch (SQLException e) {
			System.out.println("���ٲ��!");
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return bv;
	
	}	
}




















