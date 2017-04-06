package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;

/**
 * Es bleibt einem jeden immer noch soviel Kraft, das auszuführen, wovon er überzeugt ist.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   21.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class RelayEventGatewayTest {

	private static final String DUESSELDORF_MARATHON = "Metro Group Marathon Düsseldorf";
	private static final EventDay DUESSELDORF_DAY = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

	public abstract RelayEventGateway getSut();

	@Test
	@Ignore("Muessen eine Lösung bzgl Event<->Relays finden")
	public void testGetAll() {
		List<RelayEvent> resultRelayEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, resultRelayEventList.size());

		RelayEvent resultRelayEvent = resultRelayEventList.get(0);
		RelayEvent expectedRelayEvent = createEventForDuesseldorfMarathon();

		assertEquals("[name] not correct!", expectedRelayEvent.getName(), resultRelayEvent.getName());
		assertEquals("[eventDay] not correct!", expectedRelayEvent.getEventDay(), resultRelayEvent.getEventDay());
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		Eventname eventName = Eventname.newInstance(DUESSELDORF_MARATHON);
		EventDay eventDay = DUESSELDORF_DAY;
		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);
		return relayEvent;
	}

	@Test
	@Ignore("Muessen eine Lösung bzgl Event<->Relays finden")
	public void testSet() {
		Eventname expectedEventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(expectedEventname, expectedEventDay);

		getSut().set(rundUmEnnepetal);
		List<RelayEvent> resultRelayEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 2, resultRelayEventList.size());

		RelayEvent resultRelayEventDuesseldorf = resultRelayEventList.get(0);
		RelayEvent expectedRelayEventDuesseldorf = createEventForDuesseldorfMarathon();

		assertEquals("[name] for Duesseldorf not correct!", expectedRelayEventDuesseldorf.getName(), resultRelayEventDuesseldorf.getName());
		assertEquals("[eventDay] for Duesseldorf not correct!", expectedRelayEventDuesseldorf.getEventDay(), resultRelayEventDuesseldorf.getEventDay());

		RelayEvent resultRelayEventRundUmEnnepetal = resultRelayEventList.get(1);

		assertEquals("[name] for Rund um Ennepetal not correct!", expectedEventname, resultRelayEventRundUmEnnepetal.getName());
		assertEquals("[eventDay] for Rund um Ennepetal not correct!", expectedEventDay, resultRelayEventRundUmEnnepetal.getEventDay());

	}

	@Test
	public void testGet_ForExistingEntry() {
		RelayEvent expected = createRundUmEnnepetal();

		getSut().set(createDuesseldorfMarathon());
		getSut().set(expected);

		RelayEvent actual = getSut().get(expected.getUuid());

		assertNotNull("[get] has incorrect return value!", actual);
		assertEquals("[get] not correct!", expected, actual);
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createDuesseldorfMarathon());
		getSut().set(createRundUmEnnepetal());

		RelayEvent actual = getSut().get(UUID.randomUUID());

		assertNull(actual);
	}

	private RelayEvent createDuesseldorfMarathon() {
		Eventname eventname = Eventname.newInstance("Metro Group Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));
		RelayEvent duesseldorfMarathon = RelayEvent.newInstance(eventname, eventDay);

		return duesseldorfMarathon;
	}

	private RelayEvent createRundUmEnnepetal() {
		Eventname eventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(eventname, eventDay);
		return rundUmEnnepetal;
	}

}