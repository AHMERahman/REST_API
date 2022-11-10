package rest.apiOne.testPackage;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import rest.apiOne.utils.Configuration;

public class GET_JSONPlaceHolderComment_Test {
	
	Configuration config = Configuration.getInstance();
	
	@Test
	
	public void getCommentWithParameterTest() {
		RestAssured
				.given()
				.baseUri(config.get("baseUrl"))
				.param("postId", 1)
				.get(config.get("commentEndpoint"))
				.prettyPrint();
	}
	

}
