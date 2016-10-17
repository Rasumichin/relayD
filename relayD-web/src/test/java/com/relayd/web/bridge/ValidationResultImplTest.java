package com.relayd.web.bridge;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.07.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidationResultImplTest {

	@Test
	public void testConstructor() {
		String expected = "Value is not correct!";

		ValidationResultImpl sut = new ValidationResultImpl(expected);

		String result = sut.getMessage();

		assertEquals("[message] not correct!", expected, result);
	}
}