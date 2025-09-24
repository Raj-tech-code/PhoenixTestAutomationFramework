package com.api.tests;

import static io.restassured.RestAssured.*;

import org.apache.http.auth.UsernamePasswordCredentials;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static com.api.utils.ConfigManager.*;
import com.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//RestAssured code
		
		
		UserCredentials usercredBody = new UserCredentials("iamfd", "password");
		
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .and()
		 .contentType(ContentType.JSON)
		 .and()
		 .body(usercredBody)
		 .log().uri()
		 .log().method()
		 .log().headers()
		 .log().body()
		.when()
		 .post("login")
		.then()
		 .log().all()
		 .statusCode(200)
		 .time(lessThan(7000L))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
		 
		
	
	}
	
}
