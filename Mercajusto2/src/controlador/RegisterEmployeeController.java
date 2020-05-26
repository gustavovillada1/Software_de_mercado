package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;


import Exceptions.EmployeeDataMissingException;
import Exceptions.EmployeeNotNeedAccountException;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Account;
import model.Employee;

public class RegisterEmployeeController implements Initializable {
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		accountYes=false;
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
		te=FXCollections.observableArrayList(typEm);
		typeEmployee.setItems(te);
		
		List<String> typeFigure=new ArrayList<String>();
		ObservableList<String> tf;
		typeFigure.add("CIRCULO");
		typeFigure.add("TRIANGULO");
		typeFigure.add("RECTANGULO");
		typeFigure.add("ELLIPSE");
		tf=FXCollections.observableArrayList(typeFigure);
		cbFigure.setItems(tf);

		List<String> colorFigure=new ArrayList<String>();
		ObservableList<String> cf;
		colorFigure.add("AZUL");
		colorFigure.add("AMARILLO");
		colorFigure.add("VERDE");
		colorFigure.add("ROJO");
		cf=FXCollections.observableArrayList(colorFigure);
		cbColor.setItems(cf);
		
		
		
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
    private Circle circle;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Ellipse ellipse;

    @FXML
    private Polygon triangle;
    
    @FXML
    private Pane panePhoto;

    @FXML
    private ComboBox<String> cbFigure;

    @FXML
    private ComboBox<String> cbColor;

    @FXML
    private Button setPhotoperfil;

    @FXML
    private TextField acountUsername;

    @FXML
    private TextField accountPassword;

 
    //Variables para pasar.
    private Employee employee;
    
    //Variable encargada de verificar si se hace una cuenta de acceso.
    private boolean accountYes;
    
    //Variable de tipo Account, la cual contendrá. 
    private Account account;
    
    private boolean photoPerfilOk;

    
    @FXML
    void addPhotoPerfil(ActionEvent event) {
    	if(panePhoto.isVisible()==true) {
    		panePhoto.setVisible(false);
    	}else {
    	panePhoto.setVisible(true);
    }
    }

    @FXML
    void setPhotoPerfil(ActionEvent event) {
    	String figure=null;
    	Paint color=null;
    	triangle.setVisible(false);
    	circle.setVisible(false);
    	rectangle.setVisible(false);
    	ellipse.setVisible(false);
    	if(cbColor.getSelectionModel().getSelectedItem()==null||cbFigure.getSelectionModel().getSelectedItem()==null) {

			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor elige un color o figura");
			alert.setTitle("Foto de perfil.");
			alert.showAndWait();
    	}else {
    		if(cbColor.getSelectionModel().getSelectedItem().equals("ROJO")) {
    			color=Color.RED;
    		}else if(cbColor.getSelectionModel().getSelectedItem().equals("AMARILLO")) {
    			color=Color.YELLOW;
    		}else if(cbColor.getSelectionModel().getSelectedItem().equals("VERDE")) {
    			color=Color.GREEN;
    		}else if(cbColor.getSelectionModel().getSelectedItem().equals("AZUL")) {
    			color=Color.BLUE;
    		}
    		
    		if(cbFigure.getSelectionModel().getSelectedItem().equals("CIRCULO")) {
    			System.out.println("no entra");
    			figure="CIRCULO";
    			circle.setVisible(true);
    			circle.setFill(color);
    			photoPerfilOk=true;
    		}else if(cbFigure.getSelectionModel().getSelectedItem().equals("TRIANGULO")) {
    			figure="TRIANGULO";
    			triangle.setVisible(true);
    			triangle.setFill(color);
    			photoPerfilOk=true;
    		}else if(cbFigure.getSelectionModel().getSelectedItem().equals("RECTANGULO")) {
    			figure="RECTANGULO";
    			rectangle.setVisible(true);
    			rectangle.setFill(color);
    			photoPerfilOk=true;
    		}else if(cbFigure.getSelectionModel().getSelectedItem().equals("ELLIPSE")) {
    			figure="ELLIPSE";
    			ellipse.setVisible(true);
    			ellipse.setFill(color);
    			photoPerfilOk=true;
    		}
    		
    		
    	}
    	
    }
    
    @FXML
    void addAcount(ActionEvent event) {
    	if(paneAddAcount.isDisable()==true) {
    		paneAddAcount.setDisable(false);
    	}else if(paneAddAcount.isDisable()==false) {
    		paneAddAcount.setDisable(true);
    	}
    }
  
    
    @FXML
    void addNewEmployee(ActionEvent event) {
    	String te=null;
    	try {
    		 
    		//Aqui se verifica que todos los datos del empleado no estén vacíos, de lo contrario lanza Excepcion.
    		if(photoPerfilOk==false||names.getText().equals("")||lastNames.getText().equals("")||salaryMonth.getText().equals("")||telephone.getText().equals("")) {
    			throw new EmployeeDataMissingException();	
    		}		
    		//Aquí se verifica que el tipo de empleado a agregar si no necesita una cuenta de acceso no pueda crearla, de lo contrario lanza Excepcion.
    		te=typeEmployee.getSelectionModel().getSelectedItem();
    		if(te.equals("GERENTE")||te.equals("INVENTARIO")||te.equals("CAJERO")) {
    			
    		}else if((te.equals("PASILLO")||te.equals("LIMPIEZA"))&&checkCreateAcount.isSelected()==true){
    			throw new EmployeeNotNeedAccountException();
    		}
        
    		// Indica si el usuario que se está añadiendo tiene una cuenta de acceso.
    	accountYes=false;    	

    	//Se toman los datos para crear un objeto de tipo Employee, que será el empleado creado en la stage.
    	int salary=Integer.parseInt(this.salaryMonth.getText());
    	int phone=Integer.parseInt(this.telephone.getText());
    	this.employee=new Employee(this.names.getText(),this.lastNames.getText(), salary, phone, this.typeEmployee.getSelectionModel().getSelectedItem());

    	//Aquí se verifica que si están los datos de username y password cuando el panel de crear cuenta está habilitado, ya que si está visible es porque se creará una cuenta de acceso.
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
        		String photo=cbFigure.getSelectionModel().getSelectedItem()+","+cbColor.getSelectionModel().getSelectedItem();
        		Account account1=new Account(u, p, this.employee,photo);
        		this.account=account1;
    		}
    	
    	}
    	   	
    	//Si el panel de crear cuenta no está habilitado entonces no se creará una cuenta de acceso.
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
    	}catch(EmployeeNotNeedAccountException e) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("El cargo "+te+" no puede tener una cuenta de acceso.");
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

