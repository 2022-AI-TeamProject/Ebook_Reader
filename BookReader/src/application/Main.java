package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;


public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root,900,700);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//icon ¼³Á¤ 
			Image icon = new Image("file:img/windowicon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("EbookReader");
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
