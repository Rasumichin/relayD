package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.relayd.client.jaxb.EventDTO;

/**
 * @author Rasumichin (Erik@relayd.com)
 * @since 21.05.2016
 * status initial
 */

public class BasicViewTest {

	@Test
	public void testGetEventsAnswersThreeElements() {
		BasicView sut = new BasicView();
		List<EventDTO> result = sut.getEvents();
		
		int mockedNumberOfElements = 3;
		assertEquals("Event list does not contain expected number of elements.", mockedNumberOfElements, result.size());
	}
}
