package com.framework.restapi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rampourw
 *
 */
public class RestResponses {

	private static int responseCode;
	private static String responseBody;
	private static String responseMessage;
	private static Map<String, String> headers = new HashMap<>();

	public synchronized int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode -- api response status
	 */
	public synchronized void setResponseCode(int responseCode) {
		RestResponses.responseCode = responseCode;

	}

	public synchronized String getResponseBody() {
		return responseBody;
	}

	/**
	 * @param responseBody -- api response body contents
	 */
	public synchronized void setResponseBody(String responseBody) {
		RestResponses.responseBody = responseBody;
	}

	public synchronized String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage -- api response message content
	 */
	public synchronized void setResponseMessage(String responseMessage) {
		RestResponses.responseMessage = responseMessage;
	}

	public synchronized Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers -- api header response
	 */
	public synchronized void setHeaders(Map<String, String> headers) {
		RestResponses.headers = headers;
	}

	/**
	 * @param name -- header name
	 * @return
	 */
	public String getHeader(String name) {
		return headers.get(name);
	}

}
