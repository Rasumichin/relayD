package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.List;

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
	public void testGetEventsAnswersThreeElements() {
		List<EventDTO> result = sut.getEvents();
		
		int mockedNumberOfElements = 3;
		assertEquals("Event list does not contain expected number of elements.", mockedNumberOfElements, result.size());
	}
	
	@Test
	public void testGetEventsPingRequest() throws URISyntaxException {
		String uriAuthority = "localhost:8080";

		String expectedResult = "Pong response from class EventsResource.";
		String actualResult = sut.getEventsPingRequest(uriAuthority);

		assertEquals("Result of ping request to EventsResource does not match.", expectedResult, actualResult);
	}
}
