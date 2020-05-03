package controlador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import model.*;
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
	    private TableView<Employee> tableViewProducts;

	    @FXML
	    private TableColumn<Employee, String> tableColumNameEmployee;

	    @FXML
	    private TableColumn<Employee, String> tableColumLastNameEmployee;

	    @FXML
	    private TableColumn<Employee, String> tableColumWorkEmployee;

	    @FXML
	    private TableColumn<Employee, Integer> tableColumSalaryEmployee;
    
    private Principal principal;
    private Account currentAccount;
    

    private ObservableList<Employee> employeesOBS;
    
 

    
    ////////////////////GERENTE/////////////////////////
    @FXML
    void showMessaggeGerente(ActionEvent event) {    		
    		String ms=messaggeGerentText.getText();
    		messaggeGerent.setText(ms);

    }
    ////////////////////GERENTE/////////////////////////

    
    ///////////////////CAJERO///////////////////////////
    @FXML
    void addProductCajero(ActionEvent event) {

    	
    }


    @FXML
    void deteleProductCajero(ActionEvent event) {

    }

    @FXML
    void generateFacture(ActionEvent event) {

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
    void guardar(ActionEvent event) {

    }



    @FXML
    void salir(ActionEvent event) {

    	guardar(event);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();    	
    	window.close();
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

    /**
     * Inicializa algunos atributos y actualiza algunos elementos del GUI.
     * @param p La clase Principal, con sus respectivos datos.
     * @param accountCurrent La cuenta de acceso con la cual se ha ingresado al programa.
     */
public void initAtributtes(Principal p, Account accountCurrent) {
    
    	this.principal=p;
    	this.currentAccount=accountCurrent;
    	ajustPrincipalToEmployee();
    	MessaggeGerenteThread m=new MessaggeGerenteThread();
    	m.addObserver(this);
    	m.startThread();
    	
    	
    	/*
    	employeesOBS = FXCollections.observableArrayList(p.getEmployees());
    	tableViewEmployees=new TableView<>();
    	tableViewEmployees.setItems(employeesOBS);
    	
    	tableColumNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableColumLastNameEmployee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	tableColumWorkEmployee.setCellValueFactory(new PropertyValueFactory<>("work"));
    	tableColumSalaryEmployee.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	tableViewEmployees.getColumns().addAll(tableColumNameEmployee,tableColumLastNameEmployee,tableColumWorkEmployee,tableColumSalaryEmployee);
    	*/
  	
    	
    	
   }
    
   
    
    /**
     * Este método se encarga de acondicionar la ventana principal con las funcionalidades.
     */
    private void ajustPrincipalToEmployee() {
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
		messaggeGerent.setTranslateX((double) arg1);		
		
		});
	}





    
}
