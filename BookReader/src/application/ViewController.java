 package application;


import java.io.BufferedReader;
import java.io.DataOutputStream;
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
import java.util.Set;

import javax.naming.Context;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import application.Book;

public class ViewController implements Initializable{
	@FXML MenuItem transKE = new MenuItem();
	
	//번역 구간 
	@FXML TextArea leftTA = new TextArea();
	@FXML TextArea rightTA = new TextArea();
	
	String getText1, getText2;
	String resultText1, resultText2;
	

	
	 //book 내용 받음
	 public static void BookIn(Book book) {
		 String Context = book.contextB;
		   
		 //내용 전달 확인용 출력
		 System.out.println(Context);
	 }
	   
    @Override
	public void initialize(URL url, ResourceBundle rb) {

    	
    }
    
 
    
    public void setTextArea(String context) {
    	
    	
    	
    }
    
    public void Translation(ActionEvent e) {

    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	System.out.println(getText1);
    	
    	trans(getText1, null);
    	//trans(tx2.getText(), null);
    	
    }
    
    
    public static void trans(String text, String[] args) {

    	//papago api 번역
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

