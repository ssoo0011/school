package S0207;
import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		boolean isStop = false;
		LoginSVC loginSVC = new LoginSVC();
		
		do {
			System.out.println("�١ڷα��� ȭ���Դϴ�~�١�");
			System.out.println("���̵�, ��й�ȣ�� �Է��ϼ���");
			System.out.print("���̵�: ");
			String id = sc.next();
			System.out.print("��й�ȣ : ");
			String passwd = sc.next();
			
			UserEx user = loginSVC.login(id, passwd);
			if(user == null) { //��ȸ�� ���� ���� ��
				System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}else {
				System.out.println("�α����� ������� ����");
				System.out.println(user);
				isStop = true;
				
			}
		} while (!isStop);
		
	}

}
