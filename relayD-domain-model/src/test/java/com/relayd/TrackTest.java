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
		Distance dummyDistance = Distance.kilometers(BigDecimal.ONE);
		Track sut = Track.newInstance(dummyDistance);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateInstance_ForOneParameter() {
		Distance expectedDistance = Distance.kilometers(BigDecimal.TEN);

		Track sut = Track.newInstance(expectedDistance);

		assertNotNull("Expected valid instance!", sut);
	}

	@Test
	public void testCreateInstanceForTwoParameter() {
		Distance expectedDistance = Distance.kilometers(BigDecimal.TEN);
		Comment expectedComment = Comment.newInstance("DummyComment");

		Track sut = Track.newInstance(expectedDistance, expectedComment);

		assertNotNull("Expected valid instance!", sut);
	}

	@Test
	public void testToString_ForDistance() {
		Distance distance = Distance.kilometers(new BigDecimal("9.80"));
		Track sut = Track.newInstance(distance);

		String actual = sut.toString();

		assertEquals("[actual] not correct!", "9.8 km ", actual);
	}

	@Test
	public void testToString_ForDistanceAndComment() {
		Distance distance = Distance.kilometers(new BigDecimal("10.40"));
		Comment comment = Comment.newInstance("Linksseitiges Rheinufer");
		Track sut = Track.newInstance(distance, comment);

		String actual = sut.toString();

		assertEquals("[actual] not correct!", "10.4 km Linksseitiges Rheinufer", actual);
	}

	@Test
	public void testParticipant() {
		Distance dummyDistance = Distance.kilometers(BigDecimal.ONE);
		Track sut = Track.newInstance(dummyDistance);

		Participant expected = Participant.newInstance();

		sut.setParticipant(expected);

		Participant actual = sut.getParticipantRelay();

		assertEquals("[actual] not correct!", expected, actual);
	}

	@Test
	public void testGetDistance() {
		Distance expected = Distance.kilometers(BigDecimal.ONE);

		Track sut = Track.newInstance(expected);

		Distance actual = sut.getDistance();

		assertEquals("[distance] not corret!", expected, actual);
	}

	@Test
	public void testGetDistanceWithUnity() {
		Distance distance = Distance.kilometers(BigDecimal.ONE);

		Track sut = Track.newInstance(distance);

		String actual = sut.getDistanceWithUnity();

		String expected = "1 km";

		assertEquals("[distanceWithUnity] not corret!", expected, actual);
	}

	@Test
	public void testGetComment() {
		Distance dummyDistance = Distance.kilometers(BigDecimal.ONE);
		Comment expected = Comment.newInstance("Comment for Track");
		Track sut = Track.newInstance(dummyDistance, expected);

		Comment actual = sut.getComment();

		assertEquals("[comment] not corret!", expected, actual);
	}

}