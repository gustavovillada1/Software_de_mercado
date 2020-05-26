package controlador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.theProductIsNotException;
import model.*;
import model.herencia.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class PrincipalController implements Observer{

	 	@FXML
	    private Hyperlink linkCloseSesion;

	    @FXML
	    private Label labelUsername;

	    @FXML
	    private Label labelWork;

	    @FXML
	    private MenuItem guardar;
	    
	    @FXML
	    private Ellipse ellipse;

	    @FXML
	    private Polygon triangle;

	    @FXML
	    private Rectangle rectangle;
	    
	    @FXML
	    private Circle circle1;

	    @FXML
	    private MenuItem salirAplicacion;

	    @FXML
	    private MenuItem menuAyudaCaja;

	    @FXML
	    private MenuItem menuAyudaInventario;

	    @FXML
	    private Tab seccionCajero;

	    @FXML
	    private Label labelTotalPagar;

	    @FXML
	    private Label labelCambio;

	    @FXML
	    private TextField textTotalEntregado;

	    @FXML
	    private Button buttonGenerateFacture;

	    @FXML
	    private TextField textCodeProductAddCajero;

	    @FXML
	    private Button buttonAddProductCajero;

	    @FXML
	    private TextField textCantidadAddCajero;

	    @FXML
	    private TextField textCodeProductDeleteCajero;

	    @FXML
	    private Button buttonDeleteProductCajero;

	    @FXML
	    private TextField textCantidadDeleteCajero;

	    @FXML
	    private TextField textNameClient;

	    @FXML
	    private TextField textIdentificationClient;

	    @FXML
	    private Tab seccionInventario;

	    @FXML
	    private Tab seccionEmpleados;

	    @FXML
	    private Label downCostoXunidad;

	    @FXML
	    private Label downProduct;

	    @FXML
	    private Label downCantidad;

	    @FXML
	    private Label downCode;
	    
	    @FXML
	    private TextArea messaggeGerentText;

	    @FXML
	    private Label messaggeGerent;

	    @FXML
	    private TableView<Employee> tableViewEmployees;
	    
	    @FXML
	    private TableView<Product> tableViewProductsInventary;

	    @FXML
	    private TableColumn<Product, String> tableColumnInventaryName;

	    @FXML
	    private TableColumn<Product, Integer> tableColumnInventaryCode;

	    @FXML
	    private TableColumn<Product, String> tableColumnInventaryMedida;

	    @FXML
	    private TableColumn<Product, Integer> tableColumnInventaryPrice;
	    
	    @FXML
	    private TableView<Product> tableViewProducts;
	    	    
	    @FXML
	    private TableColumn<Product, String> tableColumnCajeroProduct;

	    @FXML
	    private TableColumn<Product, Integer> tableColumnCajeroCode;

	    @FXML
	    private TableColumn<Product, Integer> tableColumnCajeroPrice;

	    @FXML
	    private TableColumn<Integer, Integer> tableColumnCajeroTotalPrice;

	    @FXML
	    private TableColumn<Employee, String> tableColumNameEmployee;

	    @FXML
	    private TableColumn<Employee, String> tableColumLastNameEmployee;

	    @FXML
	    private TableColumn<Employee, String> tableColumWorkEmployee;

	    @FXML
	    private TableColumn<Employee, Integer> tableColumSalaryEmployee;
	    
	    @FXML
	    private TextField textDeleteInventaryCode;

	    @FXML
	    private TextField textCantidadDeleteInventary;
	    
	    @FXML
	    private TextField nameEmployeeDespedido;
    
    private Principal principal;
    private Account currentAccount;
    

    private ObservableList<Employee> employeesOBS;
    private ObservableList<Product> productOBS;
    private ObservableList<Product>	productClientOBS;
    
 

    
    ////////////////////GERENTE/////////////////////////
    
    @FXML
    void abrirPrograma(ActionEvent event) {
		try {
		ObjectInputStream openProgram=new ObjectInputStream(new FileInputStream("data/programSaved.gus"));
		Principal pr=(Principal) openProgram.readObject();
		this.principal=pr;
			openProgram.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	employeesOBS = FXCollections.observableArrayList(principal.getEmployees());
    	tableViewEmployees.setItems(employeesOBS);
    	
    	tableColumNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumLastNameEmployee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	tableColumWorkEmployee.setCellValueFactory(new PropertyValueFactory<>("work"));
    	tableColumSalaryEmployee.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	
    	productOBS = FXCollections.observableArrayList(principal.getProductsInventary());
    	tableViewProductsInventary.setItems(productOBS);
    	
    	tableColumnInventaryName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnInventaryCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnInventaryPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	tableColumnInventaryMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
    	
    	showAlert("Aviso", "El programa ha sido cargado exitosamente.", "INFORMATION");
    	
    }
    
    @FXML
    void showMessaggeGerente(ActionEvent event) {    		
    		String ms=messaggeGerentText.getText();
    		messaggeGerent.setText(ms);

    }
    
    @FXML
    void generateReport(ActionEvent event) throws FileNotFoundException {
    	Optional<ButtonType> action= showAlertConfirmation("Generar Reporte", "¿Estás seguro de generar el reporte?");

    	if (action.get() == ButtonType.OK) { 
    	principal.generateReportSalary();
    	}
    }
    
    @FXML
    void despedirEmpleado(ActionEvent event) {
    	String nameEmployee=nameEmployeeDespedido.getText();
    	boolean employeDeleted=false;
    	employeDeleted=principal.searchEmployeeNameBinary(nameEmployee);
    	
    	if(employeDeleted==true) {
    		showAlert("Despedido", "La persona "+nameEmployee+" ha sido despedida de la empresa :C.", "INFORMATION");
    	}else {
    		showAlert("Error", "No se ha encontrado empleado con el nombre "+nameEmployee+" .", "INFORMATION");
    	}
    	
    	employeesOBS = FXCollections.observableArrayList(principal.getEmployees());
    	tableViewEmployees.setItems(employeesOBS);
    	
    	tableColumNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumLastNameEmployee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	tableColumWorkEmployee.setCellValueFactory(new PropertyValueFactory<>("work"));
    	tableColumSalaryEmployee.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    ////////////////////GERENTE/////////////////////////

    
    ///////////////////CAJERO///////////////////////////
    @FXML
    void addProductCajero(ActionEvent event) {

    	try {
    	int code=Integer.parseInt(textCodeProductAddCajero.getText());
    	int cant=Integer.parseInt(textCantidadAddCajero.getText());
    	textCodeProductAddCajero.setText("");
    	textCantidadAddCajero.setText("");
    	
    	boolean encontrado=false;
    	for(int i=0;i<principal.getProductsInventary().size();i++) {
    		if(principal.getProductsInventary().get(i).getCode()==code) {
    			encontrado=true;
    		}
    	}
    	if(encontrado==false) {
    		throw new theProductIsNotException();
    	}
    	
    	
    	Product p=null;
    	for(int i=0;i<principal.getProductsInventary().size();i++) {
    		if(principal.getProductsInventary().get(i).getCode()==code) {
    			p=principal.getProductsInventary().get(i);
    			break;
    		}
    	}
    	
    	for(int i=0;i<cant;i++) {
    	principal.getClientProducts().add(p);
    	}
    	productClientOBS = FXCollections.observableArrayList(principal.getClientProducts());
    	tableViewProducts.setItems(productClientOBS);
    	
    	tableColumnCajeroProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnCajeroCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnCajeroPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));

    	int sumatotal=0;
    	
    	sumatotal=principal.sumarPreciosCarrito(0);
    	/*
    	for(int i=0;i<principal.getClientProducts().size();i++) {
    		sumatotal+=principal.getClientProducts().get(i).getPrecio();
    	}*/

    	labelTotalPagar.setText(sumatotal+"");
    	downCantidad.setText(cant+"");
    	downCode.setText(code+"");
    	downProduct.setText(p.getName());
    	downCostoXunidad.setText(p.getPrecio()+"");
    	}catch(theProductIsNotException e) {
    		showAlert("Error", "No hay un producto en el inventario con este código.", "ERROR");
    	}catch(NumberFormatException e) {
    		showAlert("Cuidado.!!", "Ingresa un numero o rellena los campos.", "WARNING");

    	}
    }


    @FXML
    void deteleProductCajero(ActionEvent event) {
    	try {
    		
        int code=Integer.parseInt(textCodeProductDeleteCajero.getText());
        int q=Integer.parseInt(textCantidadDeleteCajero.getText());
        textCantidadDeleteCajero.setText("");
        textCodeProductDeleteCajero.setText("");

    	//int removes=principal.deleteProduct(code, principal.getClientProducts().size()-1,q); para el recursivo
        int removes=principal.deleteProduct(code,q);
    	int totalPrice=Integer.parseInt(labelTotalPagar.getText());
    	int priceLess=principal.showPriceByCode(code);
    	labelTotalPagar.setText((totalPrice-(priceLess*removes))+"");
        if(removes==0) {
    		throw new theProductIsNotException();
    	}else if(removes<q) {
    		showAlert("ERROR", "Lo sentimos, sólo habian "+removes+" productos con este código.", "WARNING");
    	}else if(q==removes) {
    		showAlert("Correcto", "Se han eliminado correctamente "+q+" elementos del carrito", "WARNING");
    	}else {
    		showAlert("ERROR", "pinche programa de verga: "+removes, "ERROR");
    	}
    	
    	productClientOBS = FXCollections.observableArrayList(principal.getClientProducts());
    	tableViewProducts.setItems(productClientOBS);
    	
    	tableColumnCajeroProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnCajeroCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnCajeroPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	
    	}catch(NumberFormatException e) {
    		showAlert("Error", "Ingresa números por favor", "ERROR");
    	}catch(theProductIsNotException e) {
    		showAlert("Error", "No existe algún producto con este código.", "ERROR");
    	}
    	
    }

    @FXML
    void generateFacture(ActionEvent event) throws IOException {

    	int sumatotal=0;
    	
    	for(int i=0;i<principal.getClientProducts().size();i++) {
    		sumatotal+=principal.getClientProducts().get(i).getPrecio();
    	}
    	int pago=Integer.parseInt(textTotalEntregado.getText());
    	int tcambio=pago-sumatotal;

    	labelCambio.setText(tcambio+"");
    	labelTotalPagar.setText(sumatotal+"");
    	
    	Optional<ButtonType> action= showAlertConfirmation("Generar Factura", "¿Estás seguro de generar la factura?");

    	if (action.get() == ButtonType.OK) { /// ha pulsado en aceptar
    	principal.generateFacture(textNameClient.getText(), textNameClient.getText(),labelCambio.getText(), labelTotalPagar.getText(), textTotalEntregado.getText());
    	
    	
    	
    	downCantidad.setText("0");
    	downCode.setText("Ninguno");
    	downCostoXunidad.setText("0");
    	downProduct.setText("Producto");
    	labelCambio.setText("0");
    	labelTotalPagar.setText("0");
    	textCantidadAddCajero.setText(" ");
    	textCodeProductAddCajero.setText(" ");
    	textCodeProductDeleteCajero.setText(" ");
    	textCantidadDeleteCajero.setText("0");
    	principal.getClientProducts().clear();
    	
    	productClientOBS = FXCollections.observableArrayList(principal.getClientProducts());
    	tableViewProducts.setItems(productClientOBS);
    	
    	tableColumnCajeroProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnCajeroCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnCajeroPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	showAlert("Acción exitosa.!", "La factura numero "+principal.getNumberFactura()+" ha sido generada correctamente.", "INFORMATION");
    	}
    	
    }
    ///////////////////CAJERO///////////////////////////
    
    ////////////////INVENTARIO//////////////////////////
    
    
    @FXML
    void addNewProductInventario(ActionEvent event) throws IOException {


    	
	FXMLLoader open= new FXMLLoader(getClass().getResource("/vista/AddProductInventary.fxml")); 
	Parent root =open.load();
    AddProductInventaryController adi=open.getController();
    adi.initPrincipal(this.principal);
   	Scene scene1 =new Scene(root);
	Stage stage1 = new Stage();
	stage1.initModality(Modality.APPLICATION_MODAL);// PARA QUE NO ME PERMITA VOLVER A LA VENTANA ANTERIOR
	stage1.setScene(scene1);
	stage1.setTitle("AGREGAR PRODUCTO");
	stage1.showAndWait();  
	this.principal=adi.getPrincipal();

	//Actualizamos la tableView con el nuevo producto agregado.
	productOBS = FXCollections.observableArrayList(principal.getProductsInventary());
	tableViewProductsInventary.setItems(productOBS);
	
	tableColumnInventaryName.setCellValueFactory(new PropertyValueFactory<>("name"));
	tableColumnInventaryCode.setCellValueFactory(new PropertyValueFactory<>("code"));
	tableColumnInventaryPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
	tableColumnInventaryMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
	
    
    }
    
    @FXML
    void deleteProductsInventary(ActionEvent event) {
        System.out.println("no hace nada");

        int code=Integer.parseInt(textDeleteInventaryCode.getText());
        int q=Integer.parseInt(textCantidadDeleteInventary.getText());
        boolean deleted=false;
        
        deleted=principal.deleteProductInventary(code,q);
        
        if(deleted==false) {
        	showAlert("Error", "No se ha podido eliminar ningún producto.", "ERROR");
        }else {
        	showAlert("Correcto", "Se han eliminado correctamente "+q+" productos del inventario.", "INFORMATION");
        }
        
    	productOBS = FXCollections.observableArrayList(principal.getProductsInventary());
    	tableViewProductsInventary.setItems(productOBS);
    	
    	tableColumnInventaryName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnInventaryCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnInventaryPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	tableColumnInventaryMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
    }
    
    ////////////////INVENTARIO//////////////////////////


    ///////////////////VENTANA//////////////////////////

    
    @FXML
    void menuAyudaCaja(ActionEvent event) throws IOException {

    	FXMLLoader open= new FXMLLoader(getClass().getResource("/vista/CajeroInstructions.fxml")); 
    	Parent root =open.load();
       	Scene scene1 =new Scene(root);
    	Stage stage1 = new Stage();
    	stage1.initModality(Modality.APPLICATION_MODAL);// PARA QUE NO ME PERMITA VOLVER A LA VENTANA ANTERIOR
    	stage1.setScene(scene1);
    	stage1.setTitle("AYUDA");
    	stage1.showAndWait();  // 
    	
    	
    }

    @FXML
    void menuAyudaInventario(ActionEvent event) throws IOException {

    	FXMLLoader open= new FXMLLoader(getClass().getResource("/vista/InventarioInstructions.fxml")); 
    	Parent root =open.load();
       	Scene scene1 =new Scene(root);
    	Stage stage1 = new Stage();
    	stage1.initModality(Modality.APPLICATION_MODAL);// PARA QUE NO ME PERMITA VOLVER A LA VENTANA ANTERIOR
    	stage1.setScene(scene1);
    	stage1.setTitle("AYUDA");
    	stage1.showAndWait();  // Tema de las alertas.
    	
    }
    
    @FXML
    void closeSesion(ActionEvent event) throws IOException {
    	Optional<ButtonType> action= showAlertConfirmation("Cerrar Sesión", "¿Seguro que deseas cerrar la sesión?");

    	if (action.get() == ButtonType.OK) { /// ha pulsado en aceptar

		    	FXMLLoader open1= new FXMLLoader(); 
		    	open1.setLocation(Main.class.getResource("/vista/FXMLload.fxml"));
		    	Parent root =open1.load();
		    	
		    	LoadController control =open1.getController();
		    	control.initializeAtribute(principal); //Le pasamos las cuentas y los empleados a la ventana principal
		    	
		    	Scene scene1 =new Scene(root);
		    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    	window.setScene(scene1);
		    	window.setTitle("MERCAJUSTO");
		    	window.show();  
    		  		
    	} 
    	
    }

    @FXML
    void guardar(ActionEvent event) throws FileNotFoundException, IOException {

		ObjectOutputStream saving=new ObjectOutputStream(new FileOutputStream("data/programSaved.gus"));
		saving.writeObject(this.principal);
		saving.close();
    }





    /**
     * Este es un metodo genérico para el llamado a las alertas.
     * @param tittle El titulo de la ventana de alerta.
     * @param messagge El mensaje de la alerta.
     * @param typeAlert El tipo de alerta.
     */
    public void showAlert(String tittle,String messagge,String typeAlert) {
    	Alert alert = null;
    	if(typeAlert=="WARNING") {
		alert=new Alert(AlertType.WARNING);
    	}else if(typeAlert=="INFORMATION") {
		alert=new Alert(AlertType.INFORMATION);
    	}else if(typeAlert=="ERROR") {
		alert=new Alert(AlertType.ERROR);
    	}
		alert.setTitle(tittle);
		alert.setHeaderText(null);
		alert.setContentText(messagge);
		alert.showAndWait();
    }
    
    
    //Para usar cada vez que quiera un dialogo de confirmación.
    /**
     * Metodo encargado de hacer un dialogo de CONFIRMACIÓN, para que sea más facil cada vez que se quiera usar un dialogo de este tipo.
     * @param tittle titulo de la ventana de alerta.
     * @param messagge el mensaje de la ventana de alerta.
     * @return la opcion elegida por el usuario en la alerta.
     */
    public Optional<ButtonType> showAlertConfirmation(String tittle,String messagge) {
    Alert alert=new Alert(AlertType.CONFIRMATION);

	alert.setTitle(tittle);
	alert.setHeaderText(null);
	alert.setContentText(messagge);
	alert.showAndWait();
	Optional<ButtonType> action = alert.showAndWait();
	
	return action;
	
	
    }
    
    
    //hilos secundarios
    MessaggeGerenteThread m;
    PhotoPerfilThread photo;

    /**
     * Inicializa algunos atributos y actualiza algunos elementos del GUI.
     * @param p La clase Principal, con sus respectivos datos.
     * @param accountCurrent La cuenta de acceso con la cual se ha ingresado al programa.
     * @throws IOException 
     */
public void initAtributtes(Principal p, Account accountCurrent) throws IOException {
    
		//Iniciamos la clase principal del Login, para traer consigo el empleado registrado
    	this.principal=p;
    	this.currentAccount=accountCurrent;
    	ajustPrincipalToEmployee();
    	
    	//Iniciamos los hilos
    	m=new MessaggeGerenteThread();
    	m.addObserver(this);
    	m.startThread();
    	
    	photo=new PhotoPerfilThread();
    	photo.addObserver(this);
    	photo.startThread();
    	
    	Employee e[]=principal.addUserAdmin();
    	for(int i=0;i<e.length;i++){
    		this.principal.getEmployees().add(e[i]);
    	}    	
    	
    	//Cargamos los empleados en el sistema a la tableView del gerente.
    	employeesOBS = FXCollections.observableArrayList(p.getEmployees());
    	tableViewEmployees.setItems(employeesOBS);
    	
    	tableColumNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumLastNameEmployee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	tableColumWorkEmployee.setCellValueFactory(new PropertyValueFactory<>("work"));
    	tableColumSalaryEmployee.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	
    	//Cargamos los productos que hayan en el sistema a la tableView del inventario.
    	if(p.getProductsInventary().isEmpty()==false) {
    	productOBS = FXCollections.observableArrayList(p.getProductsInventary());
    	tableViewProductsInventary.setItems(productOBS);
    	
    	tableColumnInventaryName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumnInventaryCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	tableColumnInventaryPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	tableColumnInventaryMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
    	}
   }
    
   
    
    /**
     * Este método se encarga de acondicionar la ventana principal con las funcionalidades.
     * @throws IOException 
     */
    private void ajustPrincipalToEmployee() throws IOException {
    	Paint color=this.currentAccount.getColorPhoto();
    	String figure=this.currentAccount.getFigurePhoto();

    	if(figure.equals("CIRCULO")) {
    		circle1.setFill(color);
    		circle1.setVisible(true);
    	}else if(figure.equals("TRIANGULO")) {
    		triangle.setFill(color);
    		triangle.setVisible(true);
    	}else if(figure.equals("RECTANGULO")) {
    		rectangle.setFill(color);
    		rectangle.setVisible(true);
    	}else if(figure.equals("ELLIPSE")) {
    		ellipse.setFill(color);
    		ellipse.setVisible(true);
    	} 
    	
    	
    	labelUsername.setText(currentAccount.getEmployee().getName());
    	String workCurrent=currentAccount.getEmployee().getWork();
    	labelWork.setText(workCurrent);
    	
    	if(workCurrent.equals("CAJERO")) {
    		seccionEmpleados.setDisable(true);
    		seccionInventario.setDisable(true);
    	}else if(workCurrent.equals("INVENTARIO")) {
    		seccionEmpleados.setDisable(true);
    		seccionCajero.setDisable(true);
    	} 
    	

    	
    }
    ///////////////////VENTANA//////////////////////////

    
    

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Platform.runLater(()->{	
			if(arg0.equals(m)) {
		messaggeGerent.setTranslateX((double) arg1);	
			}
			
			if(arg0.equals(photo)) {
		if(this.currentAccount.getFigurePhoto().equals("TRIANGULO")) {
		triangle.setRotate((double)arg1);
		}else if(this.currentAccount.getFigurePhoto().equals("CIRCULO")) {
		circle1.setRotate((double)arg1);
		}else if(this.currentAccount.getFigurePhoto().equals("RECTANGULO")) {
		rectangle.setRotate((double)arg1);
		}else if(this.currentAccount.getFigurePhoto().equals("ELLIPSE")) {
		ellipse.setRotate((double)arg1);
		}
			}
		});
	}





    
}
