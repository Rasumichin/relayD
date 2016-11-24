package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.math.BigDecimal;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Distance;

/**
 * Niemand, der seine Arbeit tatsächlich versteht würde sich einen Experten nennen.
 *  - Henry Ford
 *
 * @author schmollc (Christian@relayd.de)
 * @since 05.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrackTest {

	@Test
	public void testIsSerializable() {
		Distance dummyDistance = Distance.newInstance(BigDecimal.ONE);
		Track sut = Track.newInstance(dummyDistance);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstanceForOneParameter() {
		Distance expectedDistance = Distance.newInstance(BigDecimal.TEN);

		Track sut = Track.newInstance(expectedDistance);

		assertNotNull("Expected valid instance!", sut);
	}

	@Test
	public void testCreateInstanceForTwoParameter() {
		Distance expectedDistance = Distance.newInstance(BigDecimal.TEN);
		Comment expectedComment = Comment.newInstance("DummyComment");

		Track sut = Track.newInstance(expectedDistance, expectedComment);

		assertNotNull("Expected valid instance!", sut);
	}

	@Test
	public void testToString_ForDistance() {
		Distance distance = Distance.kilometers(new BigDecimal("9.80"));
		Track sut = Track.newInstance(distance);

		String result = sut.toString();

		assertEquals("[result] not correct!", "9.8 km ", result);
	}

	@Test
	public void testToString_ForDistanceAndComment() {
		Distance distance = Distance.kilometers(new BigDecimal("10.40"));
		Comment comment = Comment.newInstance("Linksseitiges Rheinufer");
		Track sut = Track.newInstance(distance, comment);

		String result = sut.toString();

		assertEquals("[result] not correct!", "10.4 km Linksseitiges Rheinufer", result);
	}

	@Test
	public void testParticipant() {
		Distance dummyDistance = Distance.newInstance(BigDecimal.ONE);
		Track sut = Track.newInstance(dummyDistance);

		Participant expected = Participant.newInstance();

		sut.setParticipant(expected);

		Participant actual = sut.getParticipantRelay();

		assertEquals(expected, actual);
	}
}