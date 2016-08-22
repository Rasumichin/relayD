package com.relayd.web.bridge;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 */
public class ValidationResultImplTest {

	@Test
	public void testConstructor() {
		String expected = "Value is not correct!";

		ValidationResultImpl sut = new ValidationResultImpl(expected);

		String result = sut.getMessage();

		assertEquals("[message] not correct!", expected, result);
	}
}