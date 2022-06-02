package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	public MainController mc;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {	
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Home.fxml"));	
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root,900,700);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//icon ���� 
			Image icon = new Image("file:img/windowicon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("EbookReader");
			
			//scene ����
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
			
	
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
