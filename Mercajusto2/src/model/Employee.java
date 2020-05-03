package model;

public class Employee implements Comparable{

	private String name;
	private String lastName;
	private int salary;
	private int phone;
	private String work;
	
	
	public Employee(String name, String lastName, int salary, int phone, String work) {
		this.name = name;
		this.lastName = lastName;
		this.salary = salary;
		this.phone = phone;
		this.work = work;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getWork() {
		return work;
	}


	public void setWork(String work) {
		this.work = work;
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
