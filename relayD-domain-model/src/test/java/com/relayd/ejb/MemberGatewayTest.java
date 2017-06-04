package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

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
		Member firstMember = createJustusJonas();
		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Member result = getSut().get(firstMember.getUuidPerson());

		assertEquals("[Forename] not correct.", firstMember.getForename(), result.getForename());
		assertEquals("[Surename] not correct.", firstMember.getSurename(), result.getSurename());
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createJustusJonas());
		//		getSut().set(createHotRunners());

		Member result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	private Member createJustusJonas() {
		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		Member member = Member.newInstance(justusJonas);
		return member;
	}

	private Member createPeterShaw() {
		Person peterShaw = Person.newInstance();
		peterShaw.setForename(Forename.newInstance("Peter"));
		peterShaw.setSurename(Surename.newInstance("Shaw"));
		Member member = Member.newInstance(peterShaw);
		return member;
	}
}