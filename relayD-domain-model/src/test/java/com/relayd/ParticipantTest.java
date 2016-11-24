package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * Wer sichere Schritte tun will, muß sie langsam tun.
 *  - Johann Wolfgang von Goethe
 *
 * @author schmollc (Christian@relayd.de)
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
	public void testUuidParticipant() {
		UUID expected = UUID.randomUUID();

		Participant sut = Participant.newInstance();

		sut.setUuidPerson(expected);

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testForename() {
		Forename expected = Forename.newInstance("Jonas");

		Participant sut = Participant.newInstance();

		sut.setForename(expected);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testSurename() {
		Surename expected = Surename.newInstance("Justus");

		Participant sut = Participant.newInstance();

		sut.setSurename(expected);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		Forename forename = Forename.newInstance("Justus");
		Surename surename = Surename.newInstance("Jonas");

		Participant sut = Participant.newInstance();
		sut.setForename(forename);
		sut.setSurename(surename);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "Justus Jonas", actual);
	}

	@Test
	public void testGetHashCode() {
		Participant sut = Participant.newInstance();
		UUID uuid = UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138");
		sut.setUuidPerson(uuid);
		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		sut.setUuidPerson(null);

		hashCode = sut.hashCode();

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
		Participant sut = Participant.newInstance();
		sut.setUuidPerson(null);
		Participant secondSut = Participant.newInstance();
		UUID uuidForSecondSut = UUID.randomUUID();
		secondSut.setUuidPerson(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Participant sut = Participant.newInstance();
		sut.setUuidPerson(null);
		Participant secondSut = Participant.newInstance();
		secondSut.setUuidPerson(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Participant sut = Participant.newInstance();
		UUID uuidForSut = UUID.randomUUID();
		sut.setUuidPerson(uuidForSut);
		Participant secondSut = Participant.newInstance();
		UUID uuidForSecondSut = UUID.randomUUID();
		secondSut.setUuidPerson(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		UUID uuid = UUID.randomUUID();
		Participant sut = Participant.newInstance();
		sut.setUuidPerson(uuid);
		Participant secondSut = Participant.newInstance();
		secondSut.setUuidPerson(uuid);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}