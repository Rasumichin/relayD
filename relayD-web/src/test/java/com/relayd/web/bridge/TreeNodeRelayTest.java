package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.PersonRelay;
import com.relayd.attributes.Relayname;

/**
 * The time to write good code is at the time you are writing it.
 *  - Daniel Read
 *
 * @author schmollc (Christian@relayd.de)
 * @since 11.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRelayTest {

	@Test
	public void testIsSerializable() {
		Relayname dummyRelayname = Relayname.newInstance("Dummy String");

		TreeNodeRelay sut = TreeNodeRelay.newInstance(dummyRelayname);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance_ForParameterRelayName() {
		Relayname expected = Relayname.newInstance("Die 4 ????");
		TreeNodeRelay sut = TreeNodeRelay.newInstance(expected);

		assertNotNull("Expected valid instance!", sut);

		Relayname actual = sut.getName();

		assertEquals("Relayname not corret!", expected, actual);
	}

	@Test
	public void testCreateInstance_ForParameterPersonRelay() {
		UUID uuid = UUID.randomUUID();
		PersonRelay expected = PersonRelay.newInstance();
		expected.setUUID(uuid);

		TreeNodeRelay sut = TreeNodeRelay.newInstance(expected);

		assertNotNull("Expected valid instance!", sut);

		PersonRelay actual = sut.getParticipant();

		assertEquals("PersonRelay not corret!", expected, actual);
	}
}