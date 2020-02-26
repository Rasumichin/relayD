package com.relayd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member.MemberNullObject;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * Verlasse dich auf nichts. - Miyamoto Musashi
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
	public void testCreateInstance_ForParameterParticipant() {
		Participant dummyParticipant = Participant.newInstance();

		Member sut = Member.newInstance(dummyParticipant);

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
		Member sut = Member.newInstance(Participant.newInstance());
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
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		Forename expected = Forename.newInstance("Jonas");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(expected);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		Surename expected = Surename.newInstance("Justus");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setSurename(expected);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testGetShirtsize() {
		Shirtsize expected = Shirtsize.HerrenM;

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setShirtsize(expected);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		Shirtsize actual = sut.getShirtsize();

		assertEquals("[shirtsize] not correct!", expected, actual);
	}

	@Test
	public void testGetYearOfBirth() {
		YearOfBirth expected = YearOfBirth.newInstance(1971);

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setYearOfBirth(expected);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		YearOfBirth actual = sut.getYearOfBirth();

		assertEquals("[yearOfBirth] not correct!", expected, actual);
	}

	@Test
	public void testGetEmail() {
		Email expected = Email.newInstance("Justus.Jonas@canda.com");

		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setEmail(expected);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		Email actual = sut.getEmail();

		assertEquals("[email] not correct!", expected, actual);
	}

	@Test
	public void testHasMail_ForEmptyMailAdress() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setEmail(null);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		boolean condition = sut.hasMail();

		assertFalse("[hasMail] not correct!", condition);
	}

	@Test
	public void testHasMail_ForMailAdressAsNOP() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		Email email = Email.newInstance();
		person.setEmail(email);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		boolean condition = sut.hasMail();

		assertFalse("[hasMail] not correct!", condition);
	}

	@Test
	public void testHasMail_ForExistingMailAdress() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		Email email = Email.newInstance("Justus.Jonas@canda.com");
		person.setEmail(email);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		boolean condition = sut.hasMail();

		assertTrue("[hasMail] not correct!", condition);
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
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

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
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		person.setEmail(Email.newInstance("Justus.Jonas@canda.com"));
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testHasThatPersonIdentity_true() {
		UUID someUuid = UUID.randomUUID();
		Person person = Person.newInstance();
		person.setUuid(someUuid);
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		boolean condition = sut.hasThatPersonIdentity(someUuid);

		assertTrue("UUID of person has not been checked correctly!", condition);
	}

	@Test
	public void testHasThatPersonIdentity_false() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

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
		Participant participant = Participant.newInstance(person);
		Member sut = Member.newInstance(participant);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "Justus Jonas", actual);
	}

	@Test
	public void testHashCode() {
		Member sut = Member.newInstance(Participant.newInstance());
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
		Member sut = Member.newInstance(Participant.newInstance());
		sut.setUuid(null);

		Member secondSut = Member.newInstance(Participant.newInstance());
		secondSut.setUuid(UUID.randomUUID());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForBothIdsAreNull() {
		Member sut = Member.newInstance(Participant.newInstance());
		sut.setUuid(null);
		Member secondSut = Member.newInstance(Participant.newInstance());
		secondSut.setUuid(null);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_ForTwoDiffrentIds() {
		Member sut = Member.newInstance(Participant.newInstance());
		sut.setUuid(UUID.randomUUID());
		Member secondSut = Member.newInstance(Participant.newInstance());
		secondSut.setUuid(UUID.randomUUID());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForSameIds() {
		UUID uuid = UUID.randomUUID();

		Member sut = Member.newInstance(Participant.newInstance());
		sut.setUuid(uuid);
		Member secondSut = Member.newInstance(Participant.newInstance());
		secondSut.setUuid(uuid);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}