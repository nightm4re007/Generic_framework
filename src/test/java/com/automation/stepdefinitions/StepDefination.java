package com.automation.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.asserts.SoftAssert;

import com.automation.payloadbuilders.TestDataBuild;
import com.automation.testbase.testbase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class StepDefination extends testbase {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	

@Given("^Add Place Payload with (.*) (.*) (.*)$")
public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
		 
		 res=given().spec(requestSpecification())
		.body(data.addPlacePayLoad(name,language,address));
	} 

@When("^user calls with (.*) http request (.*)$")
public void user_calls_with_http_request(String method, String resource) {
	    // Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
	/*	APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());*/
		
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resource);
		else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resource);
		
}

	@Then("^the API call got success with status code (.*)$")
	public void the_API_call_got_success_with_status_code(Integer int1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	
			assertEquals(response.getStatusCode(),20,"Actuals did not match with expected::");
		/*	String resp = response.asString();
			JsonPath js = new JsonPath(resp);
			int len=js.getInt("courses.size()");
			for (int i = 0; i < len; i++) {
			String text=	js.get("courses['"+i+"'].sub");
			if(text.equals(""))
			{
				break;
			}*/
			}
			//return js.get(key).toString();
	
		
	
	}

	/*@Then("^(.*) in response body is (.*)")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	  
	 assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}*/
	
	/*@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
	   // requestSpec
	     place_id=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);
		 
	    
	}*/
	

/*@Given("DeletePlace Payload")
public void deleteplace_Payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
   
	res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
}*/




