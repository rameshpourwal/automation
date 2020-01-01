package com.framework.restapi;

import java.util.Map;
import java.util.Set;
import org.testng.Assert;

import com.framework.utils.CustomLog;

/**
 * @author rampourw
 *
 */
public class RestValidator {
	private RestResponses response;

	public RestValidator() {
		response = new RestResponses();
	}

	/**
	 * @param expectedResponseCode -- expected api response status
	 * @return
	 */
	public RestValidator expectedResponseCode(int expectedResponseCode) {
		CustomLog.info("Validate the response code " + expectedResponseCode);
		Assert.assertEquals(response.getResponseCode(), expectedResponseCode, "Incorrect response code");
		return this;
	}

	/**
	 * @param expectedBodyContain -- expected api body contents
	 * @return
	 */
	public RestValidator expectedResponseBody(String expectedBodyContain) {
		CustomLog.info("Validate the response body  " + expectedBodyContain);
		Assert.assertTrue(response.getResponseBody().contains(expectedBodyContain), "Iuncorrect response body");
		return this;
	}

	/**
	 * @param expectedMessage -- expected api message contents
	 * @return
	 */
	public RestValidator expectedResponseMessage(String expectedMessage) {
		CustomLog.info("Validate the response message  " + expectedMessage);
		Assert.assertEquals(response.getResponseMessage(), expectedMessage, "Incorrect response message");
		return this;
	}

	/**
	 * @param headerName  -- api header name
	 * @param headerValue -- expected header value based on header name
	 * @return
	 */
	public RestValidator expectedHeaderName(String headerName, String headerValue) {
		CustomLog.info("Validate the header message  " + headerValue);
		Assert.assertEquals(response.getHeader(headerName), headerValue, "Incorrect header " + headerName);
		return this;
	}

	/**
	 * @param expectedHeaders -- expected headers
	 * @return
	 */
	public RestValidator expectedHeaders(Map<String, String> expectedHeaders) {
		CustomLog.info("Validate the headers message  " + expectedHeaders);
		Set<String> keys = expectedHeaders.keySet();
		for (String key : keys) {
			Assert.assertEquals(expectedHeaders.get(key), response.getHeader(key), "Incorrect header " + key);
		}
		return this;
	}

	public RestValidator getResponse() {
		CustomLog.info("Get the reponse code ");
		response.getResponseCode();
		return this;
	}

}
