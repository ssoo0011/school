package Board;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;

import Board.BoardDTO;
import common.JDBConnect;
import management.JSFunction;

public class BoardDAO extends JDBConnect{
	
	
	//목록 보여주기
	public List<BoardDTO> ShowList(){ 
		List<BoardDTO> bList = new Vector<BoardDTO>();
		String query = "SELECT * FROM BOARD";
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setOfile(rs.getString(7));
				dto.setSfile(rs.getString(8));
				dto.setDowncount(rs.getInt(9));
				dto.setPass(rs.getString(10));
				dto.setVisitcount(rs.getInt(11));
				
				bList.add(dto);
			}
			
		}catch (Exception e) {
			System.out.println("리스트 못불러옴");
			e.printStackTrace();
		}
		return bList;
	}
	
	//게시물 작성~
	public void writeContent(HttpServletRequest req) {
		
		int result;
		BoardDTO dto;
		String query = "INSERT INTO BOARD (IDX, NAME, TITLE, CONTENT, OFILE, SFILE, DOWNCOUNT, PASS, VISITCOUNT)"+
				"VALUES (BOARDSEQ.NEXTVAL, ?, ?, ?, ?, ?, 0, ?, 0)";
		
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("ofile");
		String pass = req.getParameter("pass");
		
		System.out.println(name);
		
		dto = new BoardDTO(name, title, content, ofile, sfile, pass);
		try {
//			req.setCharacterEncoding("utf-8");
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getPass());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("게시물 작성 중 오류!");
			e.printStackTrace();
		}
	}
	
	//게시물 보기~~
	
	public List<BoardDTO> ShowCont(HttpServletRequest req){
		
		List<BoardDTO> bList = new Vector<BoardDTO>();
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		String query = "SELECT * FROM BOARD WHERE IDX = '" + idx+"'";
		BoardDTO dto = new BoardDTO();
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setOfile(rs.getString(7));
				dto.setSfile(rs.getString(8));
				dto.setDowncount(rs.getInt(9));
				dto.setPass(rs.getString(10));
				dto.setVisitcount(rs.getInt(11));
				
				bList.add(dto);
				
			}
			
		}catch (Exception e) {
			System.out.println("리스트 못불러옴");
			e.printStackTrace();
		}
		return bList;
	}
	
	//게시물 수정
	
	public void UpdateCont(HttpServletRequest req) {
		
		int result;
		String query = "UPDATE BOARD SET CONTENT = ?, TITLE = ? WHERE IDX = ?";
		
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		System.out.println(idx + title + content);
		
		ShowCont(req);
		
		try {
//			req.setCharacterEncoding("utf-8");
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, Integer.parseInt(idx));
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("게시물 수정 중 오류!");
			e.printStackTrace();
		}
	}
	
	public int DeleteCont(HttpServletRequest req) {
		
		String idx = req.getParameter("idx");
		String pass = req.getParameter("pass");
		int count = 0;
		
		try {
			stmt = con.createStatement();
			String query = "DELETE FROM BOARD WHERE IDX = '" + idx +"' AND PASS = '"+ pass + "'";
			 count = stmt.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("삭제 중 오류발생");
		}
		return count;
		
	}
}
