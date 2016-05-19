package com.relayd;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.relayd.attributes.EventName;

import static org.junit.Assert.*;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class RelayEventTest {

	@Test
	public void testCreateValidInstance() {
		EventName eventName = new EventName("MetroGroup Marathon Düsseldorf");
		Date eventDate = Calendar.getInstance().getTime();

		RelayEvent sut = new RelayEvent(eventName, eventDate);

		EventName actualName = sut.getName();
		Date actualEventDate = sut.getEventDate();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDate] not correct.", eventDate, actualEventDate);
	}
}