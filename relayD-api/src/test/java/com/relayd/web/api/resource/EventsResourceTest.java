package com.relayd.web.api.resource;

import static org.junit.Assert.*;

import java.net.*;
import java.util.*;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import org.junit.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.relayd.client.jaxb.RelayEventDTO;
import com.relayd.web.api.bridge.RelayEventDTOBridge;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.06.2017
 *
 */
public class EventsResourceTest {
	private RelayEventDTOBridge eventDTOBridgeMock = mock(RelayEventDTOBridge.class);
	private EventsResource sut = EventsResource.newInstance(eventDTOBridgeMock);
	
	@Test
	public void testNewInstance() {
		assertNotNull("Intance creation is not correct!", sut);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance_with_null() {
		EventsResource.newInstance(null);
	}
	
	@Test
	public void testGetRelayEventDTOBridge() {
		RelayEventDTOBridge result = sut.getRelayEventDTOBridge();
		
		assertNotNull("[relayEventDTOBridge] must not be 'null'!", result);
	}

	@Test
	public void testGetEvents() {
		when(eventDTOBridgeMock.all()).thenReturn(new ArrayList<RelayEventDTO>());
		
		List<RelayEventDTO> actual = sut.getEvents();
		
		assertTrue("Response from 'getEvents' is not correct!", actual.isEmpty());
	}

	@Test
	public void testAddEvent() throws URISyntaxException {
		RelayEventDTO eventDTO = RelayEventDTO.newInstance();
		UriInfo uriInfoMock = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfoMock.getAbsolutePath()).thenReturn(new URI("http://mock.com"));
		Status expected = Status.CREATED;
		
		Response response = sut.addEvent(eventDTO, uriInfoMock);
		
		Status actual = Status.fromStatusCode(response.getStatus());
		assertEquals("Response from 'addEvent' is not correct!", expected, actual);
	}

	@Test
	public void testUpdateEvent() {
		String id = UUID.randomUUID().toString();
		RelayEventDTO eventDTO = RelayEventDTO.newInstance();
		Status expected = Status.OK;

		Response response = sut.updateEvent(id, eventDTO);
		
		Status actual = Status.fromStatusCode(response.getStatus());
		assertEquals("Response from 'updateEvent' is not correct!", expected, actual);
	}

	@Test
	public void testPing() {
		String expected = "Pong response from class EventsResource.";
		
		String actual = sut.ping();
		
		assertEquals("Response from 'ping' is not correct!", expected, actual);
	}

	@Test
	public void testGetEvent() {
		String expected = UUID.randomUUID().toString();
		
		RelayEventDTO result = sut.getEvent(expected);
		
		String actual = result.getId();
		assertEquals("Response from 'getEvent' is not correct!", expected, actual);
	}

	@Test
	public void testDeleteEvent() {
		String id = UUID.randomUUID().toString();
		List<Status> expected = new ArrayList<>();
		expected.add(Status.NO_CONTENT);
		expected.add(Status.NOT_FOUND);
		
		Response response = sut.deleteEvent(id);
		
		Status actual = Status.fromStatusCode(response.getStatus());
		assertTrue("Response from 'deleteEvent' is not correct!", expected.contains(actual));
	}
}
