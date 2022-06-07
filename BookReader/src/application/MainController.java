 package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
	//OpenFile, �ҷ��� ������ ������ �� ���ο� stage �ʿ�  
	private Stage addStage;


    @Override
	public void initialize(URL url, ResourceBundle rb) {

    }

	//file �ҷ����� 
	public void OpenFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt����: Text Files", "*.txt"),
				new ExtensionFilter("pdf����: Pdf Filew", "*.pdf"),
				new ExtensionFilter("�� ���� ����: All Filew", "*.*"));
		
		
		
		//����
		File file = fc.showOpenDialog(addStage);
		
		//!!!!Ȯ�ο� �� ����� ��������
		//System.out.println(file);
		
		String filename ; 
		String path = "";
		
		//���õ� ���� ��� �б� 
		path = file.getParentFile().toString();
		filename = file.getName();
		
		System.out.println(filename);
		
		//������ ���õǾ��� �� 
		if(file != null) {
			
			//�ҷ����� ������ ������ ��ư�� �̸�. ���ο� ��ư ����(å) 
			Button newButton = new Button();
			newButton.setText(filename);
			
			//�ڵ� �����ؾ��� 
			
			try {
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
		
			}
			
			catch(FileNotFoundException fne) {
				Alert nullAlert = new Alert(AlertType.WARNING);
				nullAlert.setTitle("Warning Dialog");
				nullAlert.setHeaderText("������ �ҷ��� �� �����ϴ�.");
				nullAlert.setContentText("�ҷ����� ������ �ٽ� Ȯ���ϼ���.");
			}
			
		}
		//������ �ƹ��͵� �Է¹��� �ʾ��� �� �˶�
		else {
				Alert nullAlert = new Alert(AlertType.WARNING);
				nullAlert.setTitle("Warning Dialog");
				nullAlert.setHeaderText("������ �ҷ��� �� �����ϴ�.");
				nullAlert.setContentText("�ҷ����� ������ �ٽ� Ȯ���ϼ���.");
			
		}
	}
	
 
	//view fxml �� �̵� (���� �ʿ�)
	public void ViewMove(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml")); 
		Parent root;
		try {
	
			root = (Parent) loader.load();
			Stage stage = new Stage();
			//!!!! file �̸����� setting ���� �ʼ�  
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
	//view fxml �� �̵� (���� �ʿ�)
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
	
	
	public void OpenUnderMenu(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("testView.fxml")); 
		Parent root;
		try {
			root = (Parent) loader.load();
			Stage stage = new Stage();
		    stage.setTitle("fatigueSociety");
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
		    //�Ʒ� ���콺 ���� �޴� 
		    VBox menu = new VBox();
		    menu.setId("underMenu");
		    menu.prefWidthProperty();
		    //���δ� �� ���� (��?)
		    //menu.prefWidthProperty().bind(root.widthProperty());
		    menu.setPrefHeight(40);
		    menu.getChildren().addAll(new Button("correct!"));
		    
		    menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    menu.setTranslateX(-90);
		    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu);
		    menuTranslation.setFromX(-190);        
		    menuTranslation.setToX(0);    
		    
		    menu.setOnMouseEntered(evt -> {            
		    	menuTranslation.setRate(1);            
		    	menuTranslation.play();        });   
		    
		    menu.setOnMouseExited(evt -> {            
		    	menuTranslation.setRate(-1);            
		    	menuTranslation.play();        });

		    //root.getChildren().addAll(menu);
		    
		    stage.setScene(scene);
		    stage.show();
	    
		} catch (IOException tve) {
			tve.printStackTrace();
		}
	}
	
	//��ũ ��� 
	public void Darkmode() {}
	
	//�ϸ�ũ �ѱ� 
	public void BookMarkOn() {
		
	}
	
	//�ϸ�ũ ���� 
	public void BookMarkOff() {
		
	}
}

