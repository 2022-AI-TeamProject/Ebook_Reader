package application;
	

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

//import com.jfoenix.controls.JFXButton;

public class MainController implements Initializable{
	//OpenFile, 불러올 파일을 선택할 때 새로운 stage 필요  
	private Stage addStage;

	//ViewSlider (아래)
	//private JFXButton showSlide;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
  
	}	

	
	public void OpenFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt파일: Text Files", "*.txt"),
				new ExtensionFilter("pdf파일: Pdf Filew", "*.pdf"),
				new ExtensionFilter("그 외의 파일: All Filew", "*.*"));
		
		//저장
		File file = fc.showOpenDialog(addStage);
		
		//!!!!확인용 다 만들고 지워야함
		System.out.println(file);
		
		String filename ; 
		String path = "";
		
		path = file.getParentFile().toString();
		filename = file.getName();
		
		System.out.println(filename);
		if(file != null) {
			//불러들인 파일의 제목이 버튼의 이름. 새로운 버튼 생성(책) 
			
			Button newButton = new Button();
			newButton.setText(filename);
			
			try {
				//Button newBookbtn = new Button();

				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
		
			}
			
			catch(FileNotFoundException fne) {
				Alert nullAlert = new Alert(AlertType.WARNING);
				nullAlert.setTitle("Warning Dialog");
				nullAlert.setHeaderText("파일을 불러올 수 없습니다.");
				nullAlert.setContentText("불러들일 파일을 다시 확인하세요.");
			}
			
		}
		else {
				//파일이 아무것도 입력받지 않았을 때 알람
				Alert nullAlert = new Alert(AlertType.WARNING);
				nullAlert.setTitle("Warning Dialog");
				nullAlert.setHeaderText("파일을 불러올 수 없습니다.");
				nullAlert.setContentText("불러들일 파일을 다시 확인하세요.");
			
		}
	}
	
 
	//view fxml 로 이동 (수정 필요)
	public void ViewMove(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml")); 
		Parent root;
		try {
	
			root = (Parent) loader.load();
			Stage stage = new Stage();
			//!!!! file 이름으로 setting 설정 필수  
		    stage.setTitle("EBookReader");
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    stage.setScene(scene);
		    stage.show();
			
			
		} catch (IOException ve) {
			ve.printStackTrace();
		} 
	}
	

	//test 
	//view fxml 로 이동 (수정 필요)
	public void TestViewMove(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("testView.fxml")); 
		Parent root;
		try {
			root = (Parent) loader.load();
			Stage stage = new Stage();
		    stage.setTitle("fatigueSociety");
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    stage.setScene(scene);
		    stage.show();
		} catch (IOException tve) {
			tve.printStackTrace();
		}
	    
	
	}
	
	public void OpenSideMenu(ActionEvent e) {
		
		
	}
	
}

