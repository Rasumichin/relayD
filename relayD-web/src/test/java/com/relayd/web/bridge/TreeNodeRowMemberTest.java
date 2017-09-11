package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.attributes.Position;

/**
 * The only way to learn a new programming language is by writing tests in it.
 *  - Dennis Ritchie
 *
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRowMemberTest {

	@Test
	public void testIsSerializable() {
		Position dummyPosition = Position.UNKNOWN;
		Member dummyMember = Member.newInstance();

		TreeNodeRow sut = TreeNodeRow.newInstance(dummyMember, dummyPosition);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateInstance_ForMember() {
		UUID uuid = UUID.randomUUID();
		Person person = Person.newInstance();
		person.setUuid(uuid);
		Participant participant = Participant.newInstance(person);

		Member expected = Member.newInstance(participant);

		TreeNodeRow sut = TreeNodeRow.newInstance(expected, Position.FIRST);

		assertNotNull("Expected valid instance!", sut);

		Member actual = sut.getMember();

		assertEquals("Member not corret!", expected, actual);
		assertEquals("Position not correct!", Position.FIRST, sut.getPosition());
	}

	@Test
	public void testIsRelay() {
		Position dummyPosition = Position.UNKNOWN;
		Member personRelay = Member.newInstance();
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay, dummyPosition);

		boolean actual = sut.isRelay();

		assertFalse("row is not a member!", actual);
	}

	@Test
	public void testGetRelayname_ForMember() {
		Position dummyPosition = Position.UNKNOWN;
		Member dummyMember = Member.newInstance();
		TreeNodeRow sut = TreeNodeRow.newInstance(dummyMember, dummyPosition);

		String actual = sut.getRelayname();
		// TODO (Christian, Version 1.4): mit Erik drüber sprechen. Ohne toString interessantes Phänomen (wenn es ein Relayname Domain Objekt ist)
		assertEquals("relayName not correct!", "", actual.toString());
	}

	@Test
	public void testGetDuration_ForValue() {
		Member member = Member.newInstance();
		Duration duration = Duration.ofHours(3).plusMinutes(33).plusSeconds(12);
		member.setDuration(duration);

		TreeNodeRow sut = TreeNodeRow.newInstance(member, Position.FIRST);

		String actual = sut.getDuration();
		String expected = "03:33:12";

		assertEquals("[duration] not correct!", expected, actual);
	}

	@Test
	public void testGetDuration_ForNull() {
		Member relay = Member.newInstance();
		relay.setDuration(null);

		TreeNodeRow sut = TreeNodeRow.newInstance(relay, Position.FIRST);

		String actual = sut.getDuration();
		String expected = "00:00:00";

		assertEquals("[duration] not correct!", expected, actual);
	}
}