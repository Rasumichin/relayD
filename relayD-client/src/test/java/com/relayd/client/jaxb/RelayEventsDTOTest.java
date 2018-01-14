package com.relayd.client.jaxb;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class RelayEventsDTOTest {
	RelayEventsDTO sut = new RelayEventsDTO();

	@Test
	public void testGetRelayEvents() {
		List<RelayEventDTO> relayEvents = sut.getRelayEvents();
		
		boolean result = relayEvents.isEmpty();
		assertTrue("Initial content of 'relayEvents' is not correct!", result);
	}
	
	@Test
	public void testSetRelayEvents() {
		List<RelayEventDTO> expected = new ArrayList<>();
		expected.add(RelayEventDTO.newInstance());
		
		sut.setRelayEvents(expected);
		
		List<RelayEventDTO> actual = sut.getRelayEvents();
		assertEquals("Content of 'relayEvents' is not correct!", expected, actual);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetRelayEvents_with_null() {
		sut.setRelayEvents(null);
	}
	
	@Test
	public void testAddAllRelayEvents() {
		List<RelayEventDTO> expected = new ArrayList<>();
		expected.add(RelayEventDTO.newInstance());
		expected.add(RelayEventDTO.newInstance());
		expected.add(RelayEventDTO.newInstance());
		
		sut.addAllRelayEvents(expected);
		
		List<RelayEventDTO> actual = sut.getRelayEvents();
		assertEquals("Content of 'relayEvents' is not correct!", expected, actual);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testSeAddAlltRelayEvents_with_null() {
		sut.addAllRelayEvents(null);
	}
	
	@Test
	public void testToString() {
		String expected = "RelayEventsDTO [relayEventDTO elements=0]";
		
		String actual = sut.toString();
		
		assertEquals("String representation is not correct!", expected, actual);
	}
}
