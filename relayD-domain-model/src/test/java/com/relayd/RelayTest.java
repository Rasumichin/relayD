package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;

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

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected, Position.FIRST);

		Participant actual = sut.getParticipantFor(Position.FIRST);

		assertEquals("Person on first position is wrong!", expected, actual);

		assertTrue("second position not empty!", sut.getParticipantFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getParticipantFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getParticipantFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionSecond() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected, Position.SECOND);

		Participant actual = sut.getParticipantFor(Position.SECOND);

		assertTrue("first position not empty!", sut.getParticipantFor(Position.FIRST).isEmpty());
		assertEquals("Person on second position is wrong!", expected, actual);
		assertTrue("third position not empty!", sut.getParticipantFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getParticipantFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionThird() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected, Position.THIRD);

		Participant actual = sut.getParticipantFor(Position.THIRD);

		assertTrue("first position not empty!", sut.getParticipantFor(Position.FIRST).isEmpty());
		assertTrue("second position not empty!", sut.getParticipantFor(Position.SECOND).isEmpty());
		assertEquals("Person on third position is wrong!", expected, actual);
		assertTrue("fourth position not empty!", sut.getParticipantFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionFourth() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected, Position.FOURTH);

		Participant actual = sut.getParticipantFor(Position.FOURTH);

		assertTrue("first position not empty!", sut.getParticipantFor(Position.FIRST).isEmpty());
		assertTrue("second position not empty!", sut.getParticipantFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getParticipantFor(Position.THIRD).isEmpty());
		assertEquals("Person on fourth position is wrong!", expected, actual);
	}

	@Test
	public void testParticipantCount_ForEmptyRelay() {
		Relay sut = Relay.newInstance();

		Integer actual = sut.participantCount();

		assertEquals("count not correct for empty participants!", Integer.valueOf(0), actual);
	}

	@Test
	public void testParticipantCount_ForTowParticipants() {
		Relay sut = Relay.newInstance();
		sut.addParticipant(Participant.newInstance(new PersonBuilder().build()), Position.FIRST);
		sut.addParticipant(Participant.newInstance(new PersonBuilder().build()), Position.SECOND);

		Integer actual = sut.participantCount();

		assertEquals("count not correct for empty participants!", Integer.valueOf(2), actual);
	}

	@Test
	public void testToString_ForRelayWithoutParticipant() {
		Relay sut = Relay.newInstance();

		String relayname = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(relayname));

		assertEquals(relayname + " [0/4]", sut.toString());
	}

	@Test
	public void testToString_ForRelayWithTwoParticipant() {
		Relay sut = Relay.newInstance();

		String relayname = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(relayname));
		sut.addParticipant(Participant.newInstance(new PersonBuilder().build()), Position.FIRST);
		sut.addParticipant(Participant.newInstance(new PersonBuilder().build()), Position.SECOND);

		assertEquals(relayname + " [2/4]", sut.toString());
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