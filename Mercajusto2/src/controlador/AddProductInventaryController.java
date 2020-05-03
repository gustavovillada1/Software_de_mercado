package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.Principal;
import model.herencia.Product;

public class AddProductInventaryController implements Initializable{

    @FXML
    private TextField nameProduct;

    @FXML
    private TextField marca;

    @FXML
    private TextField medida;

    @FXML
    private TextField price;

    @FXML
    private TextField cantidad;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private ComboBox<String> cbCategory2;

    @FXML
    private Button add;

    @FXML
    private Pane paneFruits;

    @FXML
    private TextField typeFruit;

    @FXML
    private Pane paneCleaning;

    @FXML
    private TextField percentCleaning;

    @FXML
    private TextField polvoOLiquido;

    @FXML
    private Pane paneCleaning1;

    @FXML
    private TextField percentFruits;

    @FXML
    private TextField cantidadAzucar;
    @FXML
    private TextField codigo;

    private Principal p;

    private boolean paneOpen;

    @FXML
    void showPaneProducts(ActionEvent event) {
    	
    	if(paneOpen==true) {
    		paneFruits.setVisible(false);
    		paneCleaning1.setVisible(false);
    		paneCleaning.setVisible(false);
    		paneOpen=false;
    	}else {
    		if(cbCategory2.getSelectionModel().getSelectedItem()=="FRUTA") {
    			paneFruits.setVisible(true);
    			paneOpen=true;
    		}else if(cbCategory.getSelectionModel().getSelectedItem()=="LIMPIEZA") {
    			paneCleaning.setVisible(true);
    			paneOpen=true;

    		}else if(cbCategory.getSelectionModel().getSelectedItem()=="BEBIDA") {
    			paneCleaning1.setVisible(true);
    			paneOpen=true;
    		}
    	}

    	
    }
    
    @FXML
    void addProduct(ActionEvent event) {
    	
    	//try {
    	
    	
    	String type=cbCategory.getSelectionModel().getSelectedItem();
    	String type1=cbCategory2.getSelectionModel().getSelectedItem();
    	int code=Integer.parseInt(codigo.getText());
    	String marcax=marca.getText();
    	String name=nameProduct.getText()+" "+marca.getText();
    	int pricex=Integer.parseInt(price.getText());
    	String medidax=medida.getText();
    	String tipoFruta=typeFruit.getText();
    	int porcentajeLimpieza=Integer.parseInt(percentCleaning.getText());
    	String polvoliquido=polvoOLiquido.getText();
    	int percentFruit=Integer.parseInt(percentFruits.getText());
    	int percentSuggar=Integer.parseInt(cantidadAzucar.getText());
    	int cantidadx=Integer.parseInt(cantidad.getText());

    	System.out.println("Line1"+type+"|"+type1+"|"+code+"|"+name+"|");
    	System.out.println("Line2"+pricex+"|"+medidax+"|"+tipoFruta+"|");
    	System.out.println("Line3"+porcentajeLimpieza+"|"+polvoliquido);
    	System.out.println("Line4"+"|"+percentFruit+"|"+percentSuggar+"|"+cantidadx);
    	p.addProductsInventary(cantidadx,type, type1, code, name, pricex, medidax, tipoFruta, 0, polvoliquido, 0, 0);
   	
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Se han agregado "+cantidadx+" productos de "+name+" a un precio de "+pricex+"$");
		alert.setTitle("Opps.");
		alert.showAndWait();
    	/*}catch(Exception e) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Ha ocurrido un ERROR. "+e.getMessage());
			alert.setTitle("Opps.");
			alert.showAndWait();
    	}*/
    }
    
    @FXML
    void choose(ActionEvent event) {
    	
    	if(cbCategory.getSelectionModel().getSelectedItem().equals("ALIMENTO")) {
    		List<String> category=new ArrayList<String>();
    		ObservableList<String> te;
    		category.add("OTRO");
    		category.add("FRUTA");
    		te=FXCollections.observableArrayList(category);
    		cbCategory2.setItems(te);	
    		
    		
    	}else if(cbCategory.getSelectionModel().getSelectedItem().equals("LIMPIEZA")) {
    		List<String> category=new ArrayList<String>();
    		ObservableList<String> te;
    		category.add("LIQUIDO");
    		category.add("POLVO");
    		category.add("HERRAMIENTA");
    		te=FXCollections.observableArrayList(category);
    		cbCategory2.setItems(te);	
    	}else {
    		List<String> category=new ArrayList<String>();
    		ObservableList<String> te;
    		category.add("GASEOSA");
    		category.add("JUGO");
    		te=FXCollections.observableArrayList(category);
    		cbCategory2.setItems(te);	
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> category=new ArrayList<String>();
		ObservableList<String> te;
		category.add("ALIMENTO");
		category.add("LIMPIEZA");
		category.add("BEBIDA");
		te=FXCollections.observableArrayList(category);
		cbCategory.setItems(te);		
	}
	
	public void initPrincipal(Principal p) {
		this.p=p;
	}

	public Principal getPrincipal() {
		return p;
	}


}
