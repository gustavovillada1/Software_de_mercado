package model;

import java.io.Serializable;


import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class Account implements Serializable{

	private String username;
	private String password;
	private Employee employee;
	private String photo;
	
	private Account prev;
	private Account next;
	
	public Account(String username, String password, Employee employee, String photo) {
		super();
		this.username = username;
		this.password = password;
		this.employee = employee;
		this.photo = photo;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getFigurePhoto() {
		String a[]=photo.split(",");
		
		return a[0];
		
	}
	
	public Paint getColorPhoto() {
		String a[]=photo.split(",");
		Paint color=Color.WHITE;

		if(a[1].equals("ROJO")) {
			color=Color.RED;
		}else if(a[1].equals("AZUL")) {
			color=Color.BLUE;
		}else if(a[1].equals("AMARILLO")) {
			color=Color.YELLOW;
		}else if(a[1].equals("VERDE")) {
			color=Color.GREEN;
		}
		
		return color;
	}
	
	
	
}

