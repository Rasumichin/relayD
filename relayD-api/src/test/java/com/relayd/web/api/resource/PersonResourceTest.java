package com.relayd.web.api.resource;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   06.04.2016
 * status   initial
 */
public class PersonResourceTest {
	private PersonResource sut = new PersonResource();

	@Test
	public void testPing() {
		String result = sut.ping();
		assertEquals("a great Ping response from class PersonResource.", result);
	}
}