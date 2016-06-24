package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.Test;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class RelayEventTest {

	@Test
	public void testGetHashCode() {
		RelayEvent sut = new RelayEvent(null, null);
		UUID uuid = UUID.fromString("53a27b33-a5cb-4997-8eaf-dcf8bd1cb2d2");
		sut.setUUID(uuid);

		int hashCode = sut.hashCode();

		assertEquals(-975545171, hashCode);

		sut.setUUID(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		RelayEvent sut = new RelayEvent(null, null);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		RelayEvent sut = new RelayEvent(null, null);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		RelayEvent sut = new RelayEvent(null, null);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithUUIDIsNull() {
		RelayEvent sut = new RelayEvent(null, null);

		RelayEvent secondEvent = new RelayEvent(null, null);
		sut.setUUID(null);

		boolean result = sut.equals(secondEvent);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUUIDAreNull() {
		RelayEvent sut = new RelayEvent(null, null);
		sut.setUUID(null);

		RelayEvent secondEvent = new RelayEvent(null, null);
		secondEvent.setUUID(null);

		boolean result = sut.equals(secondEvent);

		assertTrue(result);
	}

	@Test
	public void testName() {
		RelayEvent sut = new RelayEvent(null, null);

		EventName expected = new EventName("Name");

		sut.setName(expected);

		EventName result = sut.getName();

		assertEquals(expected, result);
	}

	@Test
	public void testEventDay() {
		RelayEvent sut = new RelayEvent(null, null);

		EventDay expected = new EventDay(LocalDate.now());

		sut.setEventDay(expected);

		EventDay result = sut.getEventDay();

		assertEquals(expected, result);
	}

	@Test
	public void testCreateValidInstance() {
		EventName eventName = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDay = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventName, eventDay);

		EventName actualName = sut.getName();
		EventDay actualEventDay = sut.getEventDay();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDay] not correct.", eventDay, actualEventDay);
	}

	@Test
	public void testUUID() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);

		assertNotNull("Expected valid instance.", sut.getUUID());
	}

	@Test
	public void testEqualWithUuid() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = new RelayEvent(eventNameDummy, eventDayDummy);

		assertNotEquals(sut, secondSut);

	}

	@Test
	public void testSetUuid() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = new RelayEvent(eventNameDummy, eventDayDummy);

		secondSut.setUUID(sut.getUUID());

		assertEquals(sut, secondSut);
	}
}