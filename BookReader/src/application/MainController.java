package application;
	

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController implements Initializable{
	private Button addBookbtn;
	private Button searchBtn;
	private Button settingBtn;
	// 불러올 파일을 선택할 때, 새창이 열리는 다이얼로그. 새로운 stage가 필요하다. 
	private Stage addStage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	addBookbtn.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			handleABBtnAction(event);
			}

	
		});
	}	
	public void handleABBtnAction(ActionEvent event) {
		OpenFile(event);
		
	}
	
	public void OpenFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt파일: Text Files", "*.txt"),
				new ExtensionFilter("pdf파일: Pdf Filew", "*.pdf"),
				new ExtensionFilter("그 외의 파일: All Filew", "*.*"));
		
		//File file = fc.showOpenDialog(addStage);
		//저장
		File file = fc.showOpenDialog(addStage);
		
		if(file != null) {
			//파일이 입력 받았을 때, 새로운 객체 추가 
		}
		else {
			//파일이 아무것도 입력받지 않았을 때 알람
		}
	}
	
		
	
}

