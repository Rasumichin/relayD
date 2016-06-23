package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   21.06.2016
 * status   initial
 */
public abstract class RelayEventGatewayTest {

	private static final String KÖLN_MARATHON = "Köln Marathon";
	private static final EventDay KÖLN_DAY = new EventDay(LocalDate.of(2017, Calendar.OCTOBER, 4));

	private static final String DÜSSELDORF_MARATHON = "Düsseldorf Marathon";
	private static final EventDay DÜSSELDORF_DAY = new EventDay(LocalDate.of(2017, Calendar.APRIL, 30));

	public abstract RelayEventGateway getSut();

	@Test
	public void testGetForExistingEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		getSut().set(duesseldorfMarathon);
		getSut().set(createEventForKoelnMarathon());

		RelayEvent result = getSut().get(duesseldorfMarathon.getUUID());

		assertEquals(duesseldorfMarathon.getName(), result.getName());
	}

	@Test
	public void testGetForNonEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		getSut().set(duesseldorfMarathon);
		getSut().set(createEventForKoelnMarathon());

		RelayEvent result = getSut().get(UUID.randomUUID());

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		getSut().set(createEventForDuesseldorfMarathon());
		getSut().set(createEventForKoelnMarathon());

		List<RelayEvent> result = getSut().getAll();
		assertEquals(2, result.size());
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		EventName eventName = new EventName(DÜSSELDORF_MARATHON);
		EventDay eventDay = DÜSSELDORF_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}

	private RelayEvent createEventForKoelnMarathon() {
		EventName eventName = new EventName(KÖLN_MARATHON);
		EventDay eventDay = KÖLN_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}

	@Test
	public void remove() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		getSut().set(duesseldorfMarathon);
		UUID uuid = duesseldorfMarathon.getUUID();
		assertEquals("Error for TestFile. ", 1, getSut().getAll().size());

		getSut().remove(uuid);

		RelayEvent relayEvent = getSut().get(uuid);

		assertNull("Expected invalid instance.", relayEvent);
	}

	@Test
	public void update() {
		// ARRANGE
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		UUID uuidFromUpdateRelayEvent = duesseldorfMarathon.getUUID();

		getSut().set(duesseldorfMarathon);
		getSut().set(createEventForKoelnMarathon());

		RelayEvent updateEvent = getSut().get(uuidFromUpdateRelayEvent);

		EventName newName = new EventName("Rund um Ennepetal");
		updateEvent.setName(newName);

		// ACT
		getSut().set(updateEvent);

		// ASSERT
		RelayEvent checkEvent = getSut().get(uuidFromUpdateRelayEvent);
		assertNotNull(checkEvent);
		assertEquals(newName, checkEvent.getName());
	}
}
