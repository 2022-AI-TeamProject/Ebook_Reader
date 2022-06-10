 package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.AbstractDocument.BranchElement;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable{
	//OpenFile, 불러올 파일을 선택할 때 새로운 stage 필요  
	private Stage addStage;


    @Override
	public void initialize(URL url, ResourceBundle rb) {

    }

    
	//file 불러오기 
	public void OpenFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt파일: Text Files", "*.txt"),
				new ExtensionFilter("pdf파일: Pdf Filew", "*.pdf"),
				new ExtensionFilter("그 외의 파일: All Filew", "*.*"));
		
		
		
		//저장
		File file = fc.showOpenDialog(addStage);
		
		String fileName, fileContext = null ; 
		String path = "";
		
		//선택된 파일 경로 읽기 
		path = file.getParentFile().toString();
		fileName = file.getName();
		System.out.println(fileName);
		
		//파일이 선택되었을 때 
		if(file != null) {
			
			//불러들인 파일의 제목이 버튼의 이름. 새로운 버튼 생성(책) 
			Button newButton = new Button();
			newButton.setText(fileName);
			
			//코드 구현해야함 
			
			try {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader bis = new BufferedReader(new InputStreamReader(fis));
				String line; 
				while((line = bis.readLine()) != null) {
					fileContext+=line;
				}
				System.out.println(fileContext);
		
			}
			
			catch(FileNotFoundException fne) {
				Alert nullAlert = new Alert(AlertType.WARNING);
				nullAlert.setTitle("Warning Dialog");
				nullAlert.setHeaderText("파일을 불러올 수 없습니다.");
				nullAlert.setContentText("불러들일 파일을 다시 확인하세요.");
			}
			catch(IOException fre) {
				fre.printStackTrace();
			}
			
		}
		//파일이 아무것도 입력받지 않았을 때 알람
		else {
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
		Pane pane = new Pane();
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
	
	//다크 모드 
	public void Darkmode(ActionEvent e) {

		
	}
	


}

