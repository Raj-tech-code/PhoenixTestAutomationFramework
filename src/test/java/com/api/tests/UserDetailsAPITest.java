package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.AuthTokenProvider;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
    @Test
	public void userDetailsAPITest() throws IOException {
		
    	
		Header authHeader = new Header("Authorization", AuthTokenProvider.getToken(FD));
		
		given()
		  .baseUri(getProperty("BASE_URI"))
		  .header(authHeader)
		  .accept(ContentType.JSON)
		  .log().all()
		.when()
		  .get("userdetails")
		.then()
		  .log().all()
		  .statusCode(200)
		  .time(lessThan(7000L))
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserdetailsResponseSchema.json"));
		
	}
	
}
