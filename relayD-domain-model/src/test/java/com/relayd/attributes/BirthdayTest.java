package com.relayd.attributes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 22.03.2016 status initial
 */
public class BirthdayTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testValidateWithNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[dateOfBirth] must not be 'null'.");

		Birthday.validate(null);
	}

	@Test
	public void testValidateWithFutureDate() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[dateOfBirth] must be in the past.");

		LocalDate dateOfBirthdayOneWeekInTheFuture = LocalDate.now().plusDays(7);

		Birthday.validate(dateOfBirthdayOneWeekInTheFuture);
	}

	@Test
	public void testValidateWithPastDate() {
		LocalDate dateOfBirthdayOneWeekInTheFuture = LocalDate.now().minusYears(18);

		Birthday.validate(dateOfBirthdayOneWeekInTheFuture);
	}

	@Test
	public void testCreateValidObject() {
		final LocalDate date = LocalDate.of(1978, Month.NOVEMBER, 21);

		Birthday birthday = Birthday.newInstance(date);

		assertEquals("21-11-1978", birthday.toString());
	}
}