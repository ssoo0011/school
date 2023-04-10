package board;

// 객체없이 사용 가능
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
	

	public BoardVO getBoardVO(Scanner sc) { //게시물 등록
		
		System.out.println("♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡  게시물 등록  ♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡");
		System.out.print("작성자 :");
		String writer = sc.nextLine();
		System.out.print("비밀번호 :");
		String passwd = sc.nextLine();
		System.out.print("제목 :");
		String subject = sc.nextLine();
		System.out.print("이메일 :");
		String email = sc.nextLine();

		bv = new BoardVO(0, writer, passwd, subject, email);
		return bv;
	}
	
	public void writerArticle(Scanner sc) {
		
		bv = getBoardVO(sc);
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
		
		try {
			String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bv.getWriter());
			pstmt.setString(2, bv.getPasswd());
			pstmt.setString(3, bv.getSubject());
			pstmt.setString(4, bv.getEmail());
			
			int count = pstmt.executeUpdate();

			if (count > 0) {

				System.out.println("게시글 작성 끗!!");
				commit(con);
			}else {
				System.out.println("안드갔는데용?ㄴ");
				rollback(con);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				//pstmt 닫는게 없어도 pstmt넣으면 업캐스팅 일어나서 pstmt도 닫을 수 있음!
				close(pstmt);
				close(con);
		}
	}
	
	public void showArticleList() {
		
		System.out.println("전체 글 목록");
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
		String sql = "SELECT * FROM BOARD";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
	              System.out.printf("글번호 : %d, 작성자 : %s, 제목 : %s, 이메일 : %s\n",
	    	              rs.getInt("id"),
	    	              rs.getString("writer"),
	    	              rs.getString("subject"),
	    	              rs.getString("email"));	
				while (rs.next()) { 

	              System.out.printf("글번호 : %d, 작성자 : %s, 제목 : %s, 이메일 : %s\n",
	    	              rs.getInt("id"),
	    	              rs.getString("writer"),
	    	              rs.getString("subject"),
	    	              rs.getString("email"));	
				}

			}else {
				System.out.println("데이터x");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
				close(rs);
		}
	}
	
	public void showArticle(Scanner sc) { //실제 데이터 출력하기
		
		boolean stop = true;
		
		while (stop) {
			System.out.println("1. 글번호로 검색    2. 작성자로 검색  ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1:
				System.out.println("검색할 글번호를 입력하세요.");
				int id = sc.nextInt();
				sc.nextLine();
				bv = getArticle(id);
				if (bv == null) {
					System.out.println("글 번호 다시 입력");
				}
				else{
					System.out.println(bv);
					stop = false;
				}
				break;
				
			case 2:
				System.out.println("검색할 작성자를 입력하세요.");
//				showArticleList();
				String writer = sc.nextLine();
				ArrayList<BoardVO> getList = getArticle(writer);
				
				for (BoardVO List : getList) {
					System.out.println(List);
				}
				stop = false;

				break;

			default:
				System.out.println("번호를 다시 입력하세요");
				break;
			}
		}


	
	}
	
	private BoardVO getArticle(int id) { //번호 받아서 값 들고오기
		
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
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
				System.out.println("없는 글번호");
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
	
	
	private ArrayList<BoardVO> getArticle(String writer) { //번호 받아서 값 들고오기
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
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
		System.out.println("1. 글번호 삭제     2. 작성자 삭제");
		int num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1:
			System.out.println("삭제할 글번호를 입력하세요.");
			int id = sc.nextInt();
			sc.nextLine();
			bv = deleteArticle(id);
			break;
		case 2:
			System.out.println("삭제할 작성자르 입력하세요");
			String writer = sc.nextLine();
			bv = deleteArticle(writer);

			break;
		default:
			break;
		}

	}
	
	private BoardVO deleteArticle(int id) {
		
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
		String sql = "DELETE FROM BOARD WHERE ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("삭제 성공");
				commit(con);
			}else {
				System.out.println("글번호 존재 하지 않슴! 삭제 실패!");
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
		
		con = getConnection(); //db연결, static import 해서 객체 사용하지 않아도 호출 가능
		String sql = "DELETE FROM BOARD WHERE WRITER = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("삭제 성공");
				commit(con);
				
			}else {
				
				System.out.println("해당 작성자가 존재하지 않습니다.삭제 실패!");
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
		System.out.println("수정할 글번호를 입력하세요.");
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
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1. 제목    2. 비밀번호     3. 이메일");
			int num = sc.nextInt();
			sc.nextLine();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				switch (num) {
				case 1:
					System.out.println("수정할 제목");
					String subject = sc.nextLine();
					bv.setSubject(subject);
					sql = "UPDATE BOARD SET SUBJECT = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, subject);			
					break;
					
				case 2:
					System.out.println("수정할 비밀번호");
					String passwd = sc.nextLine();
					bv.setPasswd(passwd);
					sql = "UPDATE BOARD SET PASSWD = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, passwd);
					break;
					
				case 3:
					System.out.println("수정할 이메일");
					String email = sc.nextLine();
					bv.setEmail(email);
					sql = "UPDATE BOARD SET EMAIL = ? WHERE ID = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					break;

				default:
					System.out.println("잘못된 항목입니다. 다시 입력하세요.");
					break;
				}
				pstmt.setInt(2, id);

				
				if (count > 0 ) {
					System.out.println("수정완료");				
					System.out.println("수정내용 확인");
					
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
					System.out.println("글번호 존재 하지 않슴! 삭제 실패!");
					deleteArticle(sc);
					rollback(con);
				}
			}else {
				System.out.println("데이터업슴!");
			}
			
		} catch (SQLException e) {
			System.out.println("못바꿨따!");
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




















