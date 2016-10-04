package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * Es bleibt einem jeden immer noch soviel Kraft, das auszuführen, wovon er überzeugt ist.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   21.06.2016
 *
 */
public abstract class RelayEventGatewayTest {

	private static final String DUESSELDORF_MARATHON = "Metro Group Marathon Düsseldorf";
	private static final EventDay DUESSELDORF_DAY = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

	public abstract RelayEventGateway getSut();

	@Test
	public void testGetAll() {
		List<RelayEvent> resultRelayEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, resultRelayEventList.size());

		RelayEvent resultRelayEvent = resultRelayEventList.get(0);
		RelayEvent expectedRelayEvent = createEventForDuesseldorfMarathon();

		assertEquals("[name] not correct!", expectedRelayEvent.getName(), resultRelayEvent.getName());
		assertEquals("[eventDay] not correct!", expectedRelayEvent.getEventDay(), resultRelayEvent.getEventDay());
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		EventName eventName = EventName.newInstance(DUESSELDORF_MARATHON);
		EventDay eventDay = DUESSELDORF_DAY;
		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);
		return relayEvent;
	}
}