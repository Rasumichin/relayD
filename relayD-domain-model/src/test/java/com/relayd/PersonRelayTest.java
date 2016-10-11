package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * The best way to a breakthrough is constant small improvement - those waiting for the big break are just lazy,
 * they're waiting to be teleported to the top of the hill instead of walking.
 *  - Gary Starkweather
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   11.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonRelayTest {

	@Test
	public void testIsSerializable() {
		PersonRelay sut = PersonRelay.newInstance();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testUUID() {
		UUID expected = UUID.randomUUID();

		PersonRelay sut = PersonRelay.newInstance();

		sut.setUUID(expected);

		UUID actual = sut.getUUID();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testForename() {
		Forename expected = Forename.newInstance("Jonas");

		PersonRelay sut = PersonRelay.newInstance();

		sut.setForename(expected);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testSurename() {
		Surename expected = Surename.newInstance("Justus");

		PersonRelay sut = PersonRelay.newInstance();

		sut.setSurename(expected);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

}
