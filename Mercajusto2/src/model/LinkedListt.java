package model;

import model.herencia.Product;

public class LinkedListt  {
	    	
    private Nodo raiz;
    private Nodo tail;

    private int size;

    public LinkedListt () {
	    raiz=null;
	    size=0;
    }
      
    public void add(Product value) {
        Nodo node = new Nodo(value, null, null);
        if (tail == null && raiz == null) {
            tail = node;
            raiz = node;
        } else {
            node.setNext(raiz);
            raiz.setPrev(node);
            raiz = node;
        }
    }
    
  
    public void search(int code){
        if (raiz != null){
                   Nodo aux = raiz;
                 
                   int cont = 0;
                   while (aux != null){
                               if (aux.getDato().getCode() == code ){
                                           cont++;
                                           aux = aux.getNext();
                                         
                               }                                 
                   }                     
             }
        }

    
    public void remove(int code){ 	
    	
        if (raiz != null){
             Nodo aux = raiz;
             Nodo ant = null;
             while (aux != null){
                    if (aux.getDato().getCode() == code ){
                       if (ant == null){
                           raiz = raiz.getNext();
                           aux.setNext(null);
                           aux= raiz;
                                          
                           }else {
                                 ant.setNext(aux.getNext());
                                 aux.setNext(null);
                                 aux = ant.getNext();
                            }                                             
                            }else{
                                 ant = aux;
                                 aux = aux.getNext();
                            }
                   }
         }
}
    
    public Product get(int index) {
    	Product prductt = null;
    	Nodo start=raiz;
    	if(index<size) {
    		int cont=0;
    		while(cont<=index) {
    			cont++;
    			start=start.getNext();
    			if(cont==index) {
    				prductt=start.getDato();
    			}
    		}
    	}
    	
    	return prductt;
    }
    
    

    public boolean isEmpty() {
        if (raiz == null) {
            return true;
        }else {
            return false;
        }
    }
   
    public int size() {
    	return size;
    }


    

	}
	
