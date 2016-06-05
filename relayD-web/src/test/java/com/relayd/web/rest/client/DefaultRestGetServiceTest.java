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
public class DefaultRestGetServiceTest {

	@Test(expected=IllegalArgumentException.class)
	public void testCreateInstanceWithIllegalNullUri() {
		@SuppressWarnings("unused")
		RestGetService sut = new DefaultRestGetService.Buillder(null).build();
	}

	@Test
	public void testGetResourceUri() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		
		URI result = sut.getResourceUri();
		assertNotNull("[resourceUri] is 'null'.", result);
		assertEquals("Given [resourceUri] does not match the answered URI.", resourceUri, result);
	}
	
	private URI getTestUri() throws URISyntaxException {
		return new URI("http://www.example.com/resources/tests");
	}

	@Test
	public void testGetDefaultMediaType() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		
		String expectedResult = MediaType.TEXT_PLAIN;
		String actualResult = sut.getMediaType();
		assertEquals("Default [mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetMediaType() throws URISyntaxException {
		URI resourceUri = getTestUri();
		String expectedResult = MediaType.APPLICATION_JSON;
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withMediaType(expectedResult)
				.build();
		
		String actualResult = sut.getMediaType();
		assertEquals("[mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetDefaultResultType() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		
		Class<?> expectedResult = String.class;
		Class<?> actualResult = sut.getResultType();
		assertEquals("Default [resultType] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetResultType() throws URISyntaxException {
		URI resourceUri = getTestUri();
		Class<?> expectedResult = JsonPlaceholderPost.class;
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withResultType(JsonPlaceholderPost.class)
				.build();
		
		Class<?> actualResult = sut.getResultType();
		assertEquals("[resultType] is not as expected.", expectedResult, actualResult);
	}
}
