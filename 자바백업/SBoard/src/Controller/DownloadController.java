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

@WebServlet("/MVCBoard/down.do")
public class DownloadController extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			
		req.setCharacterEncoding("utf-8");
		FileUtil fu = new FileUtil();
		fu.DownFile(req);
		
		System.out.println(req.getParameter("idx"));
		

		req.getRequestDispatcher("/MVCBoard/View.jsp").forward(req,  resp);
		
	}
}
