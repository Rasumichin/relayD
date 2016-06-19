package com.relayd.entity;

import java.sql.Date;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 * status initial
 * 
 */
public class RelayEventEntityTest {
	int LENGTH_OF_CORRECT_UUID_STRING = 36;

	@Test
	public void testInstanceIsCreatedWithValidIdentity() {
		RelayEventEntity sut = new RelayEventEntity.Builder("name").build();
		assertNotNull("Id of EventEntity must not be 'null' after creation.", sut.getId());
		assertTrue("Id of EventEntity is not properly initialized.", sut.getId().length() == LENGTH_OF_CORRECT_UUID_STRING);
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
		// Suffice to the wonderful way how the old Date API counts months ;-)
		int june = 5;
		@SuppressWarnings("deprecation")
		Date eventDay = new Date(2017, june, 24);
		
		RelayEventEntity sut = new RelayEventEntity.Builder("name").withEventDay(eventDay).build();
		assertEquals("[eventDay] has not been set correctly.", eventDay, sut.getEventDay());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstanceCreatedWithIllegalNullValueForEventDay() {
		new RelayEventEntity.Builder("name").withEventDay(null).build();
	}
}
