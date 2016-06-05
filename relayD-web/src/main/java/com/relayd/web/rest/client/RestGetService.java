package com.relayd.web.rest.client;

import java.net.URI;

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
	URI getResourceUri();
	String getMediaType();
	String getPath();
	void setPath(String path);
}
