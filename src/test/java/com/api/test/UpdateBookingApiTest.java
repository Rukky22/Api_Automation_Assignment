package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.Bookingdates;
import com.api.pojo.CreateBookingPojo;
import com.api.utility.TestUtility;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class UpdateBookingApiTest {

	static {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

	private Header contentHeader;
	private Header acceptHeader;
	private Header cookiesHeader;
	private String addtionalneeds;

	@BeforeMethod(description = "Initializing the headers")
	public void setUP() {
		contentHeader = new Header("Content-Type", "application/json");
		acceptHeader = new Header("Accept", "application/json");
		cookiesHeader = new Header("cookie", "token=" + GenerateAuthorizationToken.token);
	}

	@Test(description = "Update the previously created booking", groups = { "Sanity", "smoke" })
	public void updateBookingApiRequest() {

		Bookingdates bookingdates = new Bookingdates("2018-01-01", "2024-05-21");

		CreateBookingPojo createBookingPojo = new CreateBookingPojo("Jim", "Brown", 111, true, bookingdates, "Lunch");
		addtionalneeds = RestAssured.given().header(contentHeader).and().header(acceptHeader).and()
				.header(cookiesHeader).body(TestUtility.getJsonObject(createBookingPojo)).and().log().all().when()
				.put("/booking/" + CreateBookingApiTest.bookingId + "").then().log().all().assertThat().statusCode(200)
				.and().time(Matchers.lessThan(10000L)).and().body("lastname", Matchers.equalTo("Brown")).and().extract()
				.jsonPath().getString("additionalneeds");

		System.out.println("The Addtionalneeds is: " + addtionalneeds);
	}

}
