package model;

import model.herencia.Product;

public class Nodo {

		public Product dato;
		Nodo next, prev;
		
		public Nodo(Product dat, Nodo nex, Nodo pre) {
			this.dato=dat;
			this.next=nex;
			this.prev=pre;
			
		}

		public Product getDato() {
			return dato;
		}

		public void setDato(Product dato) {
			this.dato = dato;
		}

		public Nodo getNext() {
			return next;
		}

		public void setNext(Nodo next) {
			this.next = next;
		}

		public Nodo getPrev() {
			return prev;
		}

		public void setPrev(Nodo prev) {
			this.prev = prev;
		}
		
		
		
}
