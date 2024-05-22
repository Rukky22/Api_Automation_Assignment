package com.api.utility;

import com.google.gson.Gson;

public class TestUtility {

	public static String getJsonObject(Object data) {

		Gson gson = new Gson();
		String result = gson.toJson(data);
		return result;
	}

}
