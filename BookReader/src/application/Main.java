package application;
	
import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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



public class Main extends Application {
	public MainController mc;
	final ScrollBar scrollBar = new ScrollBar();
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		try {
			
			
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Home.fxml"));	
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root,900,700); 
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//icon 설정 
			Image icon = new Image("file:img/windowicon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("EbookReader");
			
			//scene 설정
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
			
			//scroll 설정
			scrollBar.setLayoutX(scene.getWidth() - scrollBar.getWidth());
			scrollBar.setMin(0);
			scrollBar.setOrientation(Orientation.VERTICAL);
			scrollBar.setPrefHeight(900);
			scrollBar.setMax(360);

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void trans(String[] args) {
		//네이버 파파고 번역 
        String clientId = "c7e6eTDx_8KNaF3VNLjj";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "pS2HFuttkj";//애플리케이션 클라이언트 시크릿값";

        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
        	text = URLEncoder.encode("카프카는 대단히 난해한 단편 '프로메테우스'에서 몇 차례에 걸쳐 프로메테우스 신화의 재해석 작업을 수행한다.", "UTF-8"); 
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text);

        System.out.println(responseBody);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
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
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
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
	

}
