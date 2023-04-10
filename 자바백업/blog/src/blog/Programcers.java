package blog;

public class Programcers {

	public static void main(String[] args) {
		String []eng = {"zero", "one", "two", "three", "four",
				"five", "six", "seven", "eight", "nine"};
		
		
		String str = "one";
		StringBuilder sb = new StringBuilder(str);
		for(int i = 0; i < eng.length; i++) {
			if(str.contains(eng[i])) {
				sb.replace(str.indexOf(eng[i]),str.indexOf(eng[i])+1 , String.valueOf(i));
			}
		}
		char[] result = sb.toString().toCharArray();
		String s = "";
		
		for(char a : result) {
			if(a < 58 && a> 47) {
				s+=a;
			}
		}
		System.out.println(s);
	}

}
 