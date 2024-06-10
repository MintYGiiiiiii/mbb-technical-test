package com.main.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestfulRequestSender {

	public String send(final String json, final String url, HttpMethod httpMethod) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		
		HttpEntity<String> request = new HttpEntity<String>(json, header);
		
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, httpMethod, request, String.class);
		} catch (RestClientException e) {
			
		}
		String responseString = response != null ? response.getBody() : null;
		
		return responseString;
	}
}
