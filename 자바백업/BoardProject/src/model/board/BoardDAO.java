package model.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common1.JDBConnect2;

public class BoardDAO extends JDBConnect2{
	
	public BoardDAO(ServletContext application) {
		super(application);
		
	}
	
	//전체 게시물 개수 
	public int selectCount(Map<String, Object> map) {
		int totalcount = 0;
		
		String query = "select count(*) from board";
		if(map.get("searchWord")!=null) {
			query += " where "+ map.get("searchField") + " "
		    	  + " like '%" + map.get("searchWord") + "%'";

		}
		
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(query);
			rs.next();
			totalcount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("개시물 개수 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalcount; //받아온 행의 개수
	}
	
	
	public List<BoardDTO> selectList(Map<String, Object>map){
		
		List<BoardDTO>bbs = new Vector<BoardDTO>();
		
		String query = "select * from board ";
		if(map.get("searchWord")!=null) {
		       query +=" WHERE "+ map.get("searchField") + " "
		    	     + " LIKE '%" + map.get("searchWord") + "%' ";

		}
		query += "ORDER BY POSTDATE DESC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
					
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	public int insertWrite(BoardDTO dto){
		
		int result = 0;
		
		try {
			String qurey = "INSERT INTO BOARD ("
					+ "num, title, content, id, visitcount)"
					+ "VALUES(BOARDSEQ.NEXTVAL, ?, ?, ?, 0)";
			
			pstmt = con.prepareStatement(qurey);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("게시물 등록 중 실패");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public void updateVisitCount(String num) {

		BoardDTO dto = new BoardDTO();
		String qurey = "UPDATE BOARD SET VISITCOUNT = VISITCOUNT +1 WHERE NUM =" + num;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(qurey);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public BoardDTO selectView(String num) {
		
		BoardDTO dto = new BoardDTO();
		
		String query = "SELECT B.*, M.name "
				+ " FROM MEMBER M INNER JOIN BOARD B "
				+ " on M.id = B.id "
				+ " where num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getString("num"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setTitle(rs.getString("title"));
				dto.setVisitcount(rs.getString("Visitcount"));
			}
		} catch (Exception e) {
			System.out.println("글 불러오기 실패");
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public int updateEdit(BoardDTO dto) {
		
		int result = 0;
		try {
			String query = "UPDATE board SET title = ? , content = ?"
						+ " WHERE num = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getNum());
			
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			System.out.println("수정하는 데에서 오류");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deletePost(String num) {
		
		int result = 0;
		System.out.println(num);
		try {
			String query = "DELETE FROM BOARD WHERE NUM = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, num);

			
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			System.out.println("삭제하는 데에서 오류");
			e.printStackTrace();
		}
		
		return result;
		
	}
}










