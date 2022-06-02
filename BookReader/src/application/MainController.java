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
	// �ҷ��� ������ ������ ��, ��â�� ������ ���̾�α�. ���ο� stage�� �ʿ��ϴ�. 
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
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt����: Text Files", "*.txt"),
				new ExtensionFilter("pdf����: Pdf Filew", "*.pdf"),
				new ExtensionFilter("�� ���� ����: All Filew", "*.*"));
		
		//File file = fc.showOpenDialog(addStage);
		//����
		File file = fc.showOpenDialog(addStage);
		
		if(file != null) {
			//������ �Է� �޾��� ��, ���ο� ��ü �߰� 
		}
		else {
			//������ �ƹ��͵� �Է¹��� �ʾ��� �� �˶�
		}
	}
	
		
	
}

