package controlador;


import model.Account;
import model.DoubleLinkedCircularAccounts;
import model.Employee;
import model.Principal;
import model.herencia.Product;
import model.herencia.ProductCleaning;
import model.herencia.ProductDrinks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadController implements Initializable,Serializable{

    @FXML
    private Button buttonSignUp;

    @FXML
    private Hyperlink linkRegister;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUsername;

    /**
     * La clase principal del programa, donde se manejará todo.
     */
    private Principal principal;

    
    
    @FXML
    void registerNewEmployee(ActionEvent event) throws IOException {
    	
    	FXMLLoader open= new FXMLLoader(getClass().getResource("/vista/FXMLRegisterEmployee.fxml")); 
    	Parent root =open.load();
    	
    	RegisterEmployeeController controlleR =open.getController();
    	Scene scene1 =new Scene(root);
    	Stage stage1 = new Stage();
    	stage1.initModality(Modality.APPLICATION_MODAL);// PARA QUE NO ME PERMITA VOLVER A LA VENTANA ANTERIOR
    	stage1.setScene(scene1);
    	stage1.setTitle("Registrar Empleados");
    	stage1.showAndWait();  // Tema de las alertas.
    	

    	Employee e=controlleR.getEmployee();
    	Account employeeAccount = controlleR.getAccount();
    	
    	boolean account=controlleR.getAccountYes();
    	
    	//Si el empleado viene con una cuenta de acceso creada se agregan a dos estructuras de datos.
    	if(account==true) {
    		this.principal.getEmployees().add(e);
    		this.principal.getAccounts().add(employeeAccount);
    		
    	}else{
    		this.principal.getEmployees().add(e);
    		System.out.println("entras");
    	}
    }
    

    @FXML
    void signUp(ActionEvent event) throws IOException {
    	//Verificamos que los campos de usuario y contraseña esten llenos.
    	if(fieldUsername.getText().equals("")||fieldPassword.getText().equals("")) {   		
    		showAlert("Uppps.!!", "Ingresa la información, por favor.");
    	}

    	//Verificamos que la cuenta exista y que la clave sea la correcta, para despues entrar a la ventana principal.
		boolean foundAccount=false;
		
		
		DoubleLinkedCircularAccounts employeesAccounts=this.principal.getAccounts();
		
    	if(employeesAccounts.isEmpty()!=true) {
    	for(int i=0;i<employeesAccounts.size();i++) {    		
    		if(employeesAccounts.get(i).getUsername().equals(fieldUsername.getText())) {
    			foundAccount=true;
    			if(employeesAccounts.get(i).getPassword().equals(fieldPassword.getText())) {
    				
    		    	FXMLLoader open1= new FXMLLoader(); 
    		    	open1.setLocation(Main.class.getResource("/vista/FXMLPrincipal.fxml"));
    		    	Parent root =open1.load();
    		    	
    		    	PrincipalController control =open1.getController();
    		    	control.initAtributtes(principal,employeesAccounts.get(i)); //Le pasamos las cuentas y los empleados a la ventana principal
    		    	
    		    	Scene scene1 =new Scene(root);
    		    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		    	window.setScene(scene1);
    		    	window.setTitle("MERCAJUSTO");
    		    	window.setResizable(false);
    		    	window.show();  
    		    	
    			}else {
    				showAlert("Intenta de nuevo", "Clave incorrecta.");
    	    		fieldPassword.setText("");
    				break;
    			}
    		}
    	}
    	
    	if(foundAccount==false) {
			showAlert("Alerta", "El usuario ingresado no existe en el sistema.");
    		fieldUsername.setText("");
    		fieldPassword.setText("");
    	}
    	
    	}else {
    		showAlert("Sistema Vacio", "El programa no contiene cuentas aún, por favor registra a un empleado con cuenta.");
    		fieldUsername.setText("");
    		fieldPassword.setText("");
    	}	
    }
    

    /**
     * Metodo encargado de generar una alerta de tipo warning, para ser usada varias veces.
     * @param tittle El titulo que tendrá esta alerta
     * @param messagge El mensaje que tendrá la alerta en su contenido.
     */
    public void showAlert(String tittle,String messagge) {
		
		Alert alert=new Alert(AlertType.WARNING);
		alert.setTitle(tittle);
		alert.setHeaderText(null);
		alert.setContentText(messagge);
		alert.showAndWait();
    }

    /**
     * Se encarga de pasar la clase principal a la ventana de inicio, cuando cerramos sesión o agregamos
     * un nuevo empleado.
     * @param p
     */
    public void initializeAtribute(Principal p) {
    	this.principal.setEmployees(p.getEmployees());
    	this.principal.setAccounts(p.getAccounts());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Employee> employees=new ArrayList<Employee>();
		DoubleLinkedCircularAccounts employeesAcounts = new DoubleLinkedCircularAccounts();
		
		Employee e=new Employee("GUSTAVO VILLADA", "MOLINA", 2500000, 31087247, "GERENTE");
		Account a=new Account("gustavo", "123", e,"CIRCULO,ROJO");
				
		employees.add(e);
		employeesAcounts.add(a);
		principal=new Principal(employees, employeesAcounts);


	}
	
	


}
