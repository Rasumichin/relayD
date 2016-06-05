package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import com.relayd.client.jaxb.EventDTO;
import com.relayd.web.rest.client.RestGetService;

/**
 * @author Rasumichin (Erik@relayd.com)
 * @since 21.05.2016
 * status initial
 * 
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
	public void testCreateRestGetService() throws URISyntaxException {
		URI uri = new URI("http://example.com/pathToResource");
		RestGetService result = sut.createRestGetService(uri);
		
		assertNotNull("RestGetService has not been created.", result);
	}
	
	@Test
	public void testGetEventsPingRequest() throws URISyntaxException {
		BasicView sutWithTestDoubleForRestGetService = new BasicView() {
			private static final long serialVersionUID = 1L;
			
			@Override
			RestGetService createRestGetService(URI resourceUri) {
				return new RestGetService() {
					
					@SuppressWarnings("unchecked")
					@Override
					public <T> T getResult(Class<T> aClass) {
						return (T) "Pong response from class EventsResource.";
					}
					
					@Override
					public URI getResourceUri() {
						return null;
					}

					@Override
					public String getMediaType() {
						return null;
					}

					@Override
					public Class<?> getResultType() {
						return null;
					}
				};
			}
		};
		String uriAuthority = "localhost:8080";
		String expectedResult = "Pong response from class EventsResource.";

		String actualResult = sutWithTestDoubleForRestGetService.getEventsPingRequest(uriAuthority);

		assertEquals("RestGetService (EventsPing) has not been called correctly.", expectedResult, actualResult);
	}
}
