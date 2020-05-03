package model;

public class Account {

	private String username;
	private String password;
	private Employee employee;
	
	private Account prev;
	private Account next;
	
	public Account(String username, String password, Employee employee) {
		super();
		this.username = username;
		this.password = password;
		this.employee = employee;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Account getPrev() {
		return prev;
	}

	public void setPrev(Account prev) {
		this.prev = prev;
	}

	public Account getNext() {
		return next;
	}

	public void setNext(Account next) {
		this.next = next;
	}
	
	
	
	
	
}

