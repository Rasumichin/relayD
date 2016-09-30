package com.relayd;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.UUID;

import org.junit.Test;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * Discipline is the best tool.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 *
 */
public class PersonTest {

	@Test
	public void testCreateCompletePerson() {
		Person sut = Person.newInstance();
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setForename(Forename.newInstance("Justus"));
		sut.setYearOfBirth(YearOfBirth.newInstance(1956));
		sut.setShirtsize(Shirtsize.HerrenM);
		sut.setNationality(Locale.GERMANY);
		sut.setEmail(Email.newInstance("Jonas.Justus@rockyBeach.com"));
		sut.setRelayname(Relayname.newInstance("Die 4 ????"));
		sut.setPosition(Position.FIRST);

		String personAsString = sut.toString();

		assertEquals("Justus Jonas, 1956, Herren M, Germany, Jonas.Justus@rockyBeach.com, Die 4 ????, First", personAsString);
	}

	@Test
	public void testSurename() {
		Surename expected = Surename.newInstance("Justus");

		Person sut = Person.newInstance();

		sut.setSurename(expected);

		Surename result = sut.getSurename();

		assertEquals("[surename] not correct!", expected, result);
	}

	@Test
	public void testForename() {
		Forename expected = Forename.newInstance("Jonas");

		Person sut = Person.newInstance();

		sut.setForename(expected);

		Forename result = sut.getForename();

		assertEquals("[forename] not correct!", expected, result);
	}

	@Test
	public void testShirtsize() {
		Shirtsize expected = Shirtsize.HerrenXXL;

		Person sut = Person.newInstance();

		sut.setShirtsize(expected);

		Shirtsize result = sut.getShirtsize();

		assertEquals("[shirtsize] not correct!", expected, result);
	}

	@Test
	public void testNationality() {
		Locale expected = Locale.GERMANY;

		Person sut = Person.newInstance();

		sut.setNationality(expected);

		Locale result = sut.getNationality();

		assertEquals("[nationality] not correct!", expected, result);
	}

	@Test
	public void testYearOfBirth() {
		YearOfBirth expected = YearOfBirth.newInstance(1972);

		Person sut = Person.newInstance();

		sut.setYearOfBirth(expected);

		YearOfBirth result = sut.getYearOfBirth();

		assertEquals("[yearOfBirth] not correct!", expected, result);
	}

	@Test
	public void testEmail() {
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		Person sut = Person.newInstance();

		sut.setEmail(expected);

		Email result = sut.getEmail();

		assertEquals("[email] not correct!", expected, result);
	}

	@Test
	public void testRelayname() {
		Relayname expected = Relayname.newInstance("Die 4 ????");

		Person sut = Person.newInstance();

		sut.setRelayname(expected);

		Relayname result = sut.getRelayname();

		assertEquals("[relayname] not correct!", expected, result);
	}

	@Test
	public void testPosition() {
		Position expected = Position.FOURTH;

		Person sut = Person.newInstance();

		sut.setPosition(expected);

		Position result = sut.getPosition();

		assertEquals("[position] not correct!", expected, result);
	}

	@Test
	public void testComment() {
		Comment expected = Comment.newInstance("Spitzenläufer");

		Person sut = Person.newInstance();

		sut.setComment(expected);

		Comment result = sut.getComment();

		assertEquals("[comment] not correct!", expected, result);
	}

	@Test
	public void testUUID() {
		UUID expected = UUID.randomUUID();

		Person sut = Person.newInstance();

		sut.setUUID(expected);

		UUID result = sut.getUUID();

		assertEquals("[uuid] not correct!", expected, result);
	}

	@Test
	public void testInferEmailFromNameAndValidDomainPart() {
		Person sut = getDefaultPersonForEmailInference();

		String domainPart = "xerox-parc.com";
		Email expected = Email.newInstance(sut.getForename().toString() + "." + sut.getSurename() + "@" + domainPart);

		Email result = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, result);
	}

	private Person getDefaultPersonForEmailInference() {
		Person person = Person.newInstance();
		Forename forename = Forename.newInstance("adele");
		person.setForename(forename);
		Surename surename = Surename.newInstance("goldberg");
		person.setSurename(surename);

		return person;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInferEmailFromNameAndInvalidNullDomainPart() {
		Person sut = getDefaultPersonForEmailInference();

		String domainPart = null;
		sut.inferEmailFromNameAnd(domainPart);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInferEmailFromNameAndInvalidEmptyDomainPart() {
		Person sut = getDefaultPersonForEmailInference();

		String domainPart = "";
		sut.inferEmailFromNameAnd(domainPart);
	}

	@Test
	public void testInferEmailFromForenameOnly() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setSurename(null);

		String domainPart = "xerox-parc.com";
		Email expected = Email.newInstance(sut.getForename() + "@" + domainPart);

		Email result = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, result);
	}

	@Test
	public void testInferEmailFromSurenameOnly() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setForename(null);

		String domainPart = "xerox-parc.com";
		Email expected = Email.newInstance(sut.getSurename() + "@" + domainPart);

		Email result = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, result);
	}

	@Test
	public void testInferEmailWithoutAnyName() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setForename(null);
		sut.setSurename(null);

		String domainPart = "xerox-parc.com";
		Email result = sut.inferEmailFromNameAnd(domainPart);
		assertNull("Attribute [email] is unexpectedly not 'null'.", result);
	}

	@Test
	public void testHasEmail_ForPersonWithEmail() {
		Person sut = Person.newInstance();
		sut.setEmail(Email.newInstance("Justus.Jonas@RockyBeach.com"));
		assertTrue("Person should have an Email!", sut.hasEmail());
	}

	@Test
	public void testHasEmail_ForPersonWithNoEmail() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setEmail(null);

		assertFalse("Person should have no Email!", sut.hasEmail());
	}
}