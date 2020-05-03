package model;

public class DoubleLinkedCircularAccounts {
	private Account first;
	private int size;
	
	public DoubleLinkedCircularAccounts() {
		size=0;
		first = null;
	}

	public void add(Account ac) {

		Account s = ac;
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
	
	public Account get(int n) {
		Account current=first;
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

	public Account getFirst() {
		return first;
	}

	public void setFirst(Account first) {
		this.first = first;
	}
	

	
	
	
	
}
