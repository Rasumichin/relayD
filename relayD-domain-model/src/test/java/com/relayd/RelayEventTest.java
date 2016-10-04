package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.Test;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * Keine Straße ist zu lang mit einem Test an der Seite.
 *  - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 *
 */
public class RelayEventTest {

	@Test
	public void testName() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		EventName expected = EventName.newInstance("Name");

		sut.setName(expected);

		EventName result = sut.getName();

		assertEquals(expected, result);
	}

	@Test
	public void testEventDay() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		EventDay expected = EventDay.newInstance(LocalDate.now());

		sut.setEventDay(expected);

		EventDay result = sut.getEventDay();

		assertEquals(expected, result);
	}

	@Test
	public void testCreateValidInstance() {
		EventName eventName = EventName.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventName, eventDay);

		EventName actualName = sut.getName();
		EventDay actualEventDay = sut.getEventDay();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDay] not correct.", eventDay, actualEventDay);
	}

	@Test
	public void testUUID() {
		EventName eventNameDummy = EventName.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		assertNotNull("Expected valid instance.", sut.getUUID());
	}

	@Test
	public void testSetUUID() {
		EventName eventNameDummy = EventName.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		secondSut.setUUID(sut.getUUID());

		assertEquals(sut, secondSut);
	}

	@Test
	public void testGetHashCode() {
		RelayEvent sut = RelayEvent.newInstance(null, null);
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
		RelayEvent sut = RelayEvent.newInstance(null, null);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithUUIDIsNull() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		sut.setUUID(null);

		boolean result = sut.equals(secondEvent);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUUIDAreNull() {
		RelayEvent sut = RelayEvent.newInstance(null, null);
		sut.setUUID(null);

		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		secondEvent.setUUID(null);

		boolean result = sut.equals(secondEvent);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUuid() {
		EventName eventNameDummy = EventName.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		assertNotEquals(sut, secondSut);
	}
}