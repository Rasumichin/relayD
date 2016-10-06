package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

/**
 * Es gibt nur eine Zeit, in der es wesentlich ist tests zu schreiben.
 * Diese Zeit ist jetzt.
 *  - Siddhartha Gautama
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 *
 */
public class RelayEventEntityTest {
	private int LENGTH_OF_CORRECT_UUID_STRING = 36;

	@Test
	public void testInstanceIsCreatedWithValidIdentity() {
		RelayEventEntity sut = new RelayEventEntity.Builder("name").build();
		assertNotNull("Id of EventEntity must not be 'null' after creation.", sut.getId());
		assertEquals("Id of EventEntity is not properly initialized.", sut.getId().length(), LENGTH_OF_CORRECT_UUID_STRING);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstanceCannotBeCreatedWithoutEventName() {
		@SuppressWarnings("unused")
		RelayEventEntity sut = new RelayEventEntity.Builder(null).build();
	}

	@Test
	public void testInstanceCreatedWithValidEventName() {
		String validEventName = "My Event";
		RelayEventEntity sut = new RelayEventEntity.Builder(validEventName).build();
		assertEquals("[eventName] has not been set correctly.", validEventName, sut.getEventName());
	}

	@Test
	public void testInstanceCreatedWithValidEventDay() {
		@SuppressWarnings("deprecation")
		Date eventDay = new Date(2017, Calendar.JUNE, 24);

		RelayEventEntity sut = new RelayEventEntity.Builder("name").withEventDay(eventDay).build();
		assertEquals("[eventDay] has not been set correctly.", eventDay, sut.getEventDay());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstanceCreatedWithIllegalNullValueForEventDay() {
		new RelayEventEntity.Builder("name").withEventDay(null).build();
	}
}
