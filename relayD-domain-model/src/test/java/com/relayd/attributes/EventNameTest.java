package com.relayd.attributes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class EventNameTest {

	@Test
	public void testToString() {
		String eventName = "MetroGroup Marathon Düsseldorf";

		EventName sut = new EventName(eventName);

		String result = sut.toString();

		assertEquals(eventName, result);
	}
}