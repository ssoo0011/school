package Controller;

import java.io.IOException;
import management.JSFunction; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import Board.BoardDAO;
import Board.BoardDTO;

@WebServlet("/MVCBoard/pass.do")
public class PassController extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("utf-8");	
		BoardDAO dao = new BoardDAO();
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		EditController ec = new EditController();
		
		List<BoardDTO> bList = dao.ShowCont(req);
		req.setAttribute("List", bList);
		
		dao.ShowCont(req);
		req.getRequestDispatcher("/MVCBoard/Edit.jsp").forward(req,  resp);

		
		
		dao.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("utf-8");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		BoardDAO dao = new BoardDAO();
		EditController ec = new EditController();
		List<BoardDTO> bList = dao.ShowCont(req);
		System.out.println(mode);
		System.out.println(pass);
		
		if(mode.equals("edit")) {
			
			ec.doPost(req, resp);
			
			
		}else if(mode.equals("delete")) {
			
			int count = dao.DeleteCont(req);
			
			if(count >0) {
				req.getRequestDispatcher("/MVCBoard/List.jsp").forward(req,  resp);
			}
			else {
				req.getRequestDispatcher("/MVCBoard/Pass.jsp").forward(req,  resp);
			}
			dao.ShowCont(req);
		}

		
		dao.close();
		
		
	}
}
