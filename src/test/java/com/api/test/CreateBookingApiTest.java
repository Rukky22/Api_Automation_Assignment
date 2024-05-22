package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.Bookingdates;
import com.api.pojo.CreateBookingPojo;
import com.api.utility.TestUtility;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class CreateBookingApiTest {
	static {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

	private Header firstHeader;
	private Header secondHeader;
	public static int bookingId;

	@BeforeMethod(description = "Initializing the headers")
	public void setUP() {
		firstHeader = new Header("Content-Type", "application/json");
		secondHeader = new Header("Accept", "application/json");
	}

	@Test(description = "A create booking api request using restAssured library", groups = { "Sanity", "smoke" })
	public void createBookingApiRequest() {

		Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");

		CreateBookingPojo createBookingPojo = new CreateBookingPojo("Jim", "Brown", 111, true, bookingdates,
				"Breakfast");
		bookingId = RestAssured.given().header(firstHeader).and().header(secondHeader).and()
				.body(TestUtility.getJsonObject(createBookingPojo)).and().log().all().when().post("/booking").then()
				.log().all().assertThat().statusCode(200).and().time(Matchers.lessThan(10000L)).and()
				.body("booking.lastname", Matchers.equalTo("Brown")).and().extract().jsonPath().getInt("bookingid");

		System.out.println("The bookingId is: " + bookingId);
	}

}
