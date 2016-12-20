package com.relayd.web.bridge;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Dienen Sie dem Benutzer, nicht weil Sie es müssen, sondern weil Sie es wollen.
 *  - Philip Toshio Sudo
 *
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

	@Test
	public void testIsEmpty_ForMessageIsNull() {
		String message = null;

		ValidationResultImpl sut = new ValidationResultImpl(message);

		boolean condition = sut.isEmpty();

		assertTrue("[isEmpty] not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForMessageIsEmpty() {
		String message = "";

		ValidationResultImpl sut = new ValidationResultImpl(message);

		boolean condition = sut.isEmpty();

		assertTrue("[isEmpty] not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForMessageIsFilled() {
		String message = "Value is not corret!";

		ValidationResultImpl sut = new ValidationResultImpl(message);

		boolean condition = sut.isEmpty();

		assertFalse("[isEmpty] not correct!", condition);
	}

}