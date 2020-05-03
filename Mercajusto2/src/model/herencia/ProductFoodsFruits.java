package model.herencia;

public class ProductFoodsFruits extends ProductFoods {

	String typeFruit;
	
	public ProductFoodsFruits(int code, String name, int precio, String medida, String tf) {
		super(code, name, precio, medida);
		this.typeFruit=	tf;
		}

	public String getTypeFruit() {
		return typeFruit;
	}

	public void setTypeFruit(String typeFruit) {
		this.typeFruit = typeFruit;
	}

	
	
}
