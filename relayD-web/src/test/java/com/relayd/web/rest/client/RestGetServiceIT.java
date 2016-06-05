package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
	public void testCallServiceAndReceiveJsonPayload() throws URISyntaxException {
		URI resourceUri = new URI("http://jsonplaceholder.typicode.com");
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("posts/1")
				.withMediaType(MediaType.TEXT_PLAIN)
				.build();
		
		String result = sut.getResult(String.class);
		assertNotNull("GetService call does not deliver JSON payload.", result);
	}
	
	@Test
	public void testCallRestServiceNativelyAndDeliverTypeConvertedFromJson() throws URISyntaxException {
		URI resourceUri = new URI("http://jsonplaceholder.typicode.com");
		Client client = ClientBuilder.newClient();
		JsonPlaceholderPost result = client.target(resourceUri)
			.path("posts/1")
			.request(MediaType.APPLICATION_JSON)
			.get(JsonPlaceholderPost.class);
		assertNotNull("Conversion of JSON payload to a custom type was not correct.", result);
		assertEquals("Custom type has not the correct [id].", 1, result.getId());
	}

	@Test
	public void testCallServiceAndReceiveCustomType() throws URISyntaxException {
		URI resourceUri = new URI("http://jsonplaceholder.typicode.com");
		int expectedId = 1;
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("posts/" + expectedId)
				.withMediaType(MediaType.APPLICATION_JSON)
				.build();
		
		JsonPlaceholderPost result = sut.getResult(JsonPlaceholderPost.class);
		assertNotNull("Conversion of JSON payload to a custom type was not correct.", result);
		assertEquals("Custom type has not the correct [id].", expectedId, result.getId());
	}
}
