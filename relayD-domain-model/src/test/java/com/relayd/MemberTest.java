package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member.MemberNullObject;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * Verlasse dich auf nichts.
 *  - Miyamoto Musashi
 *
 * @author schmollc (Christian@relayd.de)
 * @since 02.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberTest {

	@Test
	public void testIsSerializable() {
		Member sut = Member.newInstance();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Member sut = Member.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == MemberNullObject.class;

		assertTrue("Instance is not correct!", result);

	}

	@Test
	public void testCreateInstance_ForParameterPerson() {
		Person dummyPerson = Person.newInstance();

		Member sut = Member.newInstance(dummyPerson);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == Member.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Member sut = Member.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == MemberNullObject.class;

		assertTrue("Instance is not correct!", result);

		UUID dummyUuid = null;

		boolean condition = sut.hasThatPersonIdentity(dummyUuid);

		assertFalse("[condition] should always be false!", condition);
	}

	@Test
	public void testGetUuidMember() {
		UUID expected = UUID.randomUUID();

		Person person = Person.newInstance();
		person.setUuid(expected);

		Member sut = Member.newInstance(person);

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		Forename expected = Forename.newInstance("Jonas");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(expected);

		Member sut = Member.newInstance(person);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		Surename expected = Surename.newInstance("Justus");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setSurename(expected);

		Member sut = Member.newInstance(person);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testGetEmail() {
		Email expected = Email.newInstance("Justus.Jonas@canda.com");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setEmail(expected);

		Member sut = Member.newInstance(person);

		Email actual = sut.getEmail();

		assertEquals("[email] not correct!", expected, actual);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Member sut = Member.newInstance();

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(UUID.randomUUID());
		dummyPerson.setForename(Forename.newInstance("Justus"));
		dummyPerson.setSurename(Surename.newInstance("Jonas"));
		dummyPerson.setEmail(Email.newInstance("Justus.Jonas@canda.com"));

		Member sut = Member.newInstance(dummyPerson);

		boolean condition = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", condition);
	}

	@Test
	public void testHasThatPersonIdentity_true() {
		UUID someUuid = UUID.randomUUID();
		Person person = Person.newInstance();
		person.setUuid(someUuid);
		Member sut = Member.newInstance(person);

		boolean result = sut.hasThatPersonIdentity(someUuid);

		assertTrue("UUID of person has not been checked correctly!", result);
	}

	@Test
	public void testHasThatPersonIdentity_false() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		Member sut = Member.newInstance(person);

		boolean result = sut.hasThatPersonIdentity(UUID.randomUUID());

		assertFalse("UUID of person has not been checked correctly!", result);
	}

	@Test
	public void testToString_ForEmptyForeAndSurename() {
		Member sut = Member.newInstance();

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

		Member sut = Member.newInstance(person);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "Justus Jonas", actual);
	}

	@Test
	public void testHashCode() {
		Person person = Person.newInstance();
		person.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		Member sut = Member.newInstance(person);
		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		Member sutWithoutUUID = Member.newInstance();

		hashCode = sutWithoutUUID.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_ForMyself() {
		Member sut = Member.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_ForNull() {
		Member sut = Member.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_ForNotCompatibleClass() {
		Member sut = Member.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_ForIdIsNull() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(null);

		Member sut = Member.newInstance(dummyPerson);

		UUID uuidForSecondSut = UUID.randomUUID();
		dummyPerson.setUuid(uuidForSecondSut);
		Member secondSut = Member.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_ForBothIdsAreNull() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(null);

		Member sut = Member.newInstance(dummyPerson);
		Member secondSut = Member.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_ForTwoDiffrentIds() {
		UUID uuidForSut = UUID.randomUUID();
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(uuidForSut);

		Member sut = Member.newInstance(dummyPerson);

		UUID uuidForSecondSut = UUID.randomUUID();
		dummyPerson.setUuid(uuidForSecondSut);
		Member secondSut = Member.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_ForSameIds() {
		Person dummyPerson = Person.newInstance();
		dummyPerson.setUuid(UUID.randomUUID());

		Member sut = Member.newInstance(dummyPerson);
		Member secondSut = Member.newInstance(dummyPerson);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}