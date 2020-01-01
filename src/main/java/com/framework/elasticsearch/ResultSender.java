package com.framework.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.utils.CustomLog;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * @author rampourw
 *
 */
public class ResultSender {

	private static final ObjectMapper OM = new ObjectMapper();
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_TYPE_VALUE = "application/json";
	private static final String ELASTICSEARCHURL = "http://localhost:9200/app/search";

	/**
	 * send the test result to elastic search
	 * 
	 * @param testStatus -- test status result
	 */

	private ResultSender() {

	}

	public static void send(final TestStatus testStatus) {
		try {

			HttpResponse<JsonNode> response = Unirest.post(ELASTICSEARCHURL).header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
					.body(OM.writeValueAsString(testStatus)).asJson();
			CustomLog.debug("response of elastic search " + response.getStatus());
			CustomLog.debug("response body of the elastic search " + response.getBody().toString());
		} catch (Exception e) {
			CustomLog.error(e.getMessage());
		}
	}

}
