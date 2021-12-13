package com.edbootcamp.restManagers;



import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.edbootcamp.view.UserImpl;

@Service
public class RESTUserManagerImpl {

	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/user";
	@Autowired
	private RestTemplate restTemplate;
	
	public UserImpl saveUser(UserImpl user) {
		String requestUri = REQUEST_URI + "/createUser";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UserImpl> entity = new HttpEntity<>(user, headers);
		System.out.println(entity.getBody());
		try {
			return restTemplate.postForObject(requestUri, entity, UserImpl.class);
		} catch (HttpStatusCodeException ex) {
	        // raw http status code e.g `404`
	        System.out.println(ex.getRawStatusCode());
	        // http status code e.g. `404 NOT_FOUND`
	        System.out.println(ex.getStatusCode().toString());
	        // get response body
	        System.out.println(ex.getResponseBodyAsString());
	        // get http headers
	        headers= ex.getResponseHeaders();
	        System.out.println(headers.get("Content-Type"));
	        System.out.println(headers.get("Server"));
	    }
		return null;
	}

	public UserImpl getUser(UserImpl user) {
		String requestUri = REQUEST_URI + "/login";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UserImpl> entity = new HttpEntity<>(user, headers);
		try {
			return restTemplate.getForObject(requestUri,UserImpl.class ,user);
		} catch (HttpStatusCodeException ex) {
	        // raw http status code e.g `404`
	        System.out.println(ex.getRawStatusCode());
	        // http status code e.g. `404 NOT_FOUND`
	        System.out.println(ex.getStatusCode().toString());
	        // get response body
	        System.out.println(ex.getResponseBodyAsString());
	        // get http headers
	        headers= ex.getResponseHeaders();
	        System.out.println(headers.get("Content-Type"));
	        System.out.println(headers.get("Server"));
	    }
		return null;
		
	}

	public UserImpl loginStudent(UserImpl user) {
		String requestUri = REQUEST_URI + "/login";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UserImpl> entity = new HttpEntity<>(user, headers);
		try {
			return restTemplate.postForObject(requestUri, entity, UserImpl.class);
		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());
			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());
			// get response body
			System.out.println(ex.getResponseBodyAsString());
			// get http headers
			headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}
	
	public UserImpl logoutStudent(UserImpl user) {
		String requestUri = REQUEST_URI + "/login";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UserImpl> entity = new HttpEntity<>(user, headers);
		try {
			return restTemplate.getForObject(requestUri,UserImpl.class, entity);
		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());
			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());
			// get response body
			System.out.println(ex.getResponseBodyAsString());
			// get http headers
			headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}

	public UserImpl accountSettings(UserImpl user) {
		String requestUri = REQUEST_URI + "/account";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UserImpl> entity = new HttpEntity<>(user, headers);
		try {
			return restTemplate.getForObject(requestUri,UserImpl.class, entity);
		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());
			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());
			// get response body
			System.out.println(ex.getResponseBodyAsString());
			// get http headers
			headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}

	public UserImpl updateUser(UserImpl user) {
		String requestUri = REQUEST_URI + "/recipes/updateUser/";
		Long id = user.getId();
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
        restTemplate.put(requestUri + "/{id}", user, params);
		return user;
	}

	public void deleteUser(Long id) {
		String requestUri = REQUEST_URI + "/recipes/deleteUser/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
        restTemplate.delete(requestUri + "/{id}", params);
	}
	
}
