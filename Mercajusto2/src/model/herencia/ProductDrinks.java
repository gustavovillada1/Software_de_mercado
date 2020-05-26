package model.herencia;


@SuppressWarnings("serial")
public class ProductDrinks extends Product {

	private int percentSuggar;
	private int percentFruit;
	
	public ProductDrinks(int code, String name, int precio,String medida,int percentFruit,int percentSuggar) {
		super(code, name, precio, medida);
		this.percentFruit=percentFruit;
		this.percentSuggar=percentSuggar;
	}

	public int getPercentSuggar() {
		return percentSuggar;
	}

	public void setPercentSuggar(int percentSuggar) {
		this.percentSuggar = percentSuggar;
	}

	public int getPercentFruit() {
		return percentFruit;
	}

	public void setPercentFruit(int percentFruit) {
		this.percentFruit = percentFruit;
	}

	@Override
	public String toString() {
		return "ProductDrinks [percentSuggar=" + percentSuggar + ", percentFruit=" + percentFruit + "]";
	}
	
	

}
