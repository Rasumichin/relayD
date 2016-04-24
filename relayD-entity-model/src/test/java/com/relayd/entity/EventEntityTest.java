package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 22.04.2016
 * status initial
 */
public class EventEntityTest {
	int LENGTH_OF_CORRECT_UUID_STRING = 36;

	@Test
	public void testInstanceIsCreatedWithValidIdentity() {
		EventEntity sut = new EventEntity.Builder("title").build();
		assertNotNull("Id of EventEntity must not be 'null' after createion.", sut.getId());
		assertTrue("Id of EventEntity is not properly initialized.", sut.getId().length() == LENGTH_OF_CORRECT_UUID_STRING);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstanceCannotBeCreatedWithouTitle() {
		@SuppressWarnings("unused")
		EventEntity sut= new EventEntity.Builder(null).build();
	}
	
	@Test
	public void testInstanceCreatedWithValidTitle() {
		String validTitle = "My Event";
		EventEntity sut = new EventEntity.Builder(validTitle).build();
		assertEquals("'title' has not been set correctly.", validTitle, sut.getTitle());
	}

	@Test
	public void testInstanceCreatedWithValidYearHappened() {
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		EventEntity sut = new EventEntity.Builder("title").withYearHappened(currentYear).build();
		assertEquals("'YearHappened' has not been set correctly.", currentYear, sut.getYearHappened());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInstanceCreatedWithInvalidYearHappened() {
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer yearBeforeCurrentYear = currentYear - 1;
		
		@SuppressWarnings("unused")
		EventEntity sut = new EventEntity.Builder("title").withYearHappened(yearBeforeCurrentYear).build();
	}
}
