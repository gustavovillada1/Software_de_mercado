package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.webkit.ContextMenu.ShowContext;

import Exceptions.EmployeeDataMissingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Account;
import model.Employee;

public class RegisterEmployeeController implements Initializable {
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    
		addTypesEm();
				
	}
	
	public void addTypesEm() {
		
		List<String> typEm=new ArrayList<String>();
		ObservableList<String> te;
		typEm.add("CAJERO");
		typEm.add("GERENTE");
		typEm.add("INVENTARIO");
		typEm.add("PASILLO");
		typEm.add("LIMPIEZA");
		typEm.add("CARNES");
		te=FXCollections.observableArrayList(typEm);
		typeEmployee.setItems(te);
	}

	
    @FXML
    private ComboBox<String> typeEmployee;

    @FXML
    private TextField names;

    @FXML
    private TextField lastNames;

    @FXML
    private TextField salaryMonth;

    @FXML
    private TextField telephone;

    @FXML
    private Button addEmployee;

    @FXML
    private CheckBox checkCreateAcount;

    @FXML
    private Pane paneAddAcount;

    @FXML
    private TextField acountUsername;

    @FXML
    private TextField accountPassword;

 
    //Variables para pasar.
    
    private Employee employee;
    
    private boolean accountYes;
    
    private Account account;

    
    
    @FXML
    void addAcount(ActionEvent event) {
    	if(paneAddAcount.isDisable()==true) {
    		paneAddAcount.setDisable(false);
    	}else if(paneAddAcount.isDisable()==false) {
    		paneAddAcount.setDisable(true);
    	}
    }
  
    
    //FALTA HACER UNA EXCEPCION PROPIA PARA CUANDO LOS CAMPOS DEL USUARIO ESTÉN VACIOS Y TAMBIEN PONER LA EXCEPCION PARA QUE NO HAYA ALFABETO EN EL SALARIO Y TELEFONO
    @FXML
    void addNewEmployee(ActionEvent event) {
    	
    	try {
    		    		
    		if(names.getText().equals("")||lastNames.getText().equals("")||salaryMonth.getText().equals("")||telephone.getText().equals("")) {
    			throw new EmployeeDataMissingException();	
    		}
        	   		
    	accountYes=false;    	

    	int salary=Integer.parseInt(this.salaryMonth.getText());
    	int phone=Integer.parseInt(this.telephone.getText());
    	this.employee=new Employee(this.names.getText(),this.lastNames.getText(), salary, phone, this.typeEmployee.getSelectionModel().getSelectedItem());
    
    	if(paneAddAcount.isDisable()==false) {
    		String u=acountUsername.getText();
    		String p=accountPassword.getText();
    		if(u.equals("")||p.equals("")) {
    			Alert alert= new Alert(AlertType.ERROR);
    			alert.setHeaderText(null);
    			alert.setContentText("Por favor Agrega un nombre de usuario o contraseña");
    			alert.setTitle("Crear Cuenta");
    			alert.showAndWait();
    		}else {
        		accountYes=true;
        		Account account1=new Account(u, p, this.employee);
        		this.account=account1;
    		}
    	
    	}
    	if((paneAddAcount.isDisable()==true&&accountYes==false)||(paneAddAcount.isDisable()==false&&accountYes==true)){
    	
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setHeaderText(null);
    	alert.setTitle("Información");
    	String msg=null;
    	if(accountYes==false) {
    	msg=names.getText()+" ha sido añadido correctamente";
    	}else {
        	msg=names.getText()+" ha sido añadido correctamente, con el usuario: | "+acountUsername.getText()+" |";
    	}
    	alert.setContentText(msg);
    	alert.showAndWait();
  
    	Stage stage1= (Stage) this.addEmployee.getScene().getWindow();  // aqui tomamos la ventana donde estamos, para que al darle clic a ese boton se cierra.
    	stage1.close();
    	}

    	}catch(EmployeeDataMissingException e) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Faltan datos del empleado, por favor agregalos.");
			alert.setTitle("Opps.");
			alert.showAndWait();
    	}catch(NumberFormatException e) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor ingresa numeros en el salario y en el telefono.");
			alert.setTitle("Opps.");
			alert.showAndWait();
    	}
    	
    }

    public boolean getAccountYes() {
    	return this.accountYes;
    }
    
    public Account getAccount() {
    	return this.account;
    }
    

    public Employee getEmployee() {
    	
    	return this.employee;
    }

}

