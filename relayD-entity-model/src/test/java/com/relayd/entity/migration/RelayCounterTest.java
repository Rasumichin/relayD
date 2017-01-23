package com.relayd.entity.migration;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Third Law of TDD: You may not write more production code than is sufficient to pass the currently failing test.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  23.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayCounterTest {
	private RelayCounter sut = RelayCounter.newIntance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance has not been created!", sut);
	}
	
	@Test
	public void testGetRelays() {
		Integer expected = RelayCounter.NOT_YET_COUNTED_VALUE;
		
		Integer actual = sut.getRelays();
		
		assertEquals("[relays] has not been correctly initialized!", expected, actual);
	}

	@Test
	public void testGetParticipants() {
		Integer expected = RelayCounter.NOT_YET_COUNTED_VALUE;
		
		Integer actual = sut.getParticipants();
		
		assertEquals("[participants] has not been correctly initialized!", expected, actual);
	}
	
	@Test
	public void testSetRelayCount() {
		Integer expected = Integer.valueOf(0);
		
		sut.setRelayCount(expected);
		
		Integer actual = sut.getRelays();
		assertEquals("[relays] has not been counted correctly!", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetRelayCount_With_Negative_Value() {
		sut.setRelayCount(Integer.valueOf(-10));
	}

	@Test
	public void testSetParticipantCount() {
		Integer expected = Integer.valueOf(0);
		
		sut.setParticipantCount(expected);
		
		Integer actual = sut.getParticipants();
		assertEquals("[participants] has not been counted correctly!", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetParticipantCount_With_Negative_Value() {
		sut.setParticipantCount(Integer.valueOf(-10));
	}
}
