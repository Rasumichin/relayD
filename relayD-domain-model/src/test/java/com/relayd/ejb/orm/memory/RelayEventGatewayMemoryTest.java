package com.relayd.ejb.orm.memory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventName;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public class RelayEventGatewayMemoryTest {
	private RelayEventGatewayMemory sut = new RelayEventGatewayMemory();

	private static final String KÖLN_MARATHON = "Köln Marathon";
	private static final Date KÖLN_DAY = new GregorianCalendar(2017, Calendar.OCTOBER, 4).getTime();

	private static final String DÜSSELDORF_MARATHON = "Düsseldorf Marathon";
	private static final Date DÜSSELDORF_DAY = new GregorianCalendar(2017, Calendar.APRIL, 30).getTime();

	@Before
	public void setUp() {
		RelayEventGatewayMemory.events.clear();
	}

	@Test
	public void testSet() {
		sut.set(createEventForDuesseldorfMarathon());

		assertFalse(RelayEventGatewayMemory.events.isEmpty());
		assertEquals(1, RelayEventGatewayMemory.events.size());
	}

	@Test
	public void testGetForExistingEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		sut.set(duesseldorfMarathon);
		sut.set(createEventForKoelnMarathon());

		RelayEvent result = sut.get(duesseldorfMarathon.getName(), duesseldorfMarathon.getEventDate());

		assertEquals(duesseldorfMarathon.getName(), result.getName());
	}

	@Test
	public void testGetForNonEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		sut.set(duesseldorfMarathon);
		sut.set(createEventForKoelnMarathon());

		Date invalidDate = new Date();
		RelayEvent result = sut.get(duesseldorfMarathon.getName(), invalidDate);

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		sut.set(createEventForDuesseldorfMarathon());
		sut.set(createEventForKoelnMarathon());

		List<RelayEvent> result = sut.getAll();
		assertEquals(2, result.size());
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		EventName eventName = new EventName(DÜSSELDORF_MARATHON);
		Date eventDay = DÜSSELDORF_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}

	private RelayEvent createEventForKoelnMarathon() {
		EventName eventName = new EventName(KÖLN_MARATHON);
		Date eventDay = KÖLN_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}
}