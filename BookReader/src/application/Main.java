package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;



public class Main extends Application {
	public MainController mc;
	final ScrollBar scrollBar = new ScrollBar();

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		try {
			
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Home.fxml"));	
			//Parent root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root,900,700); 
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//icon 설정
			Image icon = new Image("file:img/windowicon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("EbookReader");
			
			//scene 설정, 크기 변환 불가 
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			//scroll 설정 (수정 필요) 
			scrollBar.setLayoutX(scene.getWidth() - scrollBar.getWidth());
			scrollBar.setMin(0);
			scrollBar.setOrientation(Orientation.VERTICAL);
			scrollBar.setPrefHeight(900);
			scrollBar.setMax(360);
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
