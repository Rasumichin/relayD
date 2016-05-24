package com.relayd.attributes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;
import org.junit.Test;

/**
 * @author  Rasumichin (Erik@relayd.com)
 * @since   24.05.2016
 * status   initial
 * 
 */
public class EventDayTest {

	@Test
	public void testCreateValidInstance() {
		LocalDate dateOfEvent = LocalDate.of(2001, Calendar.NOVEMBER, 21);
		EventDay sut = new EventDay(dateOfEvent);
		
		LocalDate actualDate = sut.getValue();
		assertEquals("Day of event does not match.", dateOfEvent, actualDate);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateInstanceWithIllegalNullValue() {
		@SuppressWarnings("unused")
		EventDay sut = new EventDay(null);
	}
	
	@Test
	public void createInstanceThatIsInThePast() {
		LocalDate dateOfEvent = LocalDate.of(2001, Calendar.NOVEMBER, 21);
		EventDay sut = new EventDay(dateOfEvent);
		
		boolean isInThePast = sut.isInThePast();
		assertTrue("Day of event is not in the past", isInThePast);
	}
}
