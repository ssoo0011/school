package user;

public class User2 {


	private String id;
	private String passwd;
	private String name;
	private int age;
	private String gender;
	private String email;
	
	public User2() {

	}
	
	
	public User2(String id, String passwd, String name, int age, String gender, String email) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return String.format("아이디gg : %s\t, 비밀번호 :  %s\t, 이름 : %s\n, 나이 : %d\t, 성별 : %s\t, 이메일 : %s\t",
				id, passwd, name, age, gender, email);
	}
}
