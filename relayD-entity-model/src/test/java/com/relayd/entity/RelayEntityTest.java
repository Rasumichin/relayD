package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Die Güte des Werkes ist nicht abhängig vom Werkzeug, sondern von demjenigen, der das Werkzeug bedient.
 *  - Unbekannt
 *
 * @author schmollc (Christian@relayd.de)
 * @since  18.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEntityTest {

	@Test
	public void testNewInstance() {
		RelayEntity sut = RelayEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		UUID expected = UUID.randomUUID();
		RelayEntity sut = RelayEntity.newInstance(expected);

		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		@SuppressWarnings("unused")
		RelayEntity sut = RelayEntity.newInstance(null);
	}

	@Test
	public void testRelayname() {
		String relayname = "Staubwolke";
		RelayEntity sut = RelayEntity.newInstance();

		sut.setRelayname(relayname);
		assertEquals("[relayname] has not been set correctly!", relayname, sut.getRelayname());
	}

	@Test
	public void testParticipantOne() {
		RelayEntity sut = RelayEntity.newInstance();

		UUID expected = UUID.randomUUID();

		sut.setParticipantOne(expected);

		UUID actual = sut.getParticipantOne();

		assertEquals("[participantOne] has not been set correctly!", expected, actual);
	}

	@Test
	public void testParticipantTwo() {
		RelayEntity sut = RelayEntity.newInstance();

		UUID expected = UUID.randomUUID();

		sut.setParticipantTwo(expected);

		UUID actual = sut.getParticipantTwo();

		assertEquals("[participantTwo] has not been set correctly!", expected, actual);
	}

	@Test
	public void testParticipantThree() {
		RelayEntity sut = RelayEntity.newInstance();

		UUID expected = UUID.randomUUID();

		sut.setParticipantThree(expected);

		UUID actual = sut.getParticipantThree();

		assertEquals("[participantThree] has not been set correctly!", expected, actual);
	}

	@Test
	public void testParticipantFour() {
		RelayEntity sut = RelayEntity.newInstance();

		UUID expected = UUID.randomUUID();

		sut.setParticipantFour(expected);

		UUID actual = sut.getParticipantFour();

		assertEquals("[participantFour] has not been set correctly!", expected, actual);
	}

	@Test
	public void testToString() {
		RelayEntity sut = RelayEntity.newInstance();
		String relayname = "Staubwolke";
		sut.setRelayname(relayname);

		String expected = "RelayEntity [id=" + sut.getId() + ", relayname=" + relayname + "]";

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId("5697d710-8967-4b2d-9ab2-8fc50ddc6138");

		int hashCode = sut.hashCode();

		assertEquals(2031501961, hashCode);

		sut.setId(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		RelayEntity sut = RelayEntity.newInstance();

		RelayEntity secondSut = RelayEntity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		RelayEntity sut = RelayEntity.newInstance();
		RelayEntity secondSut = RelayEntity.newInstance();
		secondSut.setId(sut.getId());

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId(null);

		RelayEntity secondSut = RelayEntity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId(null);

		RelayEntity secondSut = RelayEntity.newInstance();

		secondSut.setId(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}