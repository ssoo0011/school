package membership;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String email;	
	private java.sql.Date birth;
	
	public MemberDTO() {

	}
	public MemberDTO(String id, String pass, String name, String email, Date birth) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}
