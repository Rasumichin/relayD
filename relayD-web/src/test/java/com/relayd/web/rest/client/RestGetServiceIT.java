package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
public class RestGetServiceIT {
	
	@Test
	public void testGetResultIsNotNull() throws Exception {
		URI resourceUri = new URI("http://jsonplaceholder.typicode.com/users");
		RestGetService sut = new DefaultRestGetService(resourceUri);
		
		// TODO (Erik, 2016-05-29): Result type should be converted to a generic one (e. g. type "RestServiceResult")
		String result = sut.getResult();
		
		assertNotNull("GetService call is 'null'.", result);
	}
}
