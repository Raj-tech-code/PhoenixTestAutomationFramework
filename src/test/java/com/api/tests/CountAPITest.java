package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse(){
		
		given()
		  .baseUri(getProperty("BASE_URI"))
		  .header("Authorization", getToken(FD))
		.when()
		  .get("/dashboard/count")
		.then()
		  .log().all()
		  .statusCode(200)
		  .body("message", equalTo("Success"))
		  .time(lessThan(15000L))
		  .body("data", notNullValue())
		  .body("data.size()", equalTo(3))
		  .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		  .body("data.label", everyItem(not(blankOrNullString())))
		  .body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema_FD.json"));
		  
	}
	
	
	@Test
	public void coutAPI_MissingAuthToken() {
		
		given()
		  .baseUri(getProperty("BASE_URI"))
		.when()
		  .get("/dashboard/count")
		.then()
		  .log().all()
		  .statusCode(401);
		
	}
	
}
