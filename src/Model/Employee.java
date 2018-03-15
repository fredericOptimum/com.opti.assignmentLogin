package Model;

public class Employee {
	private int id;
	private String name;
	private String nric;
	private String email;
	private String dob;
	private String role;
	private String mobile;
	private String password;
	private String status;
	private String firstLogin;
	private String SecAns;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecAns() {
		return SecAns;
	}

	public void setSecAns(String secAns) {
		SecAns = secAns;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	@Override
	public String toString() {
		return "Student [SerialNo=" + id + ", Name=" + name
				+ ", Nric=" + nric + ", Email=" + email + ", DOB="
				+ dob + ", Mobile=" + mobile +", NoofAttempts=" + firstLogin+"]";
	}
}
