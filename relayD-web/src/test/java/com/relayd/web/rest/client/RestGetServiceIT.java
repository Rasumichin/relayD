package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 *
 */
@Ignore // TODO it1f36, 08.09.2016: to avoid issues with proxy
public class RestGetServiceIT {
	private static final String JSON_TEST_URI = "http://jsonplaceholder.typicode.com";

	@Test
	public void testCallServiceAndReceiveJsonPayload() throws URISyntaxException {
		URI resourceUri = new URI(JSON_TEST_URI);
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("posts/1")
				.withMediaType(MediaType.TEXT_PLAIN)
				.build();

		String result = sut.getResult(String.class);
		assertNotNull("GetService call does not deliver JSON payload.", result);
	}

	@Test
	public void testCallRestServiceNativelyAndDeliverTypeConvertedFromJson() throws URISyntaxException {
		URI resourceUri = new URI(JSON_TEST_URI);
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
		URI resourceUri = new URI(JSON_TEST_URI);
		int expectedId = 1;
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("posts/" + expectedId)
				.withMediaType(MediaType.APPLICATION_JSON)
				.build();

		JsonPlaceholderPost result = sut.getResult(JsonPlaceholderPost.class);
		assertNotNull("Conversion of JSON payload to a custom type was not correct.", result);
		assertEquals("Custom type has not the correct [id].", expectedId, result.getId());
	}

	@Test
	public void testCallRestServiceNativelyAndDeliverListOfTypeConvertedFromJson() throws URISyntaxException {
		URI resourceUri = new URI(JSON_TEST_URI);

		// According to 'http://jsonplaceholder.typicode.com/posts' this should always return 100 elements.
		int expectedSize = 100;
		Client client = ClientBuilder.newClient();

		List<JsonPlaceholderPost> result = client.target(resourceUri)
				.path("posts")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<JsonPlaceholderPost>>() {
				});

		assertNotNull("Conversion of JSON payload to a list of custom types was not correct.", result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void testCallServiceAndReceiveCorrectlyTypedListOfCustomType() throws URISyntaxException {
		URI resourceUri = new URI(JSON_TEST_URI);
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("posts")
				.withMediaType(MediaType.APPLICATION_JSON)
				.build();

		List<JsonPlaceholderPost> result = sut.getListResult(new GenericType<List<JsonPlaceholderPost>>() {
		});
		assertNotNull("Conversion of JSON payload to a list of custom types was not correct.", result);
		assertFalse("Result does not contain any element.", result.isEmpty());

		Object firstElementOfResult = result.get(0);
		assertTrue("Elements do not have the correct type.", firstElementOfResult instanceof JsonPlaceholderPost);
	}
}
