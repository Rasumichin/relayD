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
	public void testGetDefaultPath() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		
		String expectedResult = "";
		String actualResult = sut.getPath();
		assertEquals("Default [path] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetPath() throws URISyntaxException {
		URI resourceUri = getTestUri();
		String expectedResult = "test/1";
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath(expectedResult)
				.build();
		
		String actualResult = sut.getPath();
		assertEquals("[path] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testSetPath() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath("test/1")
				.build();
		
		String expectedResult = "test/2";
		sut.setPath(expectedResult);
		String actualResult = sut.getPath();
		assertEquals("[path] is not as expected.", expectedResult, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPathWithIllegalNullValue() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		sut.setPath(null);
	}
}
