package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;

public class RelayEventEditPageBeanTest {

	@Test
	public void testCreateRelayEvent() {
		RelayEventEditPageBean sut = new RelayEventEditPageBean();
		sut.setName("Rhein Ruhr Marathon");

		// 16.04.2016
		Date date = new Date(1460757600000L);
		sut.setDate(date);

		EventDay expectedDay = new EventDay(LocalDate.of(2016, Month.APRIL, 16));

		RelayEvent relayEvent = sut.createRelayEvent();

		assertNotNull("Expected valid instance.", relayEvent);
		assertEquals(expectedDay, relayEvent.getEventDay());
	}
}