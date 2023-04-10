package MyFile;

import java.util.List;
import java.util.Vector;
import DB.DBConnPool;

public class FileDAO extends DBConnPool{
	public int insertFile(FileDTO dto) {
		int applyResult = 0;
		
		try {
			String query = "INSERT INTO MYFILE ("
					+ "IDX, NAME, TITLE, CATE, OFILE, SFILE) "
					+" VALUES ( BOARDSEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			
			applyResult = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("insert 중 예외");
			e.printStackTrace();
		}
		return applyResult;
	}
	
	public List<FileDTO> myFileList(){
		List<FileDTO> fileList = new Vector<FileDTO>();
		String query = "SELECT * FROM MYFILE ORDER BY IDX DESC";
		
		try {
			pstmt= con.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				FileDTO dto = new FileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				
				fileList.add(dto);
			}
			
		}catch (Exception e) {
			System.out.println("불러오는 중 오류 발생");
			e.printStackTrace();
		}
		return fileList;
	}
}
