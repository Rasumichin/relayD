package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Test code is just as important as production code.
 * It is not a second-class citizen.
 * It requires thought, design, and care.
 * It must be kept as clean as production code.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @since 23.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayTest {

	@Test
	public void testIsSerializable() {
		Relay sut = Relay.newInstance();

		@SuppressWarnings("cast")
		boolean actual = sut instanceof Serializable;

		assertTrue("Class not Serializable!", actual);
	}

	@Test
	public void testConstructor() {
		Relay sut = Relay.newInstance();

		assertNotNull("[UUID] not correct!", sut.getUuid());
	}

	@Test
	public void testConstructor_ForParameterRelayEvent() {
		RelayEvent expected = RelayEvent.duesseldorf();

		Relay sut = Relay.newInstance(expected);

		RelayEvent actual = sut.getRelayEvent();

		assertEquals("[relayEvent ] not correct!", expected, actual);
		assertNotNull("[UUID] not correct!", sut.getUuid());
	}

	@Test
	public void testRelayname() {
		Relay sut = Relay.newInstance();

		Relayname expected = Relayname.newInstance("Die vier ????");

		sut.setRelayname(expected);

		Relayname actual = sut.getRelayname();

		assertEquals("[Relayname] not corret!", expected, actual);
	}

	@Test
	public void testAddPerson_ForPositionFirst() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Participant expected = Participant.newInstance();
		expected.setForename(Forename.newInstance("Justus"));
		expected.setSurename(Surename.newInstance("Jonas"));

		sut.addParticipant(expected, Position.FIRST);

		Participant actual = sut.getParticipantFor(Position.FIRST);

		assertEquals("Person on first position is wrong!", expected, actual);
		assertNull("second position not null!", sut.getParticipantFor(Position.SECOND));
		assertNull("third position not null!", sut.getParticipantFor(Position.THIRD));
		assertNull("fourth position not null!", sut.getParticipantFor(Position.FOURTH));
	}

	@Test
	public void testAddPerson_ForPositionSecond() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Participant expected = Participant.newInstance();
		expected.setForename(Forename.newInstance("Justus"));
		expected.setSurename(Surename.newInstance("Jonas"));

		sut.addParticipant(expected, Position.SECOND);

		Participant actual = sut.getParticipantFor(Position.SECOND);

		assertNull("first position not null!", sut.getParticipantFor(Position.FIRST));
		assertEquals("Person on second position is wrong!", expected, actual);
		assertNull("third position not null!", sut.getParticipantFor(Position.THIRD));
		assertNull("fourth position not null!", sut.getParticipantFor(Position.FOURTH));
	}

	@Test
	public void testAddPerson_ForPositionThird() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Participant expected = Participant.newInstance();
		expected.setForename(Forename.newInstance("Justus"));
		expected.setSurename(Surename.newInstance("Jonas"));

		sut.addParticipant(expected, Position.THIRD);

		Participant actual = sut.getParticipantFor(Position.THIRD);

		assertNull("first position not null!", sut.getParticipantFor(Position.FIRST));
		assertNull("second position not null!", sut.getParticipantFor(Position.SECOND));
		assertEquals("Person on third position is wrong!", expected, actual);
		assertNull("fourth position not null!", sut.getParticipantFor(Position.FOURTH));
	}

	@Test
	public void testAddPerson_ForPositionFourth() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Participant expected = Participant.newInstance();
		expected.setForename(Forename.newInstance("Justus"));
		expected.setSurename(Surename.newInstance("Jonas"));

		sut.addParticipant(expected, Position.FOURTH);

		Participant actual = sut.getParticipantFor(Position.FOURTH);

		assertNull("first position not null!", sut.getParticipantFor(Position.FIRST));
		assertNull("second position not null!", sut.getParticipantFor(Position.SECOND));
		assertNull("third position not null!", sut.getParticipantFor(Position.THIRD));
		assertEquals("Person on fourth position is wrong!", expected, actual);
	}

	@Test
	public void testToString() {
		Relay sut = Relay.newInstance();

		String relayname = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(relayname));

		assertEquals("Relay: " + relayname, sut.toString());
	}

	@Test
	public void testHashCode() {
		Relay sut = Relay.newInstance();
		sut.uuid = UUID.fromString("2697d710-8967-4b2d-9ab2-8fc50ddc6138");

		int hashCode = sut.hashCode();

		assertEquals(949908191, hashCode);

		sut.uuid = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Relay sut = Relay.newInstance();

		boolean actual = sut.equals(sut);

		assertTrue(actual);
	}

	@Test
	public void testEqualsWithNull() {
		Relay sut = Relay.newInstance();

		boolean actual = sut.equals(null);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Relay sut = Relay.newInstance();

		boolean actual = sut.equals(new String());

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Relay sut = Relay.newInstance();
		sut.uuid = null;
		Relay secondSut = Relay.newInstance();

		boolean actual = sut.equals(secondSut);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Relay sut = Relay.newInstance();
		sut.uuid = null;
		Relay secondSut = Relay.newInstance();
		secondSut.uuid = null;

		boolean actual = sut.equals(secondSut);

		assertTrue(actual);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Relay sut = Relay.newInstance();
		Relay secondSut = Relay.newInstance();

		boolean actual = sut.equals(secondSut);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithSameValues() {
		Relay sut = Relay.newInstance();
		Relay secondSut = Relay.newInstance();
		sut.uuid = secondSut.uuid;

		boolean actual = sut.equals(secondSut);

		assertTrue(actual);
	}
}