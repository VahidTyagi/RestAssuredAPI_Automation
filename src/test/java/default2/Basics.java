package default2;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import files.ReUsableMethods;
import files.payload;

import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if Add Place is working as expected
		// Given - All input details
		// When - Submit the API - resource and http method
		// Then - Validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		
		// update place
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")	
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();	
		
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
		// testng and junit are testing framework, assertion is used to validate the test case, if assertion is true then test case is passed otherwise it will be failed
		
		assert actualAddress.equals(newAddress);
		

	}

}
