package Controller;
import java.io.IOException;
import Board.BoardDTO;
import Board.BoardDAO;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MVCBoard/list.do")

public class ListController extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> bList = dao.ShowList();

		req.setAttribute("List", bList);
		req.getRequestDispatcher("/MVCBoard/List.jsp").forward(req,  resp);
		dao.close();
	}
	
}
