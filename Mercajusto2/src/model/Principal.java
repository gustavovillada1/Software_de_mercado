package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.herencia.Product;
import model.herencia.ProductCleaning;
import model.herencia.ProductDrinks;
import model.herencia.ProductFoods;
import model.herencia.ProductFoodsFruits;

/**
 * 
 * @author Gustavo Villada
 *
 */

public class Principal implements Serializable{
	
	private Account currentAccount;
	private ArrayList<Employee> employees;
	private DoubleLinkedCircularAccounts accounts;
	private ArrayList<Product> productsInventary;
	private String messageGerente;
	private int numberFactura;
	private ArrayList<Product> clientProducts;
	
	
	/**
	 * Método constructor de la clase Principal.
	 * @param e ArrayList que contendrá a los empleados de la empresa.
	 * @param a DoubleLinkedCircularAccouns que contendrá las cuentas de acceso de los empleados al programa.
	 */
	public Principal(ArrayList<Employee> e,DoubleLinkedCircularAccounts a) {
		numberFactura=12532781;
		currentAccount=new Account(null, null, null,null);
		employees=e;
		accounts=a;
		productsInventary=new ArrayList<Product>();
		clientProducts=new ArrayList<Product>();
		messageGerente="MERCAJUSTO, DONDE MERCAS A PRECIO JUSTO.!";
		
	}

	

	
	///SECCIÓN CAJERO////





	/**
	 * Metodo encargado de generar la factura de venta al momento de vender productos  a un cliente.
	 * @param clientName El nombre del cliente que hace la compra(opcional).
	 * @param id Identificación del cliente que realiza la compra.
	 * @param cambio La cantidad de dinero sobrante que hay que devolverle al cliente despues de este haber pagado.
	 * @param total La cantidad de dinero que costaron los productos que se lleva el cliente.
	 * @param pay La cantidad de dinero que paga el cliente.
	 * @throws IOException 
	 */
	public void generateFacture(String clientName, String id,String cambio ,String total, String pay) throws IOException {
		this.numberFactura+=37219;
		DoubleLinkedCircularClientsProducts clientsProductsLinkedList=new DoubleLinkedCircularClientsProducts();		
		for(int i=0;i<clientProducts.size();i++) {
			clientsProductsLinkedList.add(clientProducts.get(i));
		}
		
		
		PrintWriter pw1=new PrintWriter("factures/facture"+numberFactura+".txt");
		pw1.println("----------------------------------------");
		pw1.println("=========== MERCAJUSTO S.A =============");
		pw1.println("----------------------------------------");
		pw1.println("    Factura de venta No°: "+numberFactura);
		pw1.println("  Teléfono: 243 213 542 - 310 772 4713");
		pw1.println(" Dirección: Cra 76: #45-21 Valle de lili");
		pw1.println("----------------------------------------");
		pw1.println("Comprador: "+clientName+"   ID: "+id);
		pw1.println("========================================");
		pw1.println();
		pw1.println("PAGO: $ "+pay);
		pw1.println("CAMBIO: $ "+cambio);
		pw1.println("----------------------------------------");
		for(int i=0;i<clientProducts.size();i++) {
			pw1.println(clientsProductsLinkedList.get(i).getName()+"\t\t $ "+clientsProductsLinkedList.get(i).getPrecio());				
		}
		pw1.println("________________________________________");
		pw1.println("           TOTAL: "+"\t $ "+total);
		pw1.println();
		pw1.println("============== MERCAJUSTO ==============");
		pw1.println("===== Todos los derechos reservados ====");
		pw1.println("========================================");		
		pw1.close();
		
	}
	
	
	/**
	 * Metodo encargado de eliminar los productos recursivamente.
	 * @param code
	 * @param index
	 * @param q
	 * @return
	 */
	public int deleteProducts(int code,int index,int q) {
		int productsWithThisCode=0;		
		if(index==0) {
			if(code==clientProducts.get(index).getCode()) {
				return 1;
			}else {
			return 0;
			}
		}else if(index>0){
			if(code==clientProducts.get(index).getCode()) {
			productsWithThisCode=productsWithThisCode+deleteProducts(code, index-1,q);
			if(productsWithThisCode<=q) {
			productsWithThisCode+=1;
			clientProducts.remove(index);
			}
			}else {
				productsWithThisCode=productsWithThisCode+deleteProducts(code, index-1,q);
			}			
		}			
		return productsWithThisCode;		
	}
	
	/**
	 * Metodo encargado de eliminar un producto de los productos cargados al cliente.
	 * @param code Codigo del producto que se desea eliminar de la cuenta del cliente.
	 * @param q Cantidad de productos de este tipo que se desean eliminar.
	 * @return	Cantidad de elementos eliminados, en caso de que se quiera eliminar más productos de los que hay.
	 */
	public int deleteProduct(int code, int q) {
		int r=0;
		for(int i=clientProducts.size()-1;i>=0;i--) {
			if(r<q) {
			if(clientProducts.get(i).getCode()==code) {
				clientProducts.remove(i);
				r++;
				}
			}		
		}
		return r;
	}
	
	/**
	 * Metodo encargado de mostrar el precio de un producto según el codigo que este tenga asignado
	 * , para luego tomar este valor y multiplicarlo por la cantidad de elementos con un mismo codigo
	 * eliminados.
	 * @param code Codigo del producto a encontrar.
	 * @return El precio del producto el cual su código entró como parametro.
	 */
	public int showPriceByCode(int code) {
		int price=0;
		for(int i=0;i<clientProducts.size();i++) {
			if(clientProducts.get(i).getCode()==code) {
				return clientProducts.get(i).getPrecio();
			}
		}			
		return price;
		
	}
	
	
	
	///FIN SECCIÓN CAJERO///
	
	///SECCIÓN INVENTARIO////
	
