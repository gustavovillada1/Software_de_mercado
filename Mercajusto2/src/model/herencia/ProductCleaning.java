package model.herencia;

public class ProductCleaning extends Product{
	
	private int percentCleaning;
	private String polvoliquido;

	public ProductCleaning(int code, String name, int precio,String medida, int percentCleaning,String polvoliquido) {
		super(code, name, precio, medida);
		this.percentCleaning=percentCleaning;
		this.polvoliquido=polvoliquido;
	
	}

	public int getPercentCleaning() {
		return percentCleaning;
	}

	public void setPercentCleaning(int percentCleaning) {
		this.percentCleaning = percentCleaning;
	}



	
}
