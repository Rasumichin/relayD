package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
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
	
	@Test
	public void testSetMediaType() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withMediaType(MediaType.APPLICATION_XML)
				.build();
		
		String expectedResult = MediaType.APPLICATION_JSON;
		sut.setMediaType(expectedResult);
		String actualResult = sut.getMediaType();
		assertEquals("[mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetMediaTypeWithIllegalNullValue() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri).build();
		sut.setMediaType(null);
	}
	
	@Test
	public void testRestClientHasBeenCreatedAfterBuildInstance() throws URISyntaxException {
		URI resourceUri = getTestUri();
		RestGetService restGetService = new DefaultRestGetService.Buillder(resourceUri).build();
		DefaultRestGetService sut = (DefaultRestGetService)restGetService;
		
		Client restClient = sut.getRestClient();
		assertNotNull("[restClient] has not been initialized correctly.", restClient);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFinalizeLeadsToRestClientNoLongerUsable() throws Throwable {
		URI resourceUri = getTestUri();
		RestGetService restGetService = new DefaultRestGetService.Buillder(resourceUri).build();
		DefaultRestGetService sut = (DefaultRestGetService)restGetService;
		
		Client restClient = sut.getRestClient();
		
		// Invoke an arbitrary method which is perfectly legal.
		restClient.target("");
		
		// Finalize our sut which closes the 'restClient'.
		sut.finalize();
		
		// Invoke again a method which is now no longer legal.
		restClient.target("");
	}
}
