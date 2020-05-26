package controlador;
//2
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		

		
		try {
			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/vista/FXMLload.fxml")); 
		Pane ventana = (Pane) loader.load();		
		Scene scene=new Scene(ventana);
		primaryStage.setTitle("Iniciar Sesión");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
