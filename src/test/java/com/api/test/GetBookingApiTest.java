package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class GetBookingApiTest {

	static {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

	private Header secondHeader;

	@BeforeMethod(description = "Initializing the headers")
	public void setUP() {
		secondHeader = new Header("Accept", "application/json");
	}

	@Test(description = "Api test to validate the last created booking")
	public void getCreatedBooking() {
		String firstname = RestAssured.given()

				.params("id", "1766").header(secondHeader).and().log().all().when()
				.get("/booking/" + CreateBookingApiTest.bookingId + "").then().assertThat().statusCode(200).and()
				.body("lastname", Matchers.equalTo("Brown")).and().extract().jsonPath().getString("firstname");

		System.out.println("The firstname of current booking is:" + firstname);
//		System.out.println("The lastname of current booking is: " + response.getBody().asPrettyString());
//		System.out.println("The lastname of current booking is: " + response.jsonPath().getString("lastname"));
//		System.out.println("The lastname of current booking is: " + response.statusCode());

	}

}
