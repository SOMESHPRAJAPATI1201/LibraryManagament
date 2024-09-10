package dto;

public class StudentDTO {

	private String name;
	private int id;
	private String email;
	private String password;
	private String role;
	private String membership_no;
	
	public String getMembership_no() {
		return membership_no;
	}

	public void setMembership_no(String membership_no) {
		this.membership_no = membership_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
