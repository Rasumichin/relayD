package com.relayd.web.api.resource;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   06.04.2016
 *
 */
public class PersonResourceTest {
	private PersonResource sut = new PersonResource();

	@Test
	public void testPing() {
		String result = sut.ping();
		assertEquals("a great Ping response from class PersonResource.", result);
	}
}