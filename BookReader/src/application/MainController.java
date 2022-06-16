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
import java.util.Set;

import javax.swing.text.AbstractDocument.BranchElement;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

import application.Book;

public class MainController implements Initializable{
	
	//book add btn
  	@FXML private Button addButton;

  	//OpenFile, 불러올 파일을 선택할 때 새로운 stage 필요  
	private Stage addStage;
	
	//메뉴(부종목) 기능 
	@FXML MenuItem DarkModeBtn = new MenuItem();
	@FXML Button searchButton = new Button();
	
  	//Grid layout 
	@FXML GridPane grid;
  	@FXML ColumnConstraints gridzeroH;
  	@FXML ColumnConstraints gridfirstH;
  	@FXML ColumnConstraints gridsecondH;
  	@FXML ColumnConstraints gridthirdH;
  	@FXML ColumnConstraints gridfourH;
  	
	
  	//new bookbtn image
  	Image bookImage = new Image("file:img/bookbutton.png");

    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	//파일 추가 버튼 
    	addButton.setOnMouseClicked(EventHandler ->{
    		FileChooser fc = new FileChooser();
    		fc.getExtensionFilters().addAll(new ExtensionFilter("txt파일: Text Files", "*.txt"),
    				new ExtensionFilter("pdf파일: Pdf Filew", "*.pdf"),
    				new ExtensionFilter("그 외의 파일: All Filew", "*.*"));
    		
    		
    		
    		//저장 (확인 필요) 
    		File file = fc.showOpenDialog(addStage);
    		
    		String fileName = "" ; 
    		String fileContext = "" ; 
    		String path = "";
    		
    		//선택된 파일 경로 읽기 
    		path = file.getParentFile().toString();
    		fileName = file.getName();
    		System.out.println(fileName);
    		
    		//파일이 선택되었을 때 
    		if(file != null) {
    			
    			//Book 객체 생성
    			Book book = new Book("", "");
    			
    			try {
    				
    				//파일 내용 읽어오기 
    				FileInputStream fis = new FileInputStream(file);
    				BufferedReader bis = new BufferedReader(new InputStreamReader(fis));
    				String line; 
    				
    				while((line = bis.readLine()) != null) {
    					fileContext+=line;
    				}

    
    				//코드 실행 확인용
    				//System.out.println(fileContext);
    				
    				//Book 객체 이름, 내용 설정 
    				book.titleB = fileName;
    				book.contextB = fileContext;
    				
    				//불러들인 파일의 제목이 버튼의 이름. 새로운 버튼 생성 -> 책
    				Button newBookbtn = new Button();
    				newBookbtn.setText(fileName);
    				newBookbtn.setMinHeight(166.0);
    				newBookbtn.setMinWidth(123.0);
    				newBookbtn.setStyle(" -fx-border-radius: 0; -fx-background-image: url(\"file:img/bookbutton.png\"); -fx-background-position: center center; -fx-background-repeat: no-repeat; -fx-background-color: #e0dddc; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 10, 0 , 2 , 2 );" );
    				
    				//gridlayout 위치 배정 
    				grid.add(newBookbtn, 1, 0);
    				
    				//본문 검사
    				Button checkingbtn = new Button();
    				checkingbtn.setText("checking");
    				checkingbtn.setMinHeight(30.0);
    				checkingbtn.setMinWidth(50.0);
    				grid.add(checkingbtn, 1, 0);

    				
    				//버튼 클릭 마우스 이벤트 
    		    	newBookbtn.setOnMouseClicked(e -> {    		    		
    		    		MouseButton button = e.getButton();
    		    		//왼쪽 마우스 클릭시 reader 새 창 
    		    		if(button == MouseButton.PRIMARY) {
    		    			TestViewMove(book, e);
    		    		}
    		    		//오른쪽 마우스 클릭시 삭제 
    		    		else if(button == MouseButton.SECONDARY) {
    		    			//클릭 확인용 
    		    			System.out.println("delete");
    		    			DeleteBook(book);
    		    			grid.getChildren().remove(checkingbtn);
    		    			grid.getChildren().remove(newBookbtn);
    		    		}
    		    	});
    		    	
    		    	checkingbtn.setOnMouseClicked(e->{
    		    		Alert checkingAlert = new Alert(AlertType.WARNING);
    		    		checkingAlert.setTitle("수위 판별");
    		    		checkingAlert.setHeaderText("내용 분석 결과입니다.");
    		    		checkingAlert.setContentText("이 책의 주요 키워드는 배심원 동정, 무죄 변호사 도울, 홈즈 통찰 실망, 지식 오직 철부지, 해결 보고서 감탄 입니다. \n 피의 표현- 35 번 \n 사지의 표현- 1 번\n 시체의 표현은- 1 번");
    		    		checkingAlert.showAndWait();
    		    	});
    		    	
    		    
    		    	
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
    		
    	});
    	
    }
    

	
	//reader 불러오기(새 창)
	public void TestViewMove(Book book, MouseEvent e) {

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("testView.fxml")); 
		Parent root;
		Image icon = new Image("file:img/windowicon.png");
		
		Pane pane = new Pane();
		try {
			root = (Parent) loader.load();
			
			Stage stage = new Stage();
			stage.getIcons().add(icon);
			stage.setResizable(false);
		    stage.setTitle(book.Booktitle());
		    
			
			//book 전달
		    ViewController viewController = new ViewController();
		    viewController.viewController(book.contextB);
		    
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
		    stage.setScene(scene);
		    stage.show();
		        
		} catch (IOException tve) {
			tve.printStackTrace();
		}
	}


	//책 버튼 삭제 
	public void DeleteBook(Book book) {
		book = null;
	}
	
	//다크모드
	public void Darkmode(ActionEvent e) {
		
		
	}
	




}
