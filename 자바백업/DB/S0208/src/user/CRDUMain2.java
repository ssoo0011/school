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
			System.out.println("�١ڡ١ڡ١ڡ١ڡ١�ȸ�� ���� ���� �ý����Ӵϴ�~�١ڡ١ڡ١ڡ١ڡ١�");
			System.out.println("�١ڷα��� ȭ���Դϴ�~�١�");
//			System.out.println("���̵�, ��й�ȣ�� �Է��ϼ���");
//			System.out.print("���̵�: ");
//			String id = scanner.next();
//			System.out.print("��й�ȣ : ");
//			String passwd = scanner.next();
			
			User2 user = ct.login("ssoo0011", "12345");
//			User user = ct.login(id, passwd);
			
//			if (id.equals("ssoo0011")) {
//				System.out.println("������ ���̵�� �α����ϼ̽��ϴ�.");
//			}
			
			if(user != null) {
							
				System.out.println("�޴� ��ȣ�� �����ϼ���");
				System.out.println("1. ȸ�� ����    2. ȸ�� ����    3. ȸ����ȸ    4. ȸ������     5. ��üȸ����ȸ");
				System.out.println("�׸��Ͻ÷��� '-1'�� �Է��ϼ���.");
				
				int num = scanner.nextInt();
				scanner.nextLine();
			
				switch (num) {
				case 1:
					ct.insert();		
					break;
					
				case 2:
//					System.out.println("�����Ͻ� ȸ���� ���̵� �Է��ϼ���");
//					String updateId = scanner.nextLine();
//					ct.update(updateId);
					ct.update("ssoo0011");
					break;
					
				case 3:	
					System.out.println("��ȸ�Ͻ� ȸ���� ���̵� �Է��ϼ���");
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
				System.out.println("���̵� �Ǵ� ��й�ȣ ����");
				isStop = true;
				break;
			} 
		}while(!isStop);
		
		
	}

}
