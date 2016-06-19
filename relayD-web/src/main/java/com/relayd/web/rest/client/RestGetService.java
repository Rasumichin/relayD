package com.relayd.web.rest.client;

import java.net.URI;

import javax.ws.rs.core.GenericType;

/**
 * Intention is to provide a simple interface for performing JAX-RS GET service calls.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
public interface RestGetService {

	<T> T getResult(Class<T> aClass);
	
	// TODO (Erik, 2016-06-18): Maybe we manage to implement this signature to avoid REST-Client dependency on the caller's side.
	// <T> List<T> getListResult(Class<T> aClass);
	
	<T> T getListResult(GenericType<T> genericType);
	URI getResourceUri();
	String getMediaType();
	String getPath();
	void setPath(String path);
	void setMediaType(String mediaType);
}
