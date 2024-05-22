package com.api.test;

import io.restassured.RestAssured;

public class BaseTest {
	public void setup() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

}
