package com.relayd.entity.migration;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * First Law of TDD: You may not write production code until you have written a failing unit test.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountRelayEntityServiceTest {
	private CountRelayEntityService sut = DefaultRelayEntityService.newInstance();

	
	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test
	public void testCountRelays() {
		Integer expected = Integer.valueOf(0);
		
		Integer actual = sut.countRelays();
		
		assertEquals("Number of 'relays' were not counted correctly!", expected, actual);
	}

	@Test
	public void testCountParticipants() {
		Integer expected = Integer.valueOf(0);
		
		Integer actual = sut.countParticipants();
		
		assertEquals("Number of 'participants' were not counted correctly!", expected, actual);
	}
}
