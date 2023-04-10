package user;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class CRDUMain2 {

	public static void main(String[] args) {

		boolean isStop = false;
		Scanner scanner = new Scanner(System.in);
		CRUDTest2 ct = new CRUDTest2();
		List<User2> list = new Vector<User2>();

		do {
			System.out.println("☆★☆★☆★☆★☆★회원 정보 관리 시스템임니다~☆★☆★☆★☆★☆★");
			System.out.println("☆★로그인 화면입니다~☆★");
//			System.out.println("아이디, 비밀번호를 입력하세요");
//			System.out.print("아이디: ");
//			String id = scanner.next();
//			System.out.print("비밀번호 : ");
//			String passwd = scanner.next();
			
			User2 user = ct.login("ssoo0011", "12345");
//			User user = ct.login(id, passwd);
			
//			if (id.equals("ssoo0011")) {
//				System.out.println("관리자 아이디로 로그인하셨습니다.");
//			}
			
			if(user != null) {
							
				System.out.println("메뉴 번호를 선택하세요");
				System.out.println("1. 회원 삽입    2. 회원 수정    3. 회원조회    4. 회원삭제     5. 전체회원조회");
				System.out.println("그만하시려면 '-1'을 입력하세요.");
				
				int num = scanner.nextInt();
				scanner.nextLine();
			
				switch (num) {
				case 1:
					ct.insert();		
					break;
					
				case 2:
//					System.out.println("수정하실 회원의 아이디를 입력하세요");
//					String updateId = scanner.nextLine();
//					ct.update(updateId);
					ct.update("ssoo0011");
					break;
					
				case 3:	
					System.out.println("조회하실 회원의 아이디를 입력하세요");
					String searchId = scanner.nextLine();				
					user =  ct.select(searchId);
					System.out.println(user);			
					break;
					
				case 4:
					ct.delete();
					break;
					
				case 5:
					list = ct.select();
					for(User2 i : list) {
						System.out.println(i);
					}
					break;

				default:
					isStop = true;
					break;
				}
				
				
			}else{
				System.out.println("아이디 또는 비밀번호 오류");
				isStop = true;
				break;
			} 
		}while(!isStop);
		
		
	}

}
