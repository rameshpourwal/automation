package com.framework.restapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.framework.utils.CustomLog;
import com.framework.utils.PropertyUtils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author rampourw
 *
 */
public class RestExecutor {

	private RequestSpecification httpRequestSpecification;
	private Response response;
	private RestResponses restResponse;

	/**
	 * @param userName -- api authentication user name
	 * @param password -- api authentication password
	 * @throws IOException
	 */
	public RestExecutor(String userName, String password) throws IOException {

		restResponse = new RestResponses();
		RestAssured.baseURI = PropertyUtils.getConfigProperty("apiurl");
		if (userName != null && password != null) {
			httpRequestSpecification = RestAssured.given().auth().basic(userName, password);
		} else {
			httpRequestSpecification = RestAssured.given();
		}

	}

	public RestExecutor() throws IOException {
		this(null, null);
	}

	/**
	 * @param method -- API method
	 * @param uri    -- API uri
	 * @return
	 */
	private Response apiRequest(Method method, String uri) {
		CustomLog.info("Call " + method + " request " + uri);
		response = httpRequestSpecification.request(method, uri);
		this.setResponse();
		return response;
	}

	/**
	 * @param method -- API method
	 * @param uri    -- API uri
	 * @param data   -- API data to be post
	 * @return
	 */
	private Response apiRequest(Method method, String uri, Map<String, String> data) {
		CustomLog.info("Call " + method + " request " + uri);
		httpRequestSpecification.body(data);
		response = httpRequestSpecification.request(method, uri);
		this.setResponse();
		return response;
	}

	/**
	 * @param uri -- api uri
	 * @return
	 */
	public Response executeGETMethod(String uri) {
		return apiRequest(Method.GET, uri);

	}

	/**
	 * @param uri  -- api uri
	 * @param data -- data for post to api
	 * @return
	 */
	public Response executePOSTMethod(String uri, Map<String, String> data) {
		return apiRequest(Method.POST, uri, data);
	}

	/**
	 * @param uri -- api uri
	 * @return
	 */
	public Response executeDELETEMethod(String uri) {
		return apiRequest(Method.DELETE, uri);

	}

	/**
	 * @param uri -- api uri
	 * @return
	 */
	public Response executeHEADMethod(String uri) {
		return apiRequest(Method.DELETE, uri);

	}

	/**
	 * @param uri  -- api uri
	 * @param data -- Edit data
	 * @return
	 */
	public Response executePUTMethod(String uri, Map<String, String> data) {
		return apiRequest(Method.PUT, uri, data);
	}

	public void setResponse() {

		restResponse.setResponseBody(response.getBody().asString());
		restResponse.setResponseCode(response.getStatusCode());
		restResponse.setResponseMessage(response.getBody().asString());

		CustomLog.debug(response.getBody().asString());

		HashMap<String, String> responseHeaders = new HashMap<>();

		Headers headers = response.getHeaders();
		for (Header header : headers) {
			CustomLog.debug(header.getName() + " and " + header.getValue());
			responseHeaders.put(header.getName(), header.getValue());

		}
		restResponse.setHeaders(responseHeaders);

	}
}
