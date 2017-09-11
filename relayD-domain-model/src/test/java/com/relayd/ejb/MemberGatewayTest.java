package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.attributes.Forename;

/**
 * Die kürzeste Antwort auf etwas ist es einfach zu tun.
 *  - Ernest Hemingway
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class MemberGatewayTest {

	/**
	 *
	 */
	private static final Duration OF_MINUTES = Duration.ofMinutes(15);

	public abstract MemberGateway getSut();

	public UUID firstMemberUUID = null;

	@Before
	public void setUp() {
		Member firstMember = createMemberOne();
		firstMemberUUID = firstMember.getUuid();
		getSut().set(firstMember);
		getSut().set(createMemberTwo());
	}

	@Test
	public void testGet_ForExistingEntry() {

		Member result = getSut().get(firstMemberUUID);

		assertEquals("[duration] not correct.", OF_MINUTES, result.getDuration());
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createMemberOne());

		Member result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	protected Member createMemberOne() {
		Person justus = Person.newInstance();
		justus.setForename(Forename.newInstance("Justus"));
		Participant participant = Participant.newInstance(justus);
		Member member = Member.newInstance(participant);
		member.setDuration(OF_MINUTES);
		return member;
	}

	protected Member createMemberTwo() {
		Person peter = Person.newInstance();
		peter.setForename(Forename.newInstance("Peter"));
		Participant participant = Participant.newInstance(peter);
		Member member = Member.newInstance(participant);
		member.setDuration(Duration.ofMinutes(17));
		return member;
	}
}