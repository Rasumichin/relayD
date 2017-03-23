package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.attributes.Position;

/**
 * The time to write good code is at the time you are writing it.
 *  - Daniel Read
 *
 * @author schmollc (Christian@relayd.de)
 * @since 11.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRowTest {
	private TreeNodeRow sut = new TreeNodeRow() {
		private static final long serialVersionUID = 1L;
	};

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance_ForTreeNodeRowRelay() {
		Relay relay = Relay.newInstance();

		TreeNodeRow sutForTreeNodeRowRelay = TreeNodeRow.newInstance(relay);

		boolean condition = sutForTreeNodeRowRelay instanceof TreeNodeRowRelay;

		assertTrue("Class not correct instance!", condition);
	}

	@Test
	public void testNewInstance_ForTreeNodeRowParticipant() {
		Participant participant = Participant.newInstance();

		TreeNodeRow sutForTreeNodeRowRelay = TreeNodeRow.newInstance(participant, Position.FIRST);

		boolean condition = sutForTreeNodeRowRelay instanceof TreeNodeRowParticipant;

		assertTrue("Class not correct instance!", condition);
	}

	@Test
	public void testIsRelay() {
		boolean condition = sut.isRelay();

		assertFalse("[isRelay] not correct", condition);
	}

	@Test
	public void testGetRelayname() {
		String actual = sut.getRelayname();

		String expected = "";
		assertEquals("[getRelayname] not correct!", expected, actual);
	}

	@Test
	public void testGetPosition() {
		Position actual = sut.getPosition();

		Position expected = Position.UNKNOWN;
		assertEquals("[getPosition] not correct!", expected, actual);
	}

	@Test
	public void testGetStatus() {
		String actual = sut.getStatus();

		String expected = "";
		assertEquals("[getStatus] not correct!", expected, actual);
	}

	@Test
	public void testGetDuration() {
		String actual = sut.getDuration();

		String expected = "";
		assertEquals("[getDuration] not correct!", expected, actual);
	}

}