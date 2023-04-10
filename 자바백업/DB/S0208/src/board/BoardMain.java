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
			System.out.println("  �Խ���");
			System.out.println("a.�� ���     b.�� ��Ϻ���     c.�� ���� ����     d. �� ����     e. �� �����ϱ�    "
					+ "f.����");
			
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
				System.out.println("�ý��� ����!");
				stop = false;
				break;

			default:
				System.out.println("�ٽ� �Է����ּ���");
				break;
			}
		}
	}

}
