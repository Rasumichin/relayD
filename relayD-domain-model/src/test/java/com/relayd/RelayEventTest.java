package com.relayd;

import java.time.LocalDate;
import java.time.Month;
import org.junit.Test;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

import static org.junit.Assert.*;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 * status initial
 * 
 */
public class RelayEventTest {

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
}