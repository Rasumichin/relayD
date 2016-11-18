package com.relayd;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest {

	@Test
	public void testToString() {
		Person sut = Person.newInstance();
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setForename(Forename.newInstance("Justus"));
		sut.setYearOfBirth(YearOfBirth.newInstance(1956));
		sut.setShirtsize(Shirtsize.HerrenM);
		sut.setEmail(Email.newInstance("Jonas.Justus@rockyBeach.com"));
		sut.setRelayname(Relayname.newInstance("Die 4 ????"));
		sut.setPosition(Position.FIRST);

		String personAsString = sut.toString();

		assertEquals("Justus Jonas, 1956, Herren M, Jonas.Justus@rockyBeach.com, Die 4 ????, First", personAsString);
	}

	@Test
	public void testSurename() {
		Person sut = Person.newInstance();
		Surename expected = Surename.newInstance("Justus");

		sut.setSurename(expected);

		Surename actual = sut.getSurename();
		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testForename() {
		Person sut = Person.newInstance();
		Forename expected = Forename.newInstance("Jonas");

		sut.setForename(expected);

		Forename actual = sut.getForename();
		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testSetForename_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setForename(null);

		Forename actual = sut.getForename();
		assertNotNull("Person must not return [forename] equals 'null'!", actual);
	}

	@Test
	public void testSetRelayname_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setRelayname(null);

		Relayname actual = sut.getRelayname();
		assertNotNull("Person must not return [relayname] equals 'null'!", actual);
	}

	@Test
	public void testSetComment_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setComment(null);

		Comment actual = sut.getComment();
		assertNotNull("Person must not return [comment] equals 'null'!", actual);
	}

	@Test
	public void testSetEmail_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setEmail(null);

		Email actual = sut.getEmail();
		assertNotNull("Person must not return [email] equals 'null'!", actual);
	}

	@Test
	public void testShirtsize() {
		Person sut = Person.newInstance();
		Shirtsize defaultShirtsize = sut.getShirtsize();

		assertEquals("[position] for default is not correct!", Shirtsize.UNKNOWN, defaultShirtsize);

		Shirtsize expected = Shirtsize.HerrenXXL;

		sut.setShirtsize(expected);

		Shirtsize actual = sut.getShirtsize();
		assertEquals("[shirtsize] not correct!", expected, actual);
	}

	@Test
	public void testGetInitialYearOfBirth() {
		Person sut = Person.newInstance();

		YearOfBirth actual = sut.getYearOfBirth();

		assertNotNull("[yearOfBirth] not correct!", actual);
	}

	@Test
	public void testSetYearOfBirth() {
		Person sut = Person.newInstance();
		YearOfBirth expected = YearOfBirth.newInstance(1972);

		sut.setYearOfBirth(expected);

		YearOfBirth actual = sut.getYearOfBirth();
		assertEquals("[yearOfBirth] not correct!", expected, actual);
	}

	@Test
	public void testSetYearOfBirth_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setYearOfBirth(null);

		YearOfBirth actual = sut.getYearOfBirth();
		assertNotNull("[yearOfBirth] not correct!", actual);
	}

	@Test
	public void testEmail() {
		Person sut = Person.newInstance();
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		sut.setEmail(expected);

		Email actual = sut.getEmail();
		assertEquals("[email] not correct!", expected, actual);
	}

	@Test
	public void testGetInitialEmail() {
		Person sut = Person.newInstance();

		Email result = sut.getEmail();

		assertNotNull("[email] not correct!", result);
	}

	@Test
	public void testRelayname() {
		Person sut = Person.newInstance();

		Relayname relayname = sut.getRelayname();

		assertNotNull("[relayname] not valid instance!", relayname);

		Relayname expected = Relayname.newInstance("Die 4 ????");

		sut.setRelayname(expected);

		Relayname actual = sut.getRelayname();

		assertEquals("[relayname] not correct!", expected, actual);
	}

	@Test
	public void testPosition() {
		Person sut = Person.newInstance();
		Position defaultPosition = sut.getPosition();

		assertEquals("[position] for default is not correct!", Position.UNKNOWN, defaultPosition);

		Position expected = Position.FOURTH;

		sut.setPosition(expected);

		Position actual = sut.getPosition();
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testComment() {
		Person sut = Person.newInstance();
		Comment expected = Comment.newInstance("What a runner!");

		sut.setComment(expected);

		Comment actual = sut.getComment();
		assertEquals("[comment] not correct!", expected, actual);
	}

	@Test
	public void testUUID() {
		Person sut = Person.newInstance();
		UUID expected = UUID.randomUUID();

		sut.setUuid(expected);

		UUID actual = sut.getUuid();
		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testHasRelay_ForRelaynameIsNull() {
		Person sut = Person.newInstance();

		boolean actual = sut.hasRelay();

		assertFalse("[actual] for hasRelay() is not correct!", actual);
	}

	@Test
	public void testHasRelay_ForRelaynameIsFilled() {
		Person sut = Person.newInstance();
		sut.setRelayname(Relayname.newInstance("Die 4 ????"));

		boolean actual = sut.hasRelay();

		assertTrue("[actual] for hasRelay() is not correct!", actual);
	}

	@Test
	public void testHasRelay_ForRelaynameIsFilledWithEmptyString() {
		Person sut = Person.newInstance();
		sut.setRelayname(Relayname.newInstance("  "));

		boolean actual = sut.hasRelay();

		assertFalse("[actual] for hasRelay() is not correct!", actual);
	}

	@Test
	public void testInferEmailFromNameAndValidDomainPart() {
		Person sut = getDefaultPersonForEmailInference();

		String domainPart = "xerox-parc.com";
		Email expected = Email.newInstance(sut.getForename().toString() + "." + sut.getSurename() + "@" + domainPart);

		Email actual = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, actual);
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

		Email actual = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, actual);
	}

	@Test
	public void testInferEmailFromSurenameOnly() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setForename(null);

		String domainPart = "xerox-parc.com";
		Email expected = Email.newInstance(sut.getSurename() + "@" + domainPart);

		Email actual = sut.inferEmailFromNameAnd(domainPart);
		assertEquals("Email has not been composed correctly.", expected, actual);
	}

	@Test
	public void testInferEmailWithoutAnyName() {
		Person sut = getDefaultPersonForEmailInference();
		sut.setForename(null);
		sut.setSurename(null);

		String domainPart = "xerox-parc.com";
		Email actual = sut.inferEmailFromNameAnd(domainPart);

		boolean result = actual.isEmpty();
		assertTrue("Attribute [email] is not empty!", result);
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

		boolean result = sut.hasEmail();

		assertFalse("Person should have no Email!", result);
	}

	@Test
	public void testGetCurrentLocalPartForenameIsSet() {
		Person sut = Person.newInstance();
		Forename forename = Forename.newInstance("Mike");
		sut.setForename(forename);

		String expected = forename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}

	@Test
	public void testGetCurrentLocalPartSurenameIsSet() {
		Person sut = Person.newInstance();
		Surename surename = Surename.newInstance("Hansen");
		sut.setSurename(surename);

		String expected = surename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}

	@Test
	public void testGetCurrentLocalPartForenameAndSurenameHaveBeenSet() {
		Person sut = Person.newInstance();
		Forename forename = Forename.newInstance("Mike");
		sut.setForename(forename);
		Surename surename = Surename.newInstance("Hansen");
		sut.setSurename(surename);

		String expected = forename.toString() + "." + surename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}

	@Test
	public void testGetCurrentLocalPartWhenNoNameHasBeenSet() {
		Person sut = Person.newInstance();

		String result = sut.getCurrentLocalPart();
		assertTrue("Current local part is not correct.", result.isEmpty());
	}

	@Test
	public void tetGetCurrentLocalPartWhenSurenameContainsWhitespace() {
		Person sut = Person.newInstance();

		Surename surename = Surename.newInstance("van Helsing");
		sut.setSurename(surename);

		String expected = "vanHelsing";
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}

	@Test
	public void testPrepareNewPerson() {
		Person sut = Person.newInstance();

		Email expected = sut.getDefaultEmail();

		Email result = sut.lastCalculatedEmail;
		assertEquals("[lastCalculatedEmail] is not correct.", expected, result);
	}

	@Test
	public void testGetDefaultEmail() {
		Person sut = Person.newInstance();

		Email expected = getExpectedDefaultEmail();
		Email result = sut.getDefaultEmail();
		assertEquals("Default email address is not correct.", expected, result);
	}

	private Email getExpectedDefaultEmail() {
		return Email.createFromLocalAndDomainPart("forename.surename", "canda.com");
	}

	@Test
	public void testCreateNewPersonAndVerifyEmailHasADefaultValue() {
		Person sut = Person.newInstance();
		Email expected = getExpectedDefaultEmail();

		Email result = sut.getEmail();
		assertEquals("Person's [email] attribute is not correctly initialized.", expected, result);
	}

	@Test
	public void testForenameValueChanged() {
		Person sut = Person.newInstance();
		Forename forename = Forename.newInstance("Clark");
		Email expected = Email.createFromLocalAndDomainPart(forename.toString(), "canda.com");

		sut.setForename(forename);
		sut.nameValueChanged();

		Email result = sut.getEmail();
		assertEquals("Forename value change has not been handled correctly.", expected, result);
	}

	@Test
	public void testSurenameValueChanged() {
		Person sut = Person.newInstance();
		Surename surename = Surename.newInstance("Kent");
		Email expected = Email.createFromLocalAndDomainPart(surename.toString(), "canda.com");

		sut.setSurename(surename);
		sut.nameValueChanged();

		Email result = sut.getEmail();
		assertEquals("Surename value change has not been handled correctly.", expected, result);
	}

	@Test
	public void testNameValueChangedButEmailWasEdited() {
		Person sut = Person.newInstance();
		String expected = "edited.name" + Email.AT_SIGN + "notcanda.com";
		Email email = Email.newInstance(expected);
		sut.setEmail(email);

		sut.setForename(Forename.newInstance("Adele"));
		sut.nameValueChanged();

		String result = sut.getEmail().toString();
		assertEquals("Email was changed although otherwise edited.", expected, result);
	}

	@Test
	public void testCurrentEmailHasBeenCalculated_true() {
		Person sut = Person.newInstance();

		boolean result = sut.currentEmailHasBeenCalculated();

		assertTrue("Recalculation should be possible!", result);
	}

	@Test
	public void testCurrentEmailHasBeenCalculated_false() {
		Person sut = Person.newInstance();
		Email email = Email.createFromLocalAndDomainPart("john", "mail.com");
		sut.setEmail(email);

		boolean result = sut.currentEmailHasBeenCalculated();

		assertFalse("Recalculation should not be possible!", result);
	}

	@Test
	public void testRecalculateEmail() {
		Person sut = Person.newInstance();
		sut.setEmail(sut.getDefaultEmail());
		Email expected = Email.createFromLocalAndDomainPart("john", "canda.com");
		sut.setForename(Forename.newInstance("john"));

		sut.recalculateEmail();

		Email result = sut.lastCalculatedEmail;
		assertEquals("Recalculation of [lastCalculatedEmail] is not correct!", expected, result);
	}
}