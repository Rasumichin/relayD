package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant.ParticipantNullObject;
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
	public void testCreateInstance() {
		Participant sut = Participant.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == ParticipantNullObject.class;

		assertTrue("Instance is not correct!", result);

	}

	@Test
	public void testCreateInstance_ForParameter() {
		Forename dummyForename = Forename.newInstance("Justus");
		Surename dummySurename = Surename.newInstance("Jonas");
		UUID dummyUuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(dummyForename, dummySurename, dummyUuid);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == Participant.class;

		assertTrue("Instance is not correct!", result);

	}

	@Test
	public void testGetUuidParticipant() {
		Forename dummyForename = Forename.newInstance("Dummy");
		Surename dummySurenmae = Surename.newInstance("Dummy");
		UUID expected = UUID.randomUUID();

		Participant sut = Participant.newInstance(dummyForename, dummySurenmae, expected);

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		Forename expected = Forename.newInstance("Jonas");
		Surename dummySurenmae = Surename.newInstance("Dummy");
		UUID dummyUuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(expected, dummySurenmae, dummyUuid);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		Forename dummyForename = Forename.newInstance("Dummy");
		Surename expected = Surename.newInstance("Justus");
		UUID dummyUuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(dummyForename, expected, dummyUuid);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForEmptyForeAndSurename() {

		Participant sut = Participant.newInstance();

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "", actual);
	}

	@Test
	public void testToString_ForFilledForeAndSurename() {
		Forename forename = Forename.newInstance("Justus");
		Surename surename = Surename.newInstance("Jonas");
		UUID uuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(forename, surename, uuid);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "Justus Jonas", actual);
	}

	@Test
	public void testHashCode() {
		Forename dummyForename = Forename.newInstance("Dummy");
		Surename dummySurenmae = Surename.newInstance("Dummy");
		UUID uuid = UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138");

		Participant sut = Participant.newInstance(dummyForename, dummySurenmae, uuid);
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
		Forename dummyForename = Forename.newInstance("Justus");
		Surename dummySurename = Surename.newInstance("Jonas");
		Participant sut = Participant.newInstance(dummyForename, dummySurename, null);

		UUID uuidForSecondSut = UUID.randomUUID();
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename, uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Forename dummyForename = Forename.newInstance("Justus");
		Surename dummySurename = Surename.newInstance("Jonas");
		Participant sut = Participant.newInstance(dummyForename, dummySurename, null);
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename, null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Forename dummyForename = Forename.newInstance("Justus");
		Surename dummySurename = Surename.newInstance("Jonas");
		UUID uuidForSut = UUID.randomUUID();
		Participant sut = Participant.newInstance(dummyForename, dummySurename, uuidForSut);

		UUID uuidForSecondSut = UUID.randomUUID();
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename, uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Forename dummyForename = Forename.newInstance("Justus");
		Surename dummySurename = Surename.newInstance("Jonas");
		UUID uuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(dummyForename, dummySurename, uuid);
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename, uuid);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}