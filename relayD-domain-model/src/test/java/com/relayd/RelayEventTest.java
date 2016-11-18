package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;

/**
 * Keine Straße ist zu lang mit einem Test an der Seite.
 *  - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventTest {

	@Test
	public void testName() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		Eventname expected = Eventname.newInstance("Name");

		sut.setName(expected);

		Eventname result = sut.getName();

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
	public void testCreateDefaultInstance() {
		Eventname eventName = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.duesseldorf();

		Eventname actualName = sut.getName();
		EventDay actualEventDay = sut.getEventDay();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDay] not correct.", eventDay, actualEventDay);
	}

	@Test
	public void testCreateValidInstance() {
		Eventname eventName = Eventname.newInstance("MetroGroup Marathon Essen");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2015, Month.MARCH, 17));

		RelayEvent sut = RelayEvent.newInstance(eventName, eventDay);

		Eventname actualName = sut.getName();
		EventDay actualEventDay = sut.getEventDay();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDay] not correct.", eventDay, actualEventDay);
	}

	@Test
	public void testUuid() {
		Eventname eventNameDummy = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		assertNotNull("Expected valid instance.", sut.getUuid());
	}

	@Test
	public void testSetUuid() {
		Eventname eventNameDummy = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		secondSut.setUuid(sut.getUuid());

		assertEquals(sut, secondSut);
	}

	@Test
	public void testGetMaxNumberOfRelays() {
		RelayEvent sut = RelayEvent.duesseldorf();

		Integer actual = sut.getMaxNumberOfRelays();

		assertEquals(Integer.valueOf(18), actual);
	}

	@Test
	public void testGetNumberOfRelays_ForEmptyRelayList() {
		RelayEvent sut = RelayEvent.duesseldorf();

		Integer actual = sut.getNumberOfRelays();

		assertEquals(Integer.valueOf(0), actual);
	}

	@Test
	public void testGetHashCode() {
		RelayEvent sut = RelayEvent.newInstance(null, null);
		UUID uuid = UUID.fromString("53a27b33-a5cb-4997-8eaf-dcf8bd1cb2d2");
		sut.setUuid(uuid);

		int hashCode = sut.hashCode();

		assertEquals(-975545171, hashCode);

		sut.setUuid(null);

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
	public void testEqualsWithUuidIsNull() {
		RelayEvent sut = RelayEvent.newInstance(null, null);

		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		sut.setUuid(null);

		boolean result = sut.equals(secondEvent);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUuidAreNull() {
		RelayEvent sut = RelayEvent.newInstance(null, null);
		sut.setUuid(null);

		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		secondEvent.setUuid(null);

		boolean result = sut.equals(secondEvent);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUuid() {
		Eventname eventNameDummy = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		assertNotEquals(sut, secondSut);
	}
}