package com.framework.smokeapi;

import org.testng.annotations.Test;

import com.framework.testbase.ApiHooks;

public class DeleteData extends ApiHooks {

	public DeleteData() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private String uri = "/api/users/2";

	@Test
	public void putData() {
		restExecutor.executeDELETEMethod(uri);
		restValidator.expectedResponseCode(200).expectedHeaderName("Connection", "keep-alive");
	}

}
