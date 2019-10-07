package com.bridgelabz.response;

public class ResponseHelper {
	public static Response statusResponse(int statusCode, String message, String token) {
		Response statusResponse = new Response();
		statusResponse.setMessage(message);
		statusResponse.setStatusCode(statusCode);
		statusResponse.setData(token);
		return statusResponse;
	
	}
}
