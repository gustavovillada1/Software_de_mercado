package model;

import java.util.Comparator;

import model.herencia.Product;

public class CompararProductCode implements Comparator<Product>{
	
	@Override
	public int compare(Product p1, Product p2) {
		if(Integer.compare(p1.getCode(), p2.getCode())<0)
			return -1; 
		else {
			if(Integer.compare(p1.getCode(), p2.getCode())>0)
				return 1;
			else
				return 0; 
		}
	}

}

