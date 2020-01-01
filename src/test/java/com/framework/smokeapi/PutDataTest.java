package com.framework.smokeapi;

import java.util.HashMap;
import org.testng.annotations.Test;

import com.framework.testbase.ApiHooks;

public class PutDataTest extends ApiHooks {

	private String uri = "/api/users/2";
	private HashMap<String, String> data;

	public PutDataTest() throws Exception {
		super();
		data = new HashMap<>();
	}

	private HashMap<String, String> setData() {
		data.put("name", "morpheus");
		data.put("job", "zion resident");
		return data;
	}

	@Test
	public void putData() {
		restExecutor.executePUTMethod(uri, setData());
		restValidator.expectedResponseCode(200).expectedHeaderName("Connection", "keep-alive");

	}

}
