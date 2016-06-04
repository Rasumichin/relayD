package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
public class RestGetServiceIT {
	
	@Test
	public void testGetResultCreatedWithBuilder() throws URISyntaxException {
		URI resourceUri = new URI("http://jsonplaceholder.typicode.com/posts/1");
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withMediaType(MediaType.TEXT_PLAIN)
				.build();
		
		// TODO (Erik, 2016-05-29): Result type should be converted to a generic one (e. g. type "RestServiceResult")
		String result = sut.getResult();
		
		assertNotNull("GetService call is 'null'.", result);
	}
}
