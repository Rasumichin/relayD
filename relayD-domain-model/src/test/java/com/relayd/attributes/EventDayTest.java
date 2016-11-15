package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.EventDay.EventDayNullObject;

/**
 * Niemand, der seine Arbeit tatsächlich versteht würde sich einen Experten nennen.
 *  - Henry Ford
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   24.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDayTest {

	@Test
	public void testIsSerializable() {
		EventDay sut = EventDay.today();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testToday() {
		EventDay sut = EventDay.today();
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

	@Test
	public void testCreateInstance_ForNullValue() {
		EventDay sut = EventDay.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == EventDayNullObject.class;

		assertTrue("Instance is not correct!", actual);

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
	public void testIsEmpty_ForValueFilled() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		EventDay sut = EventDay.newInstance(null);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testToString() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);
		String expectedResult = "2001-11-21";

		String actualResult = sut.toString();

		assertEquals("toString() does not match expected result.", expectedResult, actualResult);
	}

	@Test
	public void testToString_ForNullValue() {
		EventDay sut = EventDay.newInstance(null);

		String actual = sut.toString();

		String expected = "";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		EventDay sut = EventDay.newInstance(getDefinedLocalDateInThePast());

		int hashCode = sut.hashCode();

		assertEquals(4098804, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondSut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondSut = EventDay.newInstance(LocalDate.now());
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondSut = EventDay.newInstance(getDefinedLocalDateInThePast());

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondSut = EventDay.newInstance(LocalDate.now());

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}