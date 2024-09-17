public class BankAccount {

	private String name;
	private String PPS;
	private String email;
	private String password;
	private String address;
	private double balance;

	public BankAccount(String name, String PPS, String email, String password, String address, double balance) {
		this.name = name;
		this.PPS = PPS;
		this.email = email;
		this.password = password;
		this.address = address;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [name=" + name + ", PPS=" + PPS + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", balance=" + balance + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPPS() {
		return PPS;
	}

	public void setPPS(String pPS) {
		PPS = pPS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
