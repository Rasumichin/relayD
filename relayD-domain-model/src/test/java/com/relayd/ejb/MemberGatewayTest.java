package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
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

	public abstract MemberGateway getSut();

	@Test
	public void testGet_ForExistingEntry() {
		Member firstMember = createMemberOne();
		getSut().set(firstMember);
		getSut().set(createMemberTwo());

		Member result = getSut().get(firstMember.getUuid());

		assertEquals("[duration] not correct.", firstMember.getDuration(), result.getDuration());
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createMemberOne());

		Member result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	private Member createMemberOne() {
		Person justus = Person.newInstance();
		justus.setForename(Forename.newInstance("Justus"));
		Member member = Member.newInstance(justus);
		member.setDuration(Duration.ofMinutes(15));
		return member;
	}

	private Member createMemberTwo() {
		Person peter = Person.newInstance();
		peter.setForename(Forename.newInstance("Peter"));
		Member member = Member.newInstance(peter);
		member.setDuration(Duration.ofMinutes(17));
		return member;
	}
}