package model.herencia;

public class Product {

	private int code;
	private String name;
	private int precio;
	private String medida;
	
	
	public Product(int code, String name, int precio, String medida) {
		super();
		this.code = code;
		this.name = name;
		this.precio = precio;
		this.medida=medida;
		
	}

	
	
	public String getMedida() {
		return medida;
	}



	public void setMedida(String medida) {
		this.medida = medida;
	}



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
	
}
