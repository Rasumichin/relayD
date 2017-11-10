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
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testConstructor() {
		Relay sut = Relay.newInstance();

		assertNotNull("[UUID] not correct!", sut.getUuid());
	}

	@Test
	public void testConstructor_ForParameterRelayEvent() {
		RelayEvent expected = RelayEvent.newInstance();

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

		assertEquals("[relayname] not corret!", expected, actual);
	}

	@Test
	public void testAddParticipant_ForPositionFirst() {

		Relay sut = Relay.newInstance();

		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected, Position.FIRST);

		Member actual = sut.getMemberFor(Position.FIRST);

		assertEquals("Participant on first position is wrong!", expected, actual);

		assertTrue("Second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("Third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("Fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddParticipant_ForPositionSecond() {
		Relay sut = Relay.newInstance();

		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected, Position.SECOND);

		Member actual = sut.getMemberFor(Position.SECOND);

		assertTrue("First position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertEquals("Participant on second position is wrong!", expected, actual);
		assertTrue("Third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("Fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddParticipant_ForPositionThird() {
		Relay sut = Relay.newInstance();

		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected, Position.THIRD);

		Member actual = sut.getMemberFor(Position.THIRD);

		assertTrue("First position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertTrue("Second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertEquals("Participant on third position is wrong!", expected, actual);
		assertTrue("Fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddParticipant_ForPositionFourth() {
		Relay sut = Relay.newInstance();

		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected, Position.FOURTH);

		Member actual = sut.getMemberFor(Position.FOURTH);

		assertTrue("First position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertTrue("Second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("Third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertEquals("Participant on fourth position is wrong!", expected, actual);
	}

	@Test
	public void testAddMember_WithoutPositionForEmptyRelay() {
		Relay sut = Relay.newInstance();

		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.FIRST);

		assertEquals("Participant on first position is wrong!", expected, actual);
		assertTrue("Second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("Third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("Fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddMember_WithoutPositionForRelayWithEntryOnFirstPosition() {
		Relay sut = Relay.newInstance();

		Participant justusJonas = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member firstMember = Member.newInstance(justusJonas);

		sut.addMember(firstMember, Position.FIRST);

		Participant participant = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.SECOND);

		assertEquals("Participant on first position is wrong!", firstMember, sut.getMemberFor(Position.FIRST));
		assertEquals("Participant on second position is wrong!", expected, actual);
		assertTrue("Third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("Fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddMember_WithoutPositionForRelayWithSpaceOnPositionThree() {
		Relay sut = Relay.newInstance();

		Participant justusJonas = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Member firstMember = Member.newInstance(justusJonas);

		Participant peterShaw = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build();
		Member secondMember = Member.newInstance(peterShaw);

		Participant skinnyNoris = new ParticipantBuilder().withForename("Skinny").withSurename("Norris").build();
		Member fourthMember = Member.newInstance(skinnyNoris);

		sut.addMember(firstMember, Position.FIRST);
		sut.addMember(secondMember, Position.SECOND);
		sut.addMember(fourthMember, Position.FOURTH);

		Participant participant = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").build();
		Member expected = Member.newInstance(participant);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.THIRD);

		assertEquals("Participant on first position is wrong!", firstMember, sut.getMemberFor(Position.FIRST));
		assertEquals("Participant on second position is wrong!", secondMember, sut.getMemberFor(Position.SECOND));
		assertEquals("Participant on third position is wrong!", expected, actual);
		assertEquals("Participant on fourth position is wrong!", fourthMember, sut.getMemberFor(Position.FOURTH));
	}

	@Test
	public void testMemberCount_ForEmptyRelay() {
		Relay sut = Relay.newInstance();

		Integer actual = sut.memberCount();

		assertEquals("Count not correct for empty members!", Integer.valueOf(0), actual);
	}

	@Test
	public void testMemberCount_ForTowMembers() {
		Relay sut = Relay.newInstance();
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.SECOND);

		Integer actual = sut.memberCount();

		assertEquals("Count not correct for empty members!", Integer.valueOf(2), actual);
	}

	@Test
	public void testGetMembers() {
		Relay sut = Relay.newInstance();
		Member firstMember = Member.newInstance(new ParticipantBuilder().build());
		Member secondMember = Member.newInstance(new ParticipantBuilder().build());
		sut.addMember(firstMember, Position.FIRST);
		sut.addMember(secondMember, Position.SECOND);

		List<Member> actual = sut.getMembers();

		assertNotNull("Instance not correct!", actual);
		assertEquals("Size of List not correct!", 4, actual.size());
		assertEquals("Member at position 0 not correct!", firstMember, actual.get(0));
		assertEquals("Member at position 1 not correct!", secondMember, actual.get(1));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetMembers_ForUnmodifiable() {
		Relay sut = Relay.newInstance();

		List<Member> actual = sut.getMembers();

		actual.add(Member.newInstance());
	}

	@Test
	public void testGetMemberFor() {
		Relay sut = Relay.newInstance();
		Participant participant = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").build();
		Member bobAndrews = Member.newInstance(participant);
		sut.addMember(bobAndrews, Position.SECOND);

		Member actual = sut.getMemberFor(Position.UNKNOWN);
		assertNotEquals("[getMemberFor] Position.UNKNOWN not correct!", bobAndrews, actual);

		actual = sut.getMemberFor(Position.FIRST);
		assertNotEquals("[getMemberFor] Position.FIRST not correct!", bobAndrews, actual);

		actual = sut.getMemberFor(Position.SECOND);
		assertEquals("[getMemberFor] Position.SECOND not correct!", bobAndrews, actual);

		actual = sut.getMemberFor(Position.THIRD);
		assertNotEquals("[getMemberFor] Position.THIRD not correct!", bobAndrews, actual);

		actual = sut.getMemberFor(Position.FOURTH);
		assertNotEquals("[getMemberFor] Position.FOURTH not correct!", bobAndrews, actual);

	}

	@Test
	public void testGetEmails_ForEmptyMemberList() {
		Relay sut = Relay.newInstance();

		String actual = sut.getEmailList();

		assertTrue("[getEmailList] not correct!", actual.isEmpty());
	}

	@Test
	public void testGetEmails_ForOneMember() {
		String expected = "Justus.Jonas@canda.com";
		Participant justusJonas = new ParticipantBuilder().withEmail(expected).build();
		Member one = Member.newInstance(justusJonas);

		Relay sut = Relay.newInstance();
		sut.addMember(one, Position.FIRST);

		String actual = sut.getEmailList();

		assertEquals("[getEmailList] not correct!", expected, actual);
	}

	@Test
	public void testGetEmails_ForFourMember() {
		Participant justusJonas = new ParticipantBuilder().withEmail("Justus.Jonas@canda.com").build();
		Member one = Member.newInstance(justusJonas);
		Participant peterShaw = new ParticipantBuilder().withEmail("Peter.Shaw@canda.com").build();
		Member two = Member.newInstance(peterShaw);
		Participant bobAndrews = new ParticipantBuilder().withEmail("Bob.Andrews@canda.com").build();
		Member three = Member.newInstance(bobAndrews);
		Participant skinnyNorris = new ParticipantBuilder().withEmail("Skinny.Norris@canda.com").build();
		Member four = Member.newInstance(skinnyNorris);

		Relay sut = Relay.newInstance();
		sut.addMember(one, Position.FIRST);
		sut.addMember(two, Position.SECOND);
		sut.addMember(three, Position.THIRD);
		sut.addMember(four, Position.FOURTH);

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

		assertEquals("[duration] not corret!", expected, actual);
	}

	@Test
	public void testGetDurationFormatted_ForNull() {
		Relay sut = Relay.newInstance();
		sut.setDuration(null);

		String actual = sut.getDurationFormatted();
		String expected = "00:00:00";

		assertEquals("[duration] not corret!", expected, actual);

	}

	@Test
	public void testGetDurationFormatted_ForValue() {
		Relay sut = Relay.newInstance();
		Duration duration = Duration.ofHours(3).plusMinutes(33).plusSeconds(13);
		sut.setDuration(duration);

		String actual = sut.getDurationFormatted();
		String expected = "03:33:13";

		assertEquals("[duration] not corret!", expected, actual);

	}

	@Test
	public void testIsEmpty_ForRelayWithoutMembers() {
		Relay sut = Relay.newInstance();

		boolean condition = sut.isEmpty();

		assertTrue("[isEmpty] not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForRelayWithMembers() {
		Relay sut = Relay.newInstance();
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FIRST);

		boolean condition = sut.isEmpty();

		assertFalse("[isEmpty] not correct!", condition);
	}

	@Test
	public void testIsFilled_ForRelayWithoutMembers() {
		Relay sut = Relay.newInstance();

		boolean condition = sut.isFilled();

		assertFalse("[isFilled] not correct!", condition);
	}

	@Test
	public void testIsFilled_ForRelayWith2Members() {
		Relay sut = Relay.newInstance();
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.SECOND);

		boolean condition = sut.isFilled();

		assertFalse("[isFilled] not correct!", condition);
	}

	@Test
	public void testIsFilled_ForRelayWith4Members() {
		Relay sut = Relay.newInstance();
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.SECOND);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.THIRD);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FOURTH);

		boolean condition = sut.isFilled();

		assertTrue("[isFilled] not correct!", condition);
	}

	@Test
	public void testToString_ForRelayWithoutMember() {
		Relay sut = Relay.newInstance();

		String relayname = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(relayname));

		assertEquals(relayname + " [0/4]", sut.toString());
	}

	@Test
	public void testToString_ForRelayWithTwoMember() {
		Relay sut = Relay.newInstance();

		String relayname = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(relayname));
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new ParticipantBuilder().build()), Position.SECOND);

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

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithNull() {
		Relay sut = Relay.newInstance();

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Relay sut = Relay.newInstance();

		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Relay sut = Relay.newInstance();
		sut.uuid = null;
		Relay secondSut = Relay.newInstance();

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Relay sut = Relay.newInstance();
		sut.uuid = null;
		Relay secondSut = Relay.newInstance();
		secondSut.uuid = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Relay sut = Relay.newInstance();
		Relay secondSut = Relay.newInstance();

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithSameValues() {
		Relay sut = Relay.newInstance();
		Relay secondSut = Relay.newInstance();
		sut.uuid = secondSut.uuid;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testIsMember_forExistingMember() {
		Person justusPerson = Person.newInstance();
		Person peterPerson = Person.newInstance();
		Person bobPerson = Person.newInstance();

		Participant justusParticipant = Participant.newInstance(justusPerson);
		Participant peterParticipant = Participant.newInstance(peterPerson);
		Participant bobParticipant = Participant.newInstance(bobPerson);

		Relay sut = Relay.newInstance();

		Member member = Member.newInstance(justusParticipant);
		sut.addMember(member, Position.FIRST);
		member = Member.newInstance(peterParticipant);
		sut.addMember(member, Position.SECOND);
		member = Member.newInstance(bobParticipant);
		sut.addMember(member, Position.THIRD);

		boolean condition = sut.isMember(peterParticipant);

		assertTrue("Participant isn't member of relay!", condition);

	}

	@Test
	public void testIsMember_forNonExistingMember() {
		Person justusPerson = Person.newInstance();
		Person peterPerson = Person.newInstance();
		Person bobPerson = Person.newInstance();
		Person skinnyPerson = Person.newInstance();

		Participant justusParticipant = Participant.newInstance(justusPerson);
		Participant peterParticipant = Participant.newInstance(peterPerson);
		Participant bobParticipant = Participant.newInstance(bobPerson);
		Participant skinnyParticipant = Participant.newInstance(skinnyPerson);

		Relay sut = Relay.newInstance();

		Member member = Member.newInstance(justusParticipant);
		sut.addMember(member, Position.FIRST);
		member = Member.newInstance(peterParticipant);
		sut.addMember(member, Position.SECOND);
		member = Member.newInstance(bobParticipant);
		sut.addMember(member, Position.THIRD);

		boolean condition = sut.isMember(skinnyParticipant);

		assertFalse("Participant is member of relay!", condition);

	}
}