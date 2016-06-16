package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.relayd.client.jaxb.EventDTO;
import com.relayd.web.rest.client.RestGetService;

/**
 * @author Rasumichin (Erik@relayd.com)
 * @since 21.05.2016
 * status initial
 * 
 */
public class RelayEventBrowsePageBeanTest {
	
	private RelayEventBrowsePageBean sut = new RelayEventBrowsePageBean();

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
	public void testGetEventsPingRequestWithTestDouble() throws URISyntaxException {
		RelayEventBrowsePageBean sutWithTestDoubleForRestGetService = new RelayEventBrowsePageBean() {
			private static final long serialVersionUID = 1L;
			
			@Override
			RestGetService createRestGetService(URI resourceUri) {
				RestGetService testDoubleService = Mockito.mock(RestGetService.class);
				when(testDoubleService.getResult(String.class)).thenReturn("Pong response");
				
				return testDoubleService;
			}
		};
		
		String uriAuthority = "localhost:8080";
		String expectedResult = "Pong response";

		String actualResult = sutWithTestDoubleForRestGetService.getEventsPingRequest(uriAuthority);

		assertEquals("RestGetService (EventsPing) has not been called correctly.", expectedResult, actualResult);
	}
}
