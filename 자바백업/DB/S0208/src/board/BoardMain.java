package board;

import java.util.HashMap;
import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		BoardSVC bs = new BoardSVC();
		boolean stop = true;
		HashMap<Integer, BoardVO> list = new HashMap<Integer, BoardVO>();
		
		while (stop) {
			System.out.println("  게시판");
			System.out.println("a.글 등록     b.글 목록보기     c.글 내용 보기     d. 글 삭제     e. 글 수정하기    "
					+ "f.종료");
			
			String al = scanner.nextLine();
			
			switch (al) {
			case "a":
				bs.writerArticle(scanner);
				break;
			case "b":
				bs.showArticleList();
				break;
			case "c":
				bs.showArticle(scanner);
				break;
			case "d":
				bs.deleteArticle(scanner);
				break;
			case "e":
				bs.updateArticle(scanner);
				break;
			case "f":
				System.out.println("시스템 종료!");
				stop = false;
				break;

			default:
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

}
