package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
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

		assertEquals("[relayEvent] not correct!", expected, actual);
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
	public void testAddParticipant_WithoutPositionForEmptyRelay() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected);

		Participant actual = sut.getParticipantFor(Position.FIRST);

		assertEquals("Person on first position is wrong!", expected, actual);
		assertTrue("second position not empty!", sut.getParticipantFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getParticipantFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getParticipantFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddParticipant_WithoutPositionForRelayWithEntryOnFirstPosition() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person justusJonas = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant firstParticipant = Participant.newInstance(justusJonas);

		sut.addParticipant(firstParticipant, Position.FIRST);

		Person person = new PersonBuilder().withForename("Peter").withSurename("Shaw").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected);

		Participant actual = sut.getParticipantFor(Position.SECOND);

		assertEquals("Perosn on first position is wrong!", firstParticipant, sut.getParticipantFor(Position.FIRST));
		assertEquals("Person on second position is wrong!", expected, actual);
		assertTrue("third position not empty!", sut.getParticipantFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getParticipantFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddParticipant_WithoutPositionForRelayWithSpaceOnPositionThree() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person justusJonas = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant firstParticipant = Participant.newInstance(justusJonas);

		Person peterShaw = new PersonBuilder().withForename("Peter").withSurename("Shaw").build();
		Participant secondParticipant = Participant.newInstance(peterShaw);

		Person skinnyNoris = new PersonBuilder().withForename("Skinny").withSurename("Norris").build();
		Participant fourthParticipant = Participant.newInstance(skinnyNoris);

		sut.addParticipant(firstParticipant, Position.FIRST);
		sut.addParticipant(secondParticipant, Position.SECOND);
		sut.addParticipant(fourthParticipant, Position.FOURTH);

		Person person = new PersonBuilder().withForename("Bob").withSurename("Andrews").build();
		Participant expected = Participant.newInstance(person);

		sut.addParticipant(expected);

		Participant actual = sut.getParticipantFor(Position.THIRD);

		assertEquals("Person on first position is wrong!", firstParticipant, sut.getParticipantFor(Position.FIRST));
		assertEquals("Person on second position is wrong!", secondParticipant, sut.getParticipantFor(Position.SECOND));
		assertEquals("Person on third position is wrong!", expected, actual);
		assertEquals("Person on fourth position is wrong!", fourthParticipant, sut.getParticipantFor(Position.FOURTH));
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
	public void testGetParticipants() {
		Relay sut = Relay.newInstance();
		Participant firstParticipant = Participant.newInstance(new PersonBuilder().build());
		Participant secondParticipant = Participant.newInstance(new PersonBuilder().build());
		sut.addParticipant(firstParticipant, Position.FIRST);
		sut.addParticipant(secondParticipant, Position.SECOND);

		List<Participant> actual = sut.getParticipants();

		assertNotNull("instance not correct!", actual);
		assertEquals("size of List not correct!", 4, actual.size());
		assertEquals("Participant at position 0 not correct!", firstParticipant, actual.get(0));
		assertEquals("Participant at position 1 not correct!", secondParticipant, actual.get(1));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetParticipants_ForUnmodifiable() {
		Relay sut = Relay.newInstance();

		List<Participant> actual = sut.getParticipants();

		actual.add(Participant.newInstance());
	}

	@Test
	public void testGetEmails_ForEmptyParticipantList() {
		Relay sut = Relay.newInstance();

		String actual = sut.getEmailList();

		assertTrue("[getEmailList] not correct!", actual.isEmpty());
	}

	@Test
	public void testGetEmails_ForOneParticipant() {
		String expected = "Justus.Jonas@canda.com";
		Person justusJonas = new PersonBuilder().withEmail(expected).build();
		Participant one = Participant.newInstance(justusJonas);

		Relay sut = Relay.newInstance();
		sut.addParticipant(one, Position.FIRST);

		String actual = sut.getEmailList();

		assertEquals("[getEmailList] not correct!", expected, actual);
	}

	@Test
	public void testGetEmails_ForFourParticipant() {
		Person justusJonas = new PersonBuilder().withEmail("Justus.Jonas@canda.com").build();
		Participant one = Participant.newInstance(justusJonas);
		Person peterShaw = new PersonBuilder().withEmail("Peter.Shaw@canda.com").build();
		Participant two = Participant.newInstance(peterShaw);
		Person bobAndrews = new PersonBuilder().withEmail("Bob.Andrews@canda.com").build();
		Participant three = Participant.newInstance(bobAndrews);
		Person skinnyNorris = new PersonBuilder().withEmail("Skinny.Norris@canda.com").build();
		Participant four = Participant.newInstance(skinnyNorris);

		Relay sut = Relay.newInstance();
		sut.addParticipant(one, Position.FIRST);
		sut.addParticipant(two, Position.SECOND);
		sut.addParticipant(three, Position.THIRD);
		sut.addParticipant(four, Position.FOURTH);

		String actual = sut.getEmailList();

		String expected = "Justus.Jonas@canda.com, Peter.Shaw@canda.com, Bob.Andrews@canda.com, Skinny.Norris@canda.com";
		assertEquals("[getEmailList] not correct!", expected, actual);
	}

	@Test
	public void testDuration() {

		Relay sut = Relay.newInstance();

		Duration expected = Duration.ofHours(3).plusMinutes(33).plusSeconds(13);

		sut.setDuration(expected);

		Duration actual = sut.getDuration();

		assertEquals("[Duration] not corret!", expected, actual);
	}

	@Test
	public void testGetDurationFormatted_ForNull() {
		Relay sut = Relay.newInstance();
		sut.setDuration(null);

		String actual = sut.getDurationFormatted();
		String expected = "00:00:00";

		assertEquals("[Duration] not corret!", expected, actual);

	}

	@Test
	public void testGetDurationFormatted_ForValue() {
		Relay sut = Relay.newInstance();
		Duration duration = Duration.ofHours(3).plusMinutes(33).plusSeconds(13);
		sut.setDuration(duration);

		String actual = sut.getDurationFormatted();
		String expected = "03:33:13";

		assertEquals("[Duration] not corret!", expected, actual);

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

	@Test
	public void testIsParticipant_forExistingParticipant() {
		Person justus = Person.newInstance();
		Person peter = Person.newInstance();
		Person bob = Person.newInstance();

		Relay sut = Relay.newInstance();

		Participant participant = Participant.newInstance(justus);
		sut.addParticipant(participant, Position.FIRST);
		participant = Participant.newInstance(peter);
		sut.addParticipant(participant, Position.SECOND);
		participant = Participant.newInstance(bob);
		sut.addParticipant(participant, Position.THIRD);

		boolean condition = sut.isParticipant(peter);

		assertTrue("Person isn't participant of relay!", condition);

	}

	@Test
	public void testIsParticipant_forNonExistingParticipant() {
		Person justus = Person.newInstance();
		Person peter = Person.newInstance();
		Person bob = Person.newInstance();
		Person skinny = Person.newInstance();

		Relay sut = Relay.newInstance();

		Participant participant = Participant.newInstance(justus);
		sut.addParticipant(participant, Position.FIRST);
		participant = Participant.newInstance(peter);
		sut.addParticipant(participant, Position.SECOND);
		participant = Participant.newInstance(bob);
		sut.addParticipant(participant, Position.THIRD);

		boolean condition = sut.isParticipant(skinny);

		assertFalse("Person is participant of relay!", condition);

	}
}