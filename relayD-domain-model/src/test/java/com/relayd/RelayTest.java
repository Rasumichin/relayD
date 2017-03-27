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
		Member expected = Member.newInstance(person);

		sut.addMember(expected, Position.FIRST);

		Member actual = sut.getMemberFor(Position.FIRST);

		assertEquals("Person on first position is wrong!", expected, actual);

		assertTrue("second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionSecond() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected, Position.SECOND);

		Member actual = sut.getMemberFor(Position.SECOND);

		assertTrue("first position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertEquals("Person on second position is wrong!", expected, actual);
		assertTrue("third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionThird() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected, Position.THIRD);

		Member actual = sut.getMemberFor(Position.THIRD);

		assertTrue("first position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertTrue("second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertEquals("Person on third position is wrong!", expected, actual);
		assertTrue("fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testAddPerson_ForPositionFourth() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected, Position.FOURTH);

		Member actual = sut.getMemberFor(Position.FOURTH);

		assertTrue("first position not empty!", sut.getMemberFor(Position.FIRST).isEmpty());
		assertTrue("second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertEquals("Person on fourth position is wrong!", expected, actual);
	}

	@Test
	public void testAddMember_WithoutPositionForEmptyRelay() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person person = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.FIRST);

		assertEquals("Person on first position is wrong!", expected, actual);
		assertTrue("second position not empty!", sut.getMemberFor(Position.SECOND).isEmpty());
		assertTrue("third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddMember_WithoutPositionForRelayWithEntryOnFirstPosition() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person justusJonas = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member firstMember = Member.newInstance(justusJonas);

		sut.addMember(firstMember, Position.FIRST);

		Person person = new PersonBuilder().withForename("Peter").withSurename("Shaw").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.SECOND);

		assertEquals("Perosn on first position is wrong!", firstMember, sut.getMemberFor(Position.FIRST));
		assertEquals("Person on second position is wrong!", expected, actual);
		assertTrue("third position not empty!", sut.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("fourth position not empty!", sut.getMemberFor(Position.FOURTH).isEmpty());

	}

	@Test
	public void testAddMember_WithoutPositionForRelayWithSpaceOnPositionThree() {
		Relay sut = Relay.newInstance(RelayEvent.duesseldorf());

		Person justusJonas = new PersonBuilder().withForename("Justus").withSurename("Jonas").build();
		Member firstMember = Member.newInstance(justusJonas);

		Person peterShaw = new PersonBuilder().withForename("Peter").withSurename("Shaw").build();
		Member secondMember = Member.newInstance(peterShaw);

		Person skinnyNoris = new PersonBuilder().withForename("Skinny").withSurename("Norris").build();
		Member fourthMember = Member.newInstance(skinnyNoris);

		sut.addMember(firstMember, Position.FIRST);
		sut.addMember(secondMember, Position.SECOND);
		sut.addMember(fourthMember, Position.FOURTH);

		Person person = new PersonBuilder().withForename("Bob").withSurename("Andrews").build();
		Member expected = Member.newInstance(person);

		sut.addMember(expected);

		Member actual = sut.getMemberFor(Position.THIRD);

		assertEquals("Person on first position is wrong!", firstMember, sut.getMemberFor(Position.FIRST));
		assertEquals("Person on second position is wrong!", secondMember, sut.getMemberFor(Position.SECOND));
		assertEquals("Person on third position is wrong!", expected, actual);
		assertEquals("Person on fourth position is wrong!", fourthMember, sut.getMemberFor(Position.FOURTH));
	}

	@Test
	public void testMemberCount_ForEmptyRelay() {
		Relay sut = Relay.newInstance();

		Integer actual = sut.memberCount();

		assertEquals("count not correct for empty members!", Integer.valueOf(0), actual);
	}

	@Test
	public void testMemberCount_ForTowMembers() {
		Relay sut = Relay.newInstance();
		sut.addMember(Member.newInstance(new PersonBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new PersonBuilder().build()), Position.SECOND);

		Integer actual = sut.memberCount();

		assertEquals("count not correct for empty members!", Integer.valueOf(2), actual);
	}

	@Test
	public void testGetMembers() {
		Relay sut = Relay.newInstance();
		Member firstMember = Member.newInstance(new PersonBuilder().build());
		Member secondMember = Member.newInstance(new PersonBuilder().build());
		sut.addMember(firstMember, Position.FIRST);
		sut.addMember(secondMember, Position.SECOND);

		List<Member> actual = sut.getMembers();

		assertNotNull("instance not correct!", actual);
		assertEquals("size of List not correct!", 4, actual.size());
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
	public void testGetEmails_ForEmptyMemberList() {
		Relay sut = Relay.newInstance();

		String actual = sut.getEmailList();

		assertTrue("[getEmailList] not correct!", actual.isEmpty());
	}

	@Test
	public void testGetEmails_ForOneMember() {
		String expected = "Justus.Jonas@canda.com";
		Person justusJonas = new PersonBuilder().withEmail(expected).build();
		Member one = Member.newInstance(justusJonas);

		Relay sut = Relay.newInstance();
		sut.addMember(one, Position.FIRST);

		String actual = sut.getEmailList();

		assertEquals("[getEmailList] not correct!", expected, actual);
	}

	@Test
	public void testGetEmails_ForFourMember() {
		Person justusJonas = new PersonBuilder().withEmail("Justus.Jonas@canda.com").build();
		Member one = Member.newInstance(justusJonas);
		Person peterShaw = new PersonBuilder().withEmail("Peter.Shaw@canda.com").build();
		Member two = Member.newInstance(peterShaw);
		Person bobAndrews = new PersonBuilder().withEmail("Bob.Andrews@canda.com").build();
		Member three = Member.newInstance(bobAndrews);
		Person skinnyNorris = new PersonBuilder().withEmail("Skinny.Norris@canda.com").build();
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
		sut.addMember(Member.newInstance(new PersonBuilder().build()), Position.FIRST);
		sut.addMember(Member.newInstance(new PersonBuilder().build()), Position.SECOND);

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
	public void testIsMember_forExistingMember() {
		Person justus = Person.newInstance();
		Person peter = Person.newInstance();
		Person bob = Person.newInstance();

		Relay sut = Relay.newInstance();

		Member member = Member.newInstance(justus);
		sut.addMember(member, Position.FIRST);
		member = Member.newInstance(peter);
		sut.addMember(member, Position.SECOND);
		member = Member.newInstance(bob);
		sut.addMember(member, Position.THIRD);

		boolean condition = sut.isMember(peter);

		assertTrue("Person isn't member of relay!", condition);

	}

	@Test
	public void testIsMember_forNonExistingMember() {
		Person justus = Person.newInstance();
		Person peter = Person.newInstance();
		Person bob = Person.newInstance();
		Person skinny = Person.newInstance();

		Relay sut = Relay.newInstance();

		Member member = Member.newInstance(justus);
		sut.addMember(member, Position.FIRST);
		member = Member.newInstance(peter);
		sut.addMember(member, Position.SECOND);
		member = Member.newInstance(bob);
		sut.addMember(member, Position.THIRD);

		boolean condition = sut.isMember(skinny);

		assertFalse("Person is member of relay!", condition);

	}
}