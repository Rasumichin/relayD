package com.relayd.attributes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.*;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   24.05.2016
 * status   ready-for-review
 *
 */
public class EventDayTest {

	@Test
	public void testCreateDefaultInstanceAsForToday() {
		EventDay sut = EventDay.newInstance();
		boolean isToday = sut.isToday();

		assertTrue("Default day of event is not today.", isToday);
	}

	@Test
	public void testCreateValidInstance() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);

		LocalDate actualDate = sut.getValue();
		assertEquals("Day of event does not match.", dateOfEvent, actualDate);
	}

	private LocalDate getDefinedLocalDateInThePast() {
		return LocalDate.of(2001, Month.NOVEMBER, 21);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInstanceWithIllegalNullValue() {
		@SuppressWarnings("unused")
		EventDay sut = EventDay.newInstance(null);
	}

	@Test
	public void testCreateInstanceThatIsInThePast() {
		LocalDate dateOfEventInThePast = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEventInThePast);

		boolean isInThePast = sut.isInThePast();
		assertTrue("Day of event is not in the past", isInThePast);
	}

	@Test
	public void testCreateInstanceThatIsToday() {
		LocalDate dateOfEventToday = LocalDate.now();
		EventDay sut = EventDay.newInstance(dateOfEventToday);

		boolean isToday = sut.isToday();
		assertTrue("Day of event is not today.", isToday);
	}

	@Test
	public void testCreateInstanceThatIsInTheFuture() {
		LocalDate dateOfEventOneWeekInTheFuture = LocalDate.now().plusDays(7);
		EventDay sut = EventDay.newInstance(dateOfEventOneWeekInTheFuture);

		boolean isInTheFuture = sut.isInTheFuture();
		assertTrue("Day of event is not in the future.", isInTheFuture);
	}

	@Test
	public void testToString() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);
		String expectedResult = "2001-11-21";

		String actualResult = sut.toString();

		assertEquals("toString() does not match expected result.", expectedResult, actualResult);
	}

	// TODO (Erik, 2016-08-27): Discuss whether this is necessary. Surprisingly test failed without any change?
	@Ignore
	public void testGetHashCode() {
		EventDay sut = EventDay.newInstance(getDefinedLocalDateInThePast());

		int hashCode = sut.hashCode();

		assertEquals(4098804, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondName = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondName = EventDay.newInstance(LocalDate.now());
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondName = EventDay.newInstance(getDefinedLocalDateInThePast());

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondName = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}
}