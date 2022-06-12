package application;
	
import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	
	public MainController mc;
	@FXML MenuItem DarkModeBtn = new MenuItem();
	
	private String lightTheme = getClass().getResource("application.css").toExternalForm();
	private String DarkTheme = getClass().getResource("darkmode.css").toExternalForm();
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		try {
			
			
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Home.fxml"));	
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root,900,700); 
			
			scene.getStylesheets().add(lightTheme);

			//fxml에서 menuitem과 매핑이 안 되어있음. 
			DarkModeBtn.setOnAction(a -> {
				scene.getStylesheets().clear();
				setUserAgentStylesheet(null);
				scene.getStylesheets().add(DarkTheme);
			});
		

			//icon 설정
			Image icon = new Image("file:img/windowicon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("EbookReader");
			
			//scene 설정, 크기 변환 불가 
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	

	
	
	

}
