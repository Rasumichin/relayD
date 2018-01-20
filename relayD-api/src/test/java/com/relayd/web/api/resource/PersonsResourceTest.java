package com.relayd.web.api.resource;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   06.04.2016
 *
 */
public class PersonsResourceTest {
	private PersonsResource sut = new PersonsResource();

	@Test
	public void testPing() {
		String result = sut.ping();
		assertEquals("a great Ping response from class PersonsResource.", result);
	}
}