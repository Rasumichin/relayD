package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

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

	@Test
	public void testUUID() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);

		assertNotNull("Expected valid instance.", sut.getUuid());
	}

	@Test
	public void testEqualWithUuid() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = new RelayEvent(eventNameDummy, eventDayDummy);

		assertNotEquals(sut, secondSut);

	}

	@Test
	public void testSetUuid() {
		EventName eventNameDummy = new EventName("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = new EventDay(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent sut = new RelayEvent(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = new RelayEvent(eventNameDummy, eventDayDummy);

		secondSut.setUuid(sut.getUuid());

		assertEquals(sut, secondSut);
	}
}