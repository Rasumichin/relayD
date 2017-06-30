package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;
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
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateInstance() {
		Member sut = Member.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == MemberNullObject.class;

		assertTrue("Instance is not correct!", condition);

	}

	@Test
	public void testCreateInstance_ForParameterPerson() {
		Person dummyPerson = Person.newInstance();

		Member sut = Member.newInstance(dummyPerson);

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == Member.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Member sut = Member.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == MemberNullObject.class;

		assertTrue("Instance is not correct!", condition);

		UUID dummyUuid = null;

		condition = sut.hasThatPersonIdentity(dummyUuid);

		assertFalse("[condition] should always be false!", condition);
	}

	@Test
	public void testUuid() {
		Member sut = Member.newInstance(Person.newInstance());
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
	public void testDuration() {
		Member sut = Member.newInstance();

		Duration expected = Duration.ofHours(3).plusMinutes(33).plusSeconds(13);

		sut.setDuration(expected);

		Duration actual = sut.getDuration();

		assertEquals("[duration] not corret!", expected, actual);
	}

	@Test
	public void testGetDuration_ForDefault() {
		Person person = Person.newInstance();
		Member sut = Member.newInstance(person);

		Duration actual = sut.getDuration();

		assertEquals("[duration] not corret!", Duration.ZERO, actual);
	}

	@Test
	public void testGetDurationFormatted_ForNull() {
		Member sut = Member.newInstance();
		sut.setDuration(null);

		String actual = sut.getDurationFormatted();
		String expected = "00:00:00";

		assertEquals("[duration] not corret!", expected, actual);

	}

	@Test
	public void testGetDurationFormatted_ForValue() {
		Member sut = Member.newInstance();
		Duration duration = Duration.ofHours(3).plusMinutes(33).plusSeconds(13);
		sut.setDuration(duration);

		String actual = sut.getDurationFormatted();
		String expected = "03:33:13";

		assertEquals("[duration] not corret!", expected, actual);
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

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testHasThatPersonIdentity_true() {
		UUID someUuid = UUID.randomUUID();
		Person person = Person.newInstance();
		person.setUuid(someUuid);
		Member sut = Member.newInstance(person);

		boolean condition = sut.hasThatPersonIdentity(someUuid);

		assertTrue("UUID of person has not been checked correctly!", condition);
	}

	@Test
	public void testHasThatPersonIdentity_false() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		Member sut = Member.newInstance(person);

		boolean condition = sut.hasThatPersonIdentity(UUID.randomUUID());

		assertFalse("UUID of person has not been checked correctly!", condition);
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
		Member sut = Member.newInstance(Person.newInstance());
		sut.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_ForMyself() {
		Member sut = Member.newInstance();

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_ForNull() {
		Member sut = Member.newInstance();

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForNotCompatibleClass() {
		Member sut = Member.newInstance();

		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForIdIsNull() {
		Member sut = Member.newInstance(Person.newInstance());
		sut.setUuid(null);

		Member secondSut = Member.newInstance(Person.newInstance());
		secondSut.setUuid(UUID.randomUUID());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForBothIdsAreNull() {
		Member sut = Member.newInstance(Person.newInstance());
		sut.setUuid(null);
		Member secondSut = Member.newInstance(Person.newInstance());
		secondSut.setUuid(null);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_ForTwoDiffrentIds() {
		Member sut = Member.newInstance(Person.newInstance());
		sut.setUuid(UUID.randomUUID());
		Member secondSut = Member.newInstance(Person.newInstance());
		secondSut.setUuid(UUID.randomUUID());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForSameIds() {
		UUID uuid = UUID.randomUUID();

		Member sut = Member.newInstance(Person.newInstance());
		sut.setUuid(uuid);
		Member secondSut = Member.newInstance(Person.newInstance());
		secondSut.setUuid(uuid);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}