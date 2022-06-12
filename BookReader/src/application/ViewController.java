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

import javax.lang.model.element.Element;
import javax.naming.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.control.Button;
 import javafx.scene.control.MenuItem;
 import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import application.MainController;

public class ViewController implements Initializable{
	
	//메뉴(부종목) 기능 
	@FXML MenuItem DarkModeBtn = new MenuItem();
	@FXML Button searchButton= new Button();
	@FXML TextField textField = new TextField();
	
	//번역 버튼 
	@FXML MenuItem transKE = new MenuItem();
	@FXML BorderPane bp = new BorderPane();
	
	//번역 구간 
	@FXML TextArea leftTA = new TextArea();
	@FXML TextArea rightTA = new TextArea();
	
	String getText1, getText2;
	String resultText1, resultText2;

	static String context;

	//book.context 받아옴 
	public String viewController(String contextB) {
		this.context =  contextB;
		return context;
	}
	 
	
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	//context확인용 
    	System.out.println(viewController(context));
    	
    	//text area를 context에 입력 
    	leftTA.setText(viewController(context));
    
    	
    	//text area에서 검색 
    	searchButton.setOnMouseClicked(e -> {    		    		
    		MouseButton button = e.getButton();
    		
    		//text field에서 하이라이트 표시 검색 
    		if(button == MouseButton.PRIMARY) {
    			if(textField.getText() != null && !leftTA.getText().isEmpty()) {
                    int index = leftTA.getText().indexOf(textField.getText()); 
                    if (index == -1) {
                        textField.setText("검색 결과가 없습니다.");
                    } else {   
                        leftTA.selectRange(index, index + textField.getLength());
                    }       
                } else {
                	textField.setText("검색할 문자가 없습니다.");
                }
    		}
    	});
    	
    }
    	
    
	 
    //번역
    
    //한->영
    public void Translation(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(trans(getText1, null));
    }
    
    //영->한 
    public void TranslationEK(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(transEK(getText1, null));
    }
    
    //한->일
    public void TranslationKJ(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(transKJ(getText1, null));
    }

    //일->한
    public void TranslationJK(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(transJK(getText1, null));
    }
    
    //한->중(간체)
    public void TranslationKC(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(transKC(getText1, null));
    }

    public void TranslationCK(ActionEvent e) { 
    	//TextArea안에 text 불러오기  
    	getText1 = leftTA.getText();
    	getText2 = rightTA.getText();
    	
    	leftTA.setText(transCK(getText1, null));
    }
    
    public static String trans(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(post(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
        

    }

    public static String transEK(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(postEK(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
    }
    
    public static String transKJ(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(postKJ(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
    }
    
    public static String transJK(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(postJK(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
    }

    public static String transKC(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(postKC(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
    }

    public static String transCK(String text, String[] args) {

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

        //gson이용해서 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(postCK(apiURL, requestHeaders, text));
        String transenten = (element.getAsJsonObject().get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString());
        return transenten;
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

    private static String postEK(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=en&target=ko&text=" + text; //원본언어: 영어 -> 목적언어: 한국어
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
    
    private static String postKJ(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=ja&text=" + text; //원본언어: 한국어(ko) -> 목적언어: 일본어 (ja)
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
    
    private static String postJK(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ja&target=ko&text=" + text; //원본언어: 일본어 -> 목적언어: 한국어 
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
    
    private static String postKC(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=zh-CN&text=" + text; //원본언어: 한국어 -> 목적언어: 중국어 
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

    private static String postCK(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=zh-CN&target=ko&text=" + text; //원본언어: 중국어(간체) -> 목적언어: 한국어 
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

