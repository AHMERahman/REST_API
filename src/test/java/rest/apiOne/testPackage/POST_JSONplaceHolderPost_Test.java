package rest.apiOne.testPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest.apiOne.utils.Configuration;
import static org.hamcrest.Matchers.*;

public class POST_JSONplaceHolderPost_Test {

	Configuration config = Configuration.getInstance();
	File file = new File("src\\test\\resources\\post.json");
	
	@Test(enabled = false)
	public void postTestPrint() {
		RestAssured
			.given()
			.body("{\"title\" : \"name a title\", \"body\" : \"put a body\", \"userId\" : 24}")
			.contentType(ContentType.JSON)
			.when()
			.post("https://jsonplaceholder.typicode.com/posts")
			.prettyPrint();
	}
	
	@Test(enabled = false)
	public void postTestLog() {
		RestAssured
			.given()
			.body("{\"title\" : \"name a title\", \"body\" : \"put a body\", \"userId\" : 24}")
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post("https://jsonplaceholder.typicode.com/posts")
			.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test(enabled = false)
	public void postWithJsonFile() {
		//File can be declared global to make accessible to all methods
		File file = new File("src\\test\\resources\\post.json"); 
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all();
	}
	@Test(enabled = false)
	public void postWithJsonInputStream() throws FileNotFoundException {
		File file = new File("src\\test\\resources\\post.json");
		InputStream istream =new FileInputStream(file);
		RestAssured
		.given()
		.body(istream)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all();
	}
	@Test(enabled = false)
	public void postWithFileValidation() throws FileNotFoundException {
		File file = new File("src\\test\\resources\\post.json");
		InputStream istream =new FileInputStream(file);
		RestAssured
		.given()
		.body(istream)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all()
		.body("title", equalTo("How to be a software test engineer"))
		.body("body", equalTo("A guide to become a software test engineer"))
		.body("id", equalTo(101));
	}
	@Test(enabled = false)
	public void postWithFileFailedTest()  {
		File file = new File("src\\test\\resources\\post.json");
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all()
		.body("title", equalTo("How to be a software test engineer"))
		.body("body", equalTo("A guide to become a software test engineer"))
		.body("id", equalTo(201));
	}
	@Test(enabled = false)
	public void postWithNegativeTestWithIncorrectUrl()  {
		File file = new File("src\\test\\resources\\post.json");
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/pos")
		.then()
		.statusCode(404);
	}
	@Test(enabled = false)
	public void postWithNegativeTestWithIncorrectMethod()  {
		File file = new File("src\\test\\resources\\post.json");
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts/1")//this url is not for post
		.then()
		.statusCode(404);
	}
	@Test(enabled = false)
	public void postWithFileExtractResponse() {
		File file = new File("src\\test\\resources\\post.json");
		
		Response response = 
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.and()
		.extract()
		.response();
		
		System.out.println(response.asPrettyString());
	}
	@Test(enabled = true)
	public void postTestWithConfig() {
		RestAssured
			.given().baseUri(config.get("baseUrl"))
			.body(file)
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post(config.get("postsEndpoint"))
			.then()
			.statusCode(201)
			.log().all();
	}
}
