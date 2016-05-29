package com.relayd.web.rest.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

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
		DefaultRestGetService sut = new DefaultRestGetService(null);
	}

	@Test
	public void testGetResourceUri() throws URISyntaxException {
		URI resourceUri = new URI("http://www.example.com/resources/tests");
		DefaultRestGetService sut = new DefaultRestGetService(resourceUri);
		
		URI result = sut.getResourceUri();
		assertNotNull("[resourceUri] is 'null'.", result);
		assertEquals("Given [resourceUri] does not match the answered URI.", resourceUri, result);
	}
}
