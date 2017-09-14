package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant.ParticipantNullObject;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * Wer sichere Schritte tun will, muß sie langsam tun.
 *  - Johann Wolfgang von Goethe
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.10.2016
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantTest {

	@Test
	public void testIsSerializable() {
		Participant sut = Participant.newInstance();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Participant sut = Participant.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == ParticipantNullObject.class;

		assertTrue("Instance is not correct!", result);

	}

	@Test
	public void testCreateInstance_ForParameterPerson() {
		Person dummyPerson = Person.newInstance();

		Participant sut = Participant.newInstance(dummyPerson);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == Participant.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Participant sut = Participant.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == ParticipantNullObject.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testUuid() {
		Participant sut = Participant.newInstance(Person.newInstance());
		UUID expected = UUID.randomUUID();
		sut.setUuid(expected);

		UUID actual = sut.getUuid();
		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetUuidPerson() {
		UUID expected = UUID.randomUUID();

		Person person = Person.newInstance();
		person.setUuid(expected);

		Participant sut = Participant.newInstance(person);

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		Forename expected = Forename.newInstance("Jonas");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(expected);

		Participant sut = Participant.newInstance(person);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		Surename expected = Surename.newInstance("Justus");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setSurename(expected);

		Participant sut = Participant.newInstance(person);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testGetEmail() {
		Email expected = Email.newInstance("Justus.Jonas@canda.com");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setEmail(expected);

		Participant sut = Participant.newInstance(person);

		Email actual = sut.getEmail();

		assertEquals("[email] not correct!", expected, actual);
	}

	@Test
	public void testComment_ForValue() {
		Person person = Person.newInstance();
		Participant sut = Participant.newInstance(person);
		Comment expected = Comment.newInstance("What a runner!");

		sut.setComment(expected);

		Comment actual = sut.getComment();
		assertEquals("[comment] not correct!", expected, actual);
	}

	@Test
	public void testComment_ForNull() {
		Person person = Person.newInstance();
		Participant sut = Participant.newInstance(person);
		Comment comment = null;

		sut.setComment(comment);

		Comment actual = sut.getComment();
		assertNotNull("[comment] should not be null!", actual);

		boolean condition = actual.isEmpty();
		assertTrue("[condition] should be emtpy!", condition);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Participant sut = Participant.newInstance();

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(UUID.randomUUID());
		dummyPerson.setForename(Forename.newInstance("Justus"));
		dummyPerson.setSurename(Surename.newInstance("Jonas"));
		dummyPerson.setEmail(Email.newInstance("Justus.Jonas@canda.com"));

		Participant sut = Participant.newInstance(dummyPerson);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testToString_ForEmptyForeAndSurename() {
		Participant sut = Participant.newInstance();

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "", actual);
	}

	@Test
	public void testToString_ForFilledForeNameSurenameAndEmail() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		person.setEmail(Email.newInstance("Justus.Jonas@canda.com"));

		Participant sut = Participant.newInstance(person);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "Justus Jonas", actual);
	}

	@Test
	public void testHashCode() {
		Person person = Person.newInstance();
		person.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		Participant sut = Participant.newInstance(person);
		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		Participant sutWithoutUUID = Participant.newInstance();

		hashCode = sutWithoutUUID.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(null);

		Participant sut = Participant.newInstance(dummyPerson);

		UUID uuidForSecondSut = UUID.randomUUID();
		dummyPerson.setUuid(uuidForSecondSut);
		Participant secondSut = Participant.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(null);

		Participant sut = Participant.newInstance(dummyPerson);
		Participant secondSut = Participant.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		UUID uuidForSut = UUID.randomUUID();
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(uuidForSut);

		Participant sut = Participant.newInstance(dummyPerson);

		UUID uuidForSecondSut = UUID.randomUUID();
		dummyPerson.setUuid(uuidForSecondSut);
		Participant secondSut = Participant.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(UUID.randomUUID());

		Participant sut = Participant.newInstance(dummyPerson);
		Participant secondSut = Participant.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}