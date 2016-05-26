package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import com.relayd.client.jaxb.EventDTO;

/**
 * @author Rasumichin (Erik@relayd.com)
 * @since 21.05.2016
 * status initial
 */

public class BasicViewTest {
	
	private BasicView sut = new BasicView();

	@Test
	public void testGetRestClient() {
		Client result = sut.getRestClient();
		assertNotNull("[restClient] is 'null'.", result);
	}
	
	@Test
	public void testSetRestTargetRoot() {
		String restTargetRoot = "http://localhost:8080/";
		sut.setRestTargetRoot(restTargetRoot);
		
		String actualRestTargetRoot = sut.getRestTargetRoot();
		assertEquals("[restTargetRoot] has not been set correctly.", restTargetRoot, actualRestTargetRoot);
	}
	
	@Test
	public void testGetWebTarget() {
		String restTargetRoot = "http://localhost:8080/relayD-api/";
		sut.setRestTargetRoot(restTargetRoot);

		WebTarget webTarget = sut.getWebTarget("resources/events/ping");
		String expectedUri = "http://localhost:8080/relayD-api/resources/events/ping";
		String actualUri = webTarget.getUri().toString();

		assertEquals("WebTarget URI is not correct.", expectedUri, actualUri);
	}

	@Test
	public void testGetEventsAnswersThreeElements() {
		List<EventDTO> result = sut.getEvents();
		
		int mockedNumberOfElements = 3;
		assertEquals("Event list does not contain expected number of elements.", mockedNumberOfElements, result.size());
	}
	
	@Test
	public void testGetEventsPingRequest() {
		String restTargetRoot = "http://localhost:8080/relayD-api/";
		sut.setRestTargetRoot(restTargetRoot);

		String expectedResult = "Pong response from class EventsResource.";
		String actualResult = sut.getEventsPingRequest();

		assertEquals("Result of ping request to EventsResource does not match.", expectedResult, actualResult);
	}
}
