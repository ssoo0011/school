package blog;

import java.util.Scanner;

class Number{
	
	public void sum() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		int num = sc.nextInt();
		
		for(int i = 1; i <= num; i++) {
			System.out.print(i + "\t");
		}		
	}	
}

public class blog{
	public static void main(String[]args) {
		
		Number number = new Number();
		number.sum();

		
		
	}
}