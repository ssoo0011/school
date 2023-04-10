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

@WebServlet("/MVCBoard/view.do")
public class ViewController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			
		req.setCharacterEncoding("utf-8");
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> bList = dao.ShowCont(req);
		req.setAttribute("List", bList);
		
		req.getRequestDispatcher("/MVCBoard/View.jsp").forward(req,  resp);

		
		dao.close();
	}
}
