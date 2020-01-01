package com.framework.smokeapi;

import org.testng.annotations.Test;

import com.framework.testbase.ApiHooks;

public class GetWeatherData extends ApiHooks {

	private String uri = "/api/unknown/2";

	public GetWeatherData() throws Exception {
		super();
	}

	@Test
	public void getWeatherDataTest() {

		restExecutor.executeGETMethod(uri);
		restValidator.expectedResponseCode(200).expectedResponseBody("name").expectedHeaderName("Connection",
				"keep-alive");

	}

}