	/**
	 * Metodo encargado de agregar productos al inventario, entran como parametros todos los valores posibles
	 * y luego se categorizan de acuerdo al tipo de Producto que sean (HERENCIA).
	 * @param cantidadx
	 * @param type
	 * @param type1
	 * @param code
	 * @param name
	 * @param precio
	 * @param medida
	 * @param tipoFruta
	 * @param porcentajeLimpieza
	 * @param polvoliquido
	 * @param percentFruit
	 * @param percentSuggar
	 */
	public void addProductsInventary(int cantidadx,String type,String type1, int code, String name, int precio, String medida, 
			String tipoFruta,int porcentajeLimpieza, String polvoliquido, int percentFruit, int percentSuggar) {
		Product p = null;
		for(int i=0;i<cantidadx;i++) {
		if(type1.equals("OTRO")) {
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
	

	/**
	 * Metodo encargado de llamar a otro metodo auxiliar para eliminar cierta cantidad de productos con un
	 * mismo codigo.
	 * @param code El codigo del producto a eliminar.
	 * @param cantidad Cantidad de productos a eliminar.
	 * @return False si no se ha eliminado algun elemento.
	 */
	public boolean deleteProductInventary(int code,int cantidad) {
		boolean delete = false;
		for(int i=0;i<cantidad;i++) {
		 delete=searchInventaryBinary(code);	
		}		
		return delete;
	}
	
	/**
	 * Metodo encargado de sumar recursivamente todos los precios de los productos que tenga un cliente en el carrito.
	 * @param index
	 * @return 
	 */
	public int sumarPreciosCarrito(int index) {
		int r=0;

		if(clientProducts.isEmpty()==false) {
		if(index==clientProducts.size()) {
			return 0;
			
		}else {
			r=clientProducts.get(index).getPrecio()+sumarPreciosCarrito(index+1);
		}
		}
		return r;
	}
	
	
	///FIN SECCIÓN INVENTARIO///
	

	/**
	 * Metodo encargado de realizar una busqueda binaria en los productos del inventario y luego eliminarlo, 
	 * tendrá como criterios de busqueda el codigo del producto.
	 * @param code Codigo del producto a buscar y eliminar.
	 * @return True si se ha eliminado el producto.
	 */
		public boolean searchInventaryBinary( int code ){
			Collections.sort(productsInventary, new CompararProductCode());

		
		    boolean encontre = false;    
			int inicio = 0;
		    int fin = productsInventary.size() - 1;
		    while( inicio <= fin && !encontre ){
		        int medio = ( inicio + fin ) / 2;
		        if( productsInventary.get(medio).getCode() == code ){
		            encontre = true;        
		            productsInventary.remove(medio);
		            
			}else if( productsInventary.get(medio).getCode() > code ){
		            fin = medio - 1;        
			}else {
		            inicio = medio + 1;  
			}    
			}
		    return encontre; 

		}
		

		

		/**
		 * Metodo encargado de realizar una busqueda binaria en los Empleados de la empresa y luego eliminar
		 * al empleado encontrado, tendrá como criterios de busqueda el apellido del empleado..
		 * @param lastName Apellido del empleado que se desea eliminar de la base de datos.
		 * @return True si se ha eliminado el empleado del sistema..
		 */
		public boolean searchEmployeeNameBinary( String lastName ){
			Collections.sort(employees,new CompararPorApellido());

	
		    boolean encontre = false;    
			int inicio = 0;
		    int fin = employees.size() - 1;
		    while( inicio <= fin && !encontre ){
		        int medio = ( inicio + fin ) / 2;
		        if( employees.get(medio).getLastName().equals(lastName )){
		            encontre = true;     
		            employees.remove(medio);
			}else if( employees.get(medio).getLastName().compareTo( lastName )>0){
		            fin = medio - 1;        
			}else {
		            inicio = medio + 1;  
			}    
			}
		    return encontre; 
	
		}
	
	
	
	///INICIO SECCIÓN GERENTE///
	
		/**
		 * Metodo encargado de generar los reportes de salario de los empleados, tendrá 3 metodos auxiliares,
		 * los cuales generaran un reporte utilizando metodos de ordenamiento.
		 * @throws FileNotFoundException
		 */
	public void generateReportSalary() throws FileNotFoundException {
		generateReportSalaryBurbuja();
		generateReportSalaryInsertion();
		generateReportSalarySelection();
		
	}
	
	/**
	 * Metodo encargado de generar el reporte del salario de los empleados y ordenarlos mediante el algoritmo
	 * de ordenamiento Burbuja.
	 * @throws FileNotFoundException
	 */
	private void generateReportSalaryBurbuja() throws FileNotFoundException {
		PrintWriter pw1=new PrintWriter("reportSalary/reportBurbuja.txt");

		    Employee[] arreglo = new Employee[employees.size()];
		    for(int i=0;i<employees.size();i++) {
		    	arreglo[i]=employees.get(i);
		    }
		    
		    for( int i = arreglo.length; i > 0; i-- ){
		        for( int j = 0; j < i - 1; j++ ){
		            if( arreglo[ j ].getSalary() > arreglo[ j + 1 ].getSalary() ){
		            	Employee temp = arreglo[ j ]; 
		               	arreglo[ j ] = arreglo[ j + 1 ];
		                arreglo[ j + 1 ] = temp;      
		                }else if(arreglo[ j ].getSalary()== arreglo[ j + 1 ].getSalary()){
		              
		                	 if( arreglo[ j ].getName().compareTo(arreglo[ j + 1 ].getName() )>0){
		 		            	Employee temp = arreglo[ j ]; 
		 		               	arreglo[ j ] = arreglo[ j + 1 ];
		 		                arreglo[ j + 1 ] = temp;   
		 		                }
		                	
		                }
		            }
		        }
		
		
		pw1.println("----------REPORTE SALARIOS DE EMPLEADOS POR BURBUJA-----");
		pw1.println();

		int totalSalary=0;
		for(int i=0;i<arreglo.length;i++) {
			Employee e=arreglo[i];
			totalSalary+=e.getSalary();
			pw1.println(e.getName()+"\t\t "+e.getWork()+"\t "+e.getSalary());				
		}
		pw1.println("--------------------------------------------------------");
		pw1.println("TOTAL SALARIOS: \t\t $ "+totalSalary);
		pw1.close();
	}
	
	
	/**
	 * Metodo encargado de generar el reporte del salario de los empleados y ordenarlos mediante el algoritmo
	 * de ordenamiento Insercioń.
	 * @throws FileNotFoundException
	 */
	private void generateReportSalaryInsertion() throws FileNotFoundException {
		PrintWriter pw1=new PrintWriter("reportSalary/reportInsertion.txt");

	    Employee[] arreglo = new Employee[employees.size()];
	    for(int i=0;i<employees.size();i++) {
	    	arreglo[i]=employees.get(i);
	    }
	    
	    for( int i = 1; i <arreglo.length; i++ ){
	        for( int j = i; j > 0 ; j-- ){ 
	        	if(arreglo[ j - 1 ].getSalary() > arreglo[ j ].getSalary()) {
	           Employee temp = arreglo[ j ];
	            arreglo[ j ] = arreglo[ j - 1 ];
	            arreglo[ j - 1 ] = temp;        
	        	}else if(arreglo[ j - 1 ].getSalary() == arreglo[ j ].getSalary()) {
	 	           Employee temp = arreglo[ j ];
		            arreglo[ j ] = arreglo[ j - 1 ];
		            arreglo[ j - 1 ] = temp;  
	        	}
			}    
		}
	
	
	pw1.println("----------REPORTE SALARIOS DE EMPLEADOS POR INSERCIÓN-----");
	pw1.println();

	int totalSalary=0;
	for(int i=0;i<arreglo.length;i++) {
		Employee e=arreglo[i];
		totalSalary+=e.getSalary();
		pw1.println(e.getName()+"\t\t "+e.getWork()+"\t "+e.getSalary());				
	}
	pw1.println("--------------------------------------------------------");
	pw1.println("TOTAL SALARIOS: \t\t $ "+totalSalary);
	pw1.close();
	}
	
	
	/**
	 * Metodo encargado de generar el reporte del salario de los empleados y ordenarlos mediante el algoritmo
	 * de ordenamiento Selection.
	 * @throws FileNotFoundException
	 */
	private void generateReportSalarySelection() throws FileNotFoundException {
		PrintWriter pw1=new PrintWriter("reportSalary/reportSelection.txt");

	    Employee[] arreglo = new Employee[employees.size()];
	    for(int i=0;i<employees.size();i++) {
	    	arreglo[i]=employees.get(i);
	    }
	    
	    for( int i = 0; i < (arreglo.length - 1); i++ ){
	        Employee menor = arreglo[ i ];
	        int cual = i;
	        for( int j = i + 1; j < arreglo.length; j++ ){
	            if( arreglo[ j ].getSalary() < menor.getSalary() ){
	                menor = arreglo[ j ];
	                cual = j;            
	                }        
	            }
	        Employee temp = arreglo[ i ];
	        arreglo[ i ] = menor;
	        arreglo[ cual ] = temp;   
	        }
	
	
	pw1.println("----------REPORTE SALARIOS DE EMPLEADOS POR SELECTION-----");
	pw1.println();

	int totalSalary=0;
	for(int i=0;i<arreglo.length;i++) {
		Employee e=arreglo[i];
		totalSalary+=e.getSalary();
		pw1.println(e.getName()+"\t\t "+e.getWork()+"\t "+e.getSalary());				
	}
	pw1.println("--------------------------------------------------------");
	pw1.println("TOTAL SALARIOS: \t\t $ "+totalSalary);
	pw1.close();
	}
	
	public Employee[] addUserAdmin() throws IOException {
		Employee e=null;
		ArrayList<Employee> employees=new ArrayList<Employee>();
		String prefix=",";
		File file=new File("data/userAdmin.txt");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);		
		String line=br.readLine();
		
		
		while(line!=null) {

			String[] datos=line.split(prefix);
			
			String name=(datos[0]);
			String lastName=(datos[1]);
			int salary=Integer.parseInt(datos[2]);
			int phone=Integer.parseInt(datos[3]);
			String work=datos[4];
			
			e=new Employee(name,lastName,salary,phone,work);
			employees.add(e);
			line=br.readLine();
		}
		Employee em[]=new Employee[employees.size()];
		for(int i=0;i<4;i++) {
			em[i]=employees.get(i);
		}
		fr.close();
		br.close();
		return em;
	}
	

	
	///FIN SECCIÓN GERENTE///
	
	public void addAccounts(Account a) {
		accounts.add(a);		
	}
	
	
	
	
	
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

	public String getNumberFactura() {
		
		return numberFactura+"";
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}
	
	
	
}
