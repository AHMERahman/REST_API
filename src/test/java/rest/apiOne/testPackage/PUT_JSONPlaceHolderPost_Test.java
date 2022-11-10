package rest.apiOne.testPackage;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import rest.apiOne.utils.Configuration;

public class PUT_JSONPlaceHolderPost_Test {
	
	Configuration config = Configuration.getInstance();
	
	Map<String, Object> map = new HashMap<String, Object>();
	public Map<String, Object> getMap(){
		map.put("id", 4);
		map.put("title", "I want to be a great man");
		map.put("body", "Be kind");
		map.put("userId", 4);
		return map;
	}
	
	@Test(enabled=false)
	
	public void putCall() {
		getMap();
		RestAssured
		.given()
		.baseUri(config.get("baseUrl"))
		.contentType(ContentType.JSON)
		.body(map)
		.log().all()
		.put(config.get("postsEndpoint")+"/"+map.get("id"))  //.prettyPrint()
		.then()
		.log().all();
			
	}
	
//	2nd method
	@Test
	public void putCall2() {
		
		RestAssured
		.given()
		.baseUri(config.get("baseUrl"))
		.contentType(ContentType.JSON)
		.body(getMap())
		.log().all()
		.put(config.get("postsEndpoint")+"/"+map.get("id"))  //.prettyPrint()
		.then()
		.log().all();
	}
}
