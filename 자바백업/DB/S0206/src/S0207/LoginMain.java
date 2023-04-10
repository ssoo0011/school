package S0207;
import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		boolean isStop = false;
		LoginSVC loginSVC = new LoginSVC();
		
		do {
			System.out.println("☆★로그인 화면입니다~☆★");
			System.out.println("아이디, 비밀번호를 입력하세요");
			System.out.print("아이디: ");
			String id = sc.next();
			System.out.print("비밀번호 : ");
			String passwd = sc.next();
			
			UserEx user = loginSVC.login(id, passwd);
			if(user == null) { //조회된 값이 없을 때
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			}else {
				System.out.println("로그인한 사용자의 정보");
				System.out.println(user);
				isStop = true;
				
			}
		} while (!isStop);
		
	}

}
