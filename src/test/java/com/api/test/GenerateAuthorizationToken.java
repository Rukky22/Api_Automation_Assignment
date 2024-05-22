package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.AuthorizationPojo;
import com.api.utility.TestUtility;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class GenerateAuthorizationToken {

	static {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

	private Header contentHeader;
	public static String token;

	@BeforeMethod(description = "Initializing the headers")
	public void setUP() {
		contentHeader = new Header("Content-Type", "application/json");
	}

	@Test(description = "A simple test to generate the authorization token")
	public void generateToken() {

		AuthorizationPojo tokenPojo = new AuthorizationPojo("admin", "password123");
		token = RestAssured.given().header(contentHeader).and().body(TestUtility.getJsonObject(tokenPojo)).and().log()
				.all().when().post("/auth").then().log().all().assertThat().statusCode(200).and().extract().jsonPath()
				.getString("token");

		System.out.println("The token is: " + token);
	}
}
