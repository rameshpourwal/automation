package com.framework.smokeapi;

import java.util.HashMap;
import org.testng.annotations.Test;

import com.framework.testbase.ApiHooks;

public class Registartion extends ApiHooks {

	private String uri = "/api/users";
	private HashMap<String, String> data;

	public Registartion() throws Exception {
		super();
		data = new HashMap<>();
	}

	private HashMap<String, String> setData() {
		data.put("name", "morpheus");
		data.put("job", "leader");
		return data;
	}

	@Test
	public void registartion() {
		restExecutor.executePOSTMethod(uri, setData());
		restValidator.expectedResponseCode(201).expectedResponseBody("id");
	}

}
