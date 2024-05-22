package com.api.utility;

import com.api.pojo.Bookingdates;
import com.api.pojo.CreateBookingPojo;
import com.google.gson.Gson;

public class Runner1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
		CreateBookingPojo bookingPojo = new CreateBookingPojo("Jim", "Brown", 111, true, bookingdates, "Breakfast");
		System.out.println(convertPojoToJson(bookingPojo));

	}

	public static String convertPojoToJson(Object data) {

		Gson gson = new Gson();
		String result = gson.toJson(data);
		return result;
	}

}
