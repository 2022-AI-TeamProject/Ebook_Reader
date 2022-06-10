 package application;

import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewController implements Initializable{
	//����
	TextArea leftTA = new TextArea();
	TextArea rightTA = new TextArea();
	
	String getText1, getText2;
	String resultText1, resultText2;


    @Override
	public void initialize(URL url, ResourceBundle rb) {

    }

    
    public void Translation(ActionEvent e) {
    	
    	//setText으로 지정한 텍스트만 불러올 수 있음, 기존의 테스트용 글자는 불러오지 못 함
    	leftTA.setText("감자");
    	
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	System.out.println(getText1);
    	
    	trans(getText1, null);
    	//trans(tx2.getText(), null);
    	
    }
    
    
    public static void trans(String text, String[] args) {
    	TextArea tx = new TextArea();
    	TextArea tx2 = new TextArea();
    	
		//���̹� ���İ� ���� 
        String clientId = "c7e6eTDx_8KNaF3VNLjj";//애플리케이션 클라이언트 아이디 값
        String clientSecret = "pS2HFuttkj";//애플리케이션 클라이언트 시크릿값
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        try {
        	text = URLEncoder.encode(text, "UTF-8"); 
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("���ڵ� ����", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text);

        System.out.println(responseBody);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어(ko) -> 목적언어: 영어 (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못 되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패하였습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
	}
	
	//다크모드
	public void Darkmode(ActionEvent e) {
		
	}
	
	//북마크 켜기
	public void BookMarkOn(ActionEvent e) {
		
	}
	
	//북마크 끄기 
	public void BookMarkOff(ActionEvent e) {
		Button button = new Button();
		button.setVisible(false);
	}
}

