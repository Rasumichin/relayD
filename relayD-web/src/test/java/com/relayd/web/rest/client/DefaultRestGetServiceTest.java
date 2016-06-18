package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
public class DefaultRestGetServiceTest {
	private RestGetService sut;
	private URI resourceUri;
	
	@Before
	public void setUp() throws URISyntaxException {
		resourceUri = getTestUri();
		sut = new DefaultRestGetService.Buillder(resourceUri).build();
	}

	private URI getTestUri() throws URISyntaxException {
		return new URI("http://www.example.com/resources/tests");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateInstanceWithIllegalNullUri() {
		new DefaultRestGetService.Buillder(null).build();
	}

	@Test
	public void testGetResourceUri() {
		URI result = sut.getResourceUri();
		
		assertNotNull("[resourceUri] is 'null'.", result);
		assertEquals("Given [resourceUri] does not match the answered URI.", resourceUri, result);
	}
	
	@Test
	public void testGetDefaultMediaType() {
		String expectedResult = MediaType.TEXT_PLAIN;
		String actualResult = sut.getMediaType();
		
		assertEquals("Default [mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetMediaTypeSetWithBuilder() throws URISyntaxException {
		String expectedResult = MediaType.APPLICATION_JSON;
		URI resourceUri = getTestUri();

		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withMediaType(expectedResult)
				.build();
		
		String actualResult = sut.getMediaType();
		assertEquals("[mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetDefaultPath() {
		String expectedResult = "";
		String actualResult = sut.getPath();
		
		assertEquals("Default [path] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testGetPathSetWithBuilder() throws URISyntaxException {
		URI resourceUri = getTestUri();
		String expectedResult = "test/1";
		RestGetService sut = new DefaultRestGetService.Buillder(resourceUri)
				.withPath(expectedResult)
				.build();
		
		String actualResult = sut.getPath();
		assertEquals("[path] is not as expected.", expectedResult, actualResult);
	}

	@Test
	public void testSetPath() {
		String expectedResult = "test/2";
		sut.setPath(expectedResult);

		String actualResult = sut.getPath();
		assertEquals("[path] is not as expected.", expectedResult, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPathWithIllegalNullValue() {
		sut.setPath(null);
	}
	
	@Test
	public void testSetMediaType() {
		String expectedResult = MediaType.APPLICATION_JSON;
		sut.setMediaType(expectedResult);
		
		String actualResult = sut.getMediaType();
		assertEquals("[mediaType] is not as expected.", expectedResult, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetMediaTypeWithIllegalNullValue() throws URISyntaxException {
		sut.setMediaType(null);
	}
	
	@Test
	public void testRestClientHasBeenCreatedAfterBuildInstance() {
		Client restClient = ((DefaultRestGetService)sut).getRestClient();
		
		assertNotNull("[restClient] has not been initialized correctly.", restClient);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFinalizeLeadsToRestClientNoLongerUsable() throws Throwable {
		Client restClient = ((DefaultRestGetService)sut).getRestClient();
		
		// Invoke an arbitrary method which is perfectly legal.
		restClient.target("");
		
		// Finalize our sut which closes the 'restClient'.
		((DefaultRestGetService)sut).finalize();
		
		// Invoke again a method which is now no longer legal.
		restClient.target("");
	}
}
