package model;

import java.io.Serializable;

import model.herencia.Product;

public class DoubleLinkedCircularClientsProducts implements Serializable{
	private Product first;
	private int size;
	
	public DoubleLinkedCircularClientsProducts() {
		size=0;
		first = null;
	}

	public void add(Product ac) {

		Product s = ac;
		if(first==null) {
			first = s;
			s.setPrev(s);
			s.setNext(s);
			size++;
		}else {
			first.getPrev().setNext(s);
			s.setPrev(first.getPrev());
			s.setNext(first);
			first.setPrev(s);
			size++;
		}
	}
	
	public Product get(int n) {
		Product current=first;
		for(int i=0;i<=n;i++) {
			current=current.getNext();
		}
		return current;
	}

	public boolean isEmpty() {
		boolean r;
		if(first==null) {
			r=true;
		}else {
			r=false;
		}
		return r;
	}
	
	
	
	
	public int size() {
		return size;
	}

	public Product getFirst() {
		return first;
	}

	public void setFirst(Product first) {
		this.first = first;
	}
	

	
	
	
	
}
