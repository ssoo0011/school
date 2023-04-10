package membership;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import DB.JDBConnect;

public class MemberDAO extends JDBConnect{
	
	public MemberDAO() {}
	
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM MEMBER WHERE ID = ? AND PASS = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, uid);
			pstmt.setString(2, upass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public MemberDTO insertMemberDTO(String uid, String upass, String name, String email, Date birth) {
		MemberDTO dto = new MemberDTO();
		String query = "INSERT INTO MEMBER VALUES(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, uid);
			pstmt.setString(2, upass);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setDate(5, birth);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public List<MemberDTO> getInforMemberDTO(String uid){
		
		List<MemberDTO>mbs = new Vector<MemberDTO>();
		String query = "select * from MEMBER WHERE ID = '" + uid + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));		
				
				mbs.add(dto);
					
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return mbs;
	}
	
}
