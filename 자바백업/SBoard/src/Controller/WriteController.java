package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.BoardDAO;
import Board.BoardDTO;
import fileupload.FileUtil;

@WebServlet("/MVCBoard/write.do")
public class WriteController extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			
		req.setCharacterEncoding("utf-8");
		
		BoardDAO dao = new BoardDAO();
		FileUtil fu = new FileUtil();
		BoardDTO dto =  fu.UpFile(req);
		
		dao.writeContent(req, dto);
		req.getRequestDispatcher("/MVCBoard/List.jsp").forward(req,  resp);

		dao.close();
	}

}
