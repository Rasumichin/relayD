package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
public class RelayEventEditPageBeanTest {

	@Test
	public void testCreateRelayEvent() {
		RelayEventEditPageBean sut = new RelayEventEditPageBean();
		EventName name = new EventName("Rhein Ruhr Marathon");
		sut.setName(name);

		EventDay day = new EventDay(LocalDate.of(2016, Month.APRIL, 16));

		sut.setDate(day);

		RelayEvent relayEvent = sut.createRelayEvent();

		assertNotNull("Expected valid instance.", relayEvent);
		assertEquals("[Name] not correct.", name, relayEvent.getName());
		assertEquals("[EventDay] not correct.", day, relayEvent.getEventDay());
	}
}