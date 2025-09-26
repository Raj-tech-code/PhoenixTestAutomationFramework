package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		
		given()
		   .baseUri(getProperty("BASE_URI"))
		   .header("Authorization",getToken(FD))
		   .contentType("")
		   .log().all()
		.when()
		   .post("master")
		.then()
		   .log().all()
		   .statusCode(200)
		   .time(lessThan(10000L))
		   .body("message", equalTo("Success"))
		   .body("data", notNullValue())
		   .body("data", hasKey("mst_oem"))
		   .body("$", hasKey("message"))
		   .body("$", hasKey("data"))
		   .body("data.mst_oem.size()", greaterThan(0))
		   .body("data.mst_model.size()", equalTo(3))
		   .body("data.mst_oem.id", everyItem(notNullValue()))
		   .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponse_FD.json"));
		   
		   
		
	}
	
	
	@Test
	public void invalidTokenMasterAPI() {
		
		given()
		   .baseUri(getProperty("BASE_URI"))
		   .header("Authorization","")
		   .contentType("")
		   .log().all()
		.when()
		   .post("master")
		.then()
		   .log().all()
		   .statusCode(401);
		
	}
	
	
	
	
	
	
	
	

}
