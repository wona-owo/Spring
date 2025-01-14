package kr.or.iei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	
	@GetMapping("/publicData")
	public String movePage(String reqPage) {
		return "publicData/"+reqPage;
	}
	
	
	//부산 맛집 서비스 - XML - Jsoup 라이브러리 활용 : 웹 파싱 전용 라이브러리
	@GetMapping(value = "/busanFoodXml", produces="application/json; charset=utf-8")
	@ResponseBody
	public String busanFoodXml(String reqPage) {
		
		//JSP로 응답할 JSON 문자열
		String resJsonStr = "";
		
		//맛집 정보 서비스를 제공하는 URL
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		
		//맛집 정보 서비스를 사용하기 위한 serviceKey
		String serviceKey ="fN4/aXC3o3571K3RYH1usEAtwnRkagse8eQV2zkqJwavW+zCv72U8yAc8eKdkcxntJkUohPg6sel4VcC2vWMqg==";
		
		//전달해야 하는 파라미터 전달
		try {
		Document document = Jsoup.connect(url)
				.data("serviceKey", serviceKey)
				.data("numOfRows","10") //줄수
				.data("pageNo", reqPage) //몇번째 페이지
				.ignoreContentType(true) //응답형식을 무시하고 파싱하겠다.
				.get(); //get방식 - 요청방식 전달
		
		Elements elements =  document.select("item"); //응답데이터 중, item 태그만 선택
		//응답 데이터 처리할 List 객체형인 elements
		ArrayList<FoodPlace> placeList = new ArrayList<FoodPlace>();
		
		for(int i=0; i<elements.size(); i++) {
			Element element = elements.get(i);
			
			//Jsoup 장점!! 선택자 선택하기 편함
			//java에서 제공하는 기본 클래스인 org.w3c.dom(xml전용)로도 xml파싱 가능
			//매개변수에 선택자를 작성할 수 있다(css선택자도 선택 가능, XML 선택자 작성)
			String placeTitle = element.select("MAIN_TITLE").get(0).text();
			String placeTel = element.select("CNTCT_TEL").get(0).text();
			String placeHour = element.select("USAGE_DAY_WEEK_AND_TIME").get(0).text();
			String placeAddr = element.select("ADDR1").get(0).text();
			String placeImg = element.select("MAIN_IMG_THUMB").get(0).text();
			
			
			FoodPlace place = new FoodPlace(placeTitle, placeTel, placeHour, placeAddr, placeImg);
			placeList.add(place);
		}
		
		//자바 -> 자바스크립트 변환	
		Gson gson = new Gson();
		resJsonStr = gson.toJson(placeList);
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return resJsonStr;
	}
	
	//부산 맛집 서비스 - XML
		@GetMapping(value = "/busanFoodXml2", produces="application/json; charset=utf-8")
		@ResponseBody
		public String busanFoodXml2(String reqPage) {
			//JSP로 응답할 JSON 문자열
			String resJsonStr = "";
			
			//맛집 정보 서비스를 제공하는 URL
			String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
			
			//맛집 정보 서비스를 사용하기 위한 serviceKey - 자동 인코딩이 안되어서 인코딩 된거 쓰기
			String serviceKey ="fN4%2FaXC3o3571K3RYH1usEAtwnRkagse8eQV2zkqJwavW%2BzCv72U8yAc8eKdkcxntJkUohPg6sel4VcC2vWMqg%3D%3D";
		
			//url 뒤에 파라미터 붙여 전달
			url += "?serviceKey="+serviceKey;
			url += "&numOfRows=10";
			url += "&pageNo"+reqPage;
			
					
			try {
				//데이터 요청 및 응답
				org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
				
				//응답 XML 중에서, 루트 태그 선택
				doc.getDocumentElement().normalize();
			
				//응답 XML 중, item 태그들만 선택
				NodeList nodeList = doc.getElementsByTagName("item");
				ArrayList<FoodPlace> placeList = new ArrayList<FoodPlace>();
			
				for(int i=0; i<nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					org.w3c.dom.Element el = (org.w3c.dom.Element) node;
				
					String placeTitle = el.getElementsByTagName("MAIN_TITLE").item(0).getTextContent();
					String placeTel = el.getElementsByTagName("CNTCT_TEL").item(0).getTextContent();
					String placeHour = el.getElementsByTagName("USAGE_DAY_WEEK_AND_TIME").item(0).getTextContent();
					String placeAddr = el.getElementsByTagName("ADDR1").item(0).getTextContent();
					String placeImg = el.getElementsByTagName("MAIN_IMG_THUMB").item(0).getTextContent();
				
					FoodPlace place = new FoodPlace(placeTitle, placeTel, placeHour, placeAddr, placeImg);
					placeList.add(place);
				}
				
				resJsonStr = new Gson().toJson(placeList);
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			return resJsonStr;
		}
		
		@GetMapping(value="/financial", produces="application/json; charset=utf-8")
		@ResponseBody
		public String financial() {
			
			String resData ="";
			
			String reqUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
			reqUrl += "?authkey=5E2uKAxK2LqCjNZiI9uvyj1rMM2HRLD9";
			reqUrl += "&searchdate=20241218";
			reqUrl += "&data=AP01";
			
			
			URL url;
			try {
				url = new URL(reqUrl);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();	
				
				int responseCode = conn.getResponseCode(); //Http 통신 여부에 따라 응답코드 추출
				
				if(responseCode == HttpsURLConnection.HTTP_OK) { //응답코드가 200인지 -> 정상적으로 통신이 되었는지
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String inputLine;
					StringBuilder resStr = new StringBuilder();
					
					while((inputLine = in.readLine()) != null) { //null이 아닌동안 한줄씩 데이터를 읽는다
						resStr.append(inputLine);				
					}
					
					in.close();
					
					//JAVA 객체로서의 DB 저장, 파싱 진행 : 파싱할 String 매개변수 전달
					JsonArray jsonArr = JsonParser.parseString(resStr.toString()).getAsJsonArray();
					
					String [] resArr = new String[jsonArr.size()];
					
					for(int i=0; i<jsonArr.size(); i++) {
						
						JsonObject jsonObj = (JsonObject) jsonArr.get(i);
						
						String unit = jsonObj.get("cur_unit").getAsString();
						String bas = jsonObj.get("deal_bas_r").getAsString();
						String nm = jsonObj.get("cur_nm").getAsString();				
						
						String oneObj = "국가/통화명 :" + nm +", 통화코드 :" + unit + ", 매매기준율 :" + bas;
						
						resArr[i] = oneObj;
					}
					
					resData = new Gson().toJson(resArr);
				}
				
				
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return resData;
		}
}
