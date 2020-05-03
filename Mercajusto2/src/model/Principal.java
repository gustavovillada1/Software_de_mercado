package model;

import java.util.ArrayList;

import model.herencia.Product;
import model.herencia.ProductCleaning;
import model.herencia.ProductDrinks;
import model.herencia.ProductFoods;
import model.herencia.ProductFoodsFruits;

public class Principal {
	
	private ArrayList<Employee> employees;
	private DoubleLinkedCircularAccounts accounts;
	private ArrayList<Product> productsInventary;
	
	private String messageGerente;
	
	private ArrayList<Product> clientProducts;
	
	public Principal(ArrayList<Employee> e,DoubleLinkedCircularAccounts a) {
		employees=e;
		accounts=a;
		productsInventary=new ArrayList<Product>();
		messageGerente="MERCAJUSTO, DONDE MERCAS A PRECIO JUSTO.!";
		
	}

	///SECCIÓN CAJERO////
	
	
	
	
	
	
	
	///FIN SECCIÓN CAJERO///
	
	///SECCIÓN INVENTARIO////
	public void addProductsInventary(int cantidadx,String type,String type1, int code, String name, int precio, String medida, 
			String tipoFruta,int porcentajeLimpieza, String polvoliquido, int percentFruit, int percentSuggar) {
		System.out.println("Entra");
		Product p = null;
		for(int i=0;i<cantidadx;i++) {
		if(type1.equals("OTROS")) {
			 p=new ProductFoods(code, name, precio, medida);
			productsInventary.add(p);
		}else if(type1.equals("FRUTA")){
			 p=new ProductFoodsFruits(code, name, precio, medida, tipoFruta);
			productsInventary.add(p);
		}else if(type.equals("LIMPIEZA")) {
			 p=new ProductCleaning(code, name, precio, medida, porcentajeLimpieza, polvoliquido);
			productsInventary.add(p);
		}else if(type.equals("BEBIDA")) {
			 p=new ProductDrinks(code, name, precio, medida, percentFruit, percentSuggar);
			productsInventary.add(p);
		}
	}
	
	}
	
	
	
	
	
	
	///FIN SECCIÓN INVENTARIO///
	
	
	
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public DoubleLinkedCircularAccounts getAccounts() {
		return accounts;
	}

	public void setAccounts(DoubleLinkedCircularAccounts accounts) {
		this.accounts = accounts;
	}

	public ArrayList<Product> getProductsInventary() {
		return productsInventary;
	}

	public void setProductsInventary(ArrayList<Product> productsInventary) {
		this.productsInventary = productsInventary;
	}

	public String getMessageGerente() {
		return messageGerente;
	}

	public void setMessageGerente(String messageGerente) {
		this.messageGerente = messageGerente;
	}

	public ArrayList<Product> getClientProducts() {
		return clientProducts;
	}

	public void setClientProducts(ArrayList<Product> clientProducts) {
		this.clientProducts = clientProducts;
	}
	
	
	
}
