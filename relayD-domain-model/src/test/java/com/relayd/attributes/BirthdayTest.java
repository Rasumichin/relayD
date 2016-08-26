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
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[dateOfBirth] must not be 'null'.");
		Birthday.newInstance(null);
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
	public void testCreateInstance() {
		final LocalDate date = LocalDate.of(1978, Month.NOVEMBER, 21);

		Birthday birthday = Birthday.newInstance(date);

		assertNotNull("Expected valid instance!", birthday);
	}

	@Test
	public void testToString() {
		final LocalDate date = LocalDate.of(1978, Month.NOVEMBER, 21);

		Birthday birthday = Birthday.newInstance(date);

		assertEquals("1978-11-21", birthday.toString());
	}

	@Test
	public void testGetHashCode() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		int hashCode = sut.hashCode();

		assertEquals(4051700, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));
		sut.value = null;
		Birthday secondName = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));
		sut.value = null;
		Birthday secondName = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));
		Birthday secondName = Birthday.newInstance(LocalDate.of(1979, Month.DECEMBER, 22));

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Birthday sut = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));
		Birthday secondName = Birthday.newInstance(LocalDate.of(1978, Month.NOVEMBER, 21));

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

}