package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Distance;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Position;
import com.relayd.attributes.RelayCount;
import com.relayd.attributes.Relayname;

/**
 * Keine Straße ist zu lang mit einem Test an der Seite.
 *  - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventTest {
	private Eventname eventName = Eventname.newInstance("Metro Group Marathon Düsseldorf");
	private EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

	private RelayEvent sut = RelayEvent.newInstance(eventName, eventDay);

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateValidInstance() {

		Eventname actualName = sut.getName();
		EventDay actualEventDay = sut.getEventDay();

		assertEquals("[Name] not correct.", eventName, actualName);
		assertEquals("[EventDay] not correct.", eventDay, actualEventDay);
	}

	@Test
	public void testCreateEmptyInstance() {
		RelayEvent sutEmpty = RelayEvent.newInstance();

		assertNotNull("[Uuid] not correct!", sutEmpty.getUuid());
		assertNotNull("[Name] not correct!", sutEmpty.getName());
		assertNotNull("[EventDay] not correct!", sutEmpty.getEventDay());
	}

	@Test
	public void testName() {
		Eventname expected = Eventname.newInstance("Name");

		sut.setName(expected);

		Eventname result = sut.getName();

		assertEquals(expected, result);
	}

	@Test
	public void testEventDay() {
		EventDay expected = EventDay.newInstance(LocalDate.now());

		sut.setEventDay(expected);

		EventDay result = sut.getEventDay();

		assertEquals(expected, result);
	}

	@Test
	public void testAddParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();

		sut.addParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		assertEquals("[participants] size not correct!", 1, actual.size());
		Participant actualParticipant = actual.iterator().next();

		assertEquals("inserted participant not correct!", participant, actualParticipant);
	}

	@Test
	public void testAddParticipant_ForSameParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();

		sut.addParticipant(participant);
		sut.addParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		assertEquals("[participants] size not correct!", 1, actual.size());
		Participant actualParticipant = actual.iterator().next();

		assertEquals("inserted participant not correct!", participant, actualParticipant);
	}

	@Test
	public void testRemoveParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		sut.addParticipant(participant);

		sut.removeParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		boolean condition = actual.isEmpty();
		assertTrue("[participants] size not correct!", condition);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetParticipant_ForUnmodifiable() {
		Collection<Participant> participants = sut.getParticipants();
		participants.add(Participant.newInstance());
	}

	@Test
	public void testUuid() {

		assertNotNull("Expected valid instance.", sut.getUuid());
	}

	@Test
	public void testSetUuid() {
		Eventname eventNameDummy = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		secondSut.setUuid(sut.getUuid());

		assertEquals(sut, secondSut);
	}

	@Test
	public void testAddRelay_ForOneEntry() {
		Relay dieVierFragezeichen = Relay.newInstance();
		dieVierFragezeichen.setRelayname(Relayname.newInstance("Die 4 ????"));

		sut.addRelay(dieVierFragezeichen);

		Set<Relay> relays = sut.getRelays();
		assertNotNull("[relays] instance not correct!", relays);
		assertEquals("[size] of relaylist is not correct!", 1, relays.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRelay_ForMaximumBorder() {
		for (int i = 0; i < sut.getMaxNumberOfRelays().intValue(); i++) {
			sut.addRelay(Relay.newInstance());
		}
		Relay dieVierFragezeichen = Relay.newInstance();
		dieVierFragezeichen.setRelayname(Relayname.newInstance("Die 4 ????"));

		sut.addRelay(dieVierFragezeichen);
	}

	@Test
	public void testIsRelayFull_ForSpace() {
		sut.setMaxNumberOfRelays(RelayCount.newInstance(1));

		boolean condition = sut.isRelayFull();

		assertFalse("[isRelayFull] not correct!", condition);
	}

	@Test
	public void testIsRelayFull_ForFull() {
		sut.setMaxNumberOfRelays(RelayCount.newInstance(1));
		sut.addRelay(Relay.newInstance());

		boolean condition = sut.isRelayFull();

		assertTrue("[isRelayFull] not correct!", condition);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetRelay_ForUnmodifiable() {
		Set<Relay> relays = sut.getRelays();
		relays.add(Relay.newInstance());
	}

	@Test
	public void testGetMaxNumberOfRelays_Default() {
		RelayCount expected = RelayCount.newInstance(18);
		RelayCount actual = sut.getMaxNumberOfRelays();

		assertEquals("[getMaxNumberOfRelays] not correct!", expected, actual);
	}

	@Test
	public void testMaxNumberOfRelays() {
		RelayCount expected = RelayCount.newInstance(10);

		sut.setMaxNumberOfRelays(expected);

		RelayCount actual = sut.getMaxNumberOfRelays();

		assertEquals("[maxNumberOfRelays] not correct!", expected, actual);
	}

	@Test
	public void testGetNumberOfRelays_ForEmptyRelayList() {
		Integer actual = sut.getNumberOfRelays();

		assertEquals(Integer.valueOf(0), actual);
	}

	@Test
	public void testGetNumberOfRelays_ForListWithOneEntry() {
		sut.addRelay(Relay.newInstance());

		Integer actual = sut.getNumberOfRelays();

		assertEquals(Integer.valueOf(1), actual);
	}

	@Test
	public void testGetNumberOfParticipants_ForEmptyParticipantList() {
		Integer actual = sut.getNumberOfParticipants();

		assertEquals(Integer.valueOf(0), actual);
	}

	@Test
	public void testGetNumberOfParticipants_ForListWithOneEntry() {
		sut.addParticipant(Participant.newInstance());

		Integer actual = sut.getNumberOfParticipants();

		assertEquals(Integer.valueOf(1), actual);
	}

	@Test
	public void testGetTrackForPosition_ForPositionOne() {
		Track track = sut.getTrackForPosition(Position.FIRST);

		assertEquals("[track] for given position is not correct!", "11.3 km Nordpark - Wechselzone: ", track.toString());
	}

	@Test
	public void testGetTrackForPosition_ForPositionTwo() {
		Track track = sut.getTrackForPosition(Position.SECOND);

		assertEquals("[track] for given position is not correct!", "13.1 km Oberkassel - Wechselzone: ", track.toString());
	}

	@Test
	public void testGetTrackForPosition_ForPositionThree() {
		Track track = sut.getTrackForPosition(Position.THIRD);

		assertEquals("[track] for given position is not correct!", "8.6 km Pempelfort - Wechselzone: ", track.toString());
	}

	@Test
	public void testGetTrackForPosition_ForPositionFour() {
		Track track = sut.getTrackForPosition(Position.FOURTH);

		assertEquals("[track] for given position is not correct!", "9.2 km Carlstadt - Wechselzone: ", track.toString());
	}

	@Test
	public void testGetTracks() {
		List<Track> actual = sut.getTracks();

		assertNotNull("[getTracks] list no valid instance!", actual);

		int expected = 4;
		assertEquals("size of list not correct!", expected, actual.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetTracks_ForImmutable() {
		List<Track> actual = sut.getTracks();

		actual.add(Track.newInstance(Distance.newInstance()));
	}

	@Test
	public void testCompleteRelays() {
		Relay dieVierFragezeichen = Relay.newInstance();
		dieVierFragezeichen.addMember(Member.newInstance(Participant.newInstance(new PersonBuilder().build())));
		dieVierFragezeichen.addMember(Member.newInstance(Participant.newInstance(new PersonBuilder().build())));
		dieVierFragezeichen.addMember(Member.newInstance(Participant.newInstance(new PersonBuilder().build())));
		dieVierFragezeichen.addMember(Member.newInstance(Participant.newInstance(new PersonBuilder().build())));

		sut.addRelay(dieVierFragezeichen);

		Relay staubwolke = Relay.newInstance();
		staubwolke.addMember(Member.newInstance());
		staubwolke.addMember(Member.newInstance());

		sut.addRelay(staubwolke);

		Integer actual = sut.completeRelays();

		Integer expected = 1;

		assertEquals("[completeRelays] not corret!", expected, actual);
	}

	@Test
	public void testToString() {
		String actual = sut.toString();
		String expected = "Metro Group Marathon Düsseldorf, 2017-04-30";

		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testGetHashCode() {
		UUID uuid = UUID.fromString("53a27b33-a5cb-4997-8eaf-dcf8bd1cb2d2");
		sut.setUuid(uuid);

		int hashCode = sut.hashCode();

		assertEquals(-975545171, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithUuidIsNull() {
		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		sut.setUuid(null);

		boolean result = sut.equals(secondEvent);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUuidAreNull() {
		sut.setUuid(null);

		RelayEvent secondEvent = RelayEvent.newInstance(null, null);
		secondEvent.setUuid(null);

		boolean result = sut.equals(secondEvent);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUuid() {
		Eventname eventNameDummy = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent firstSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);
		RelayEvent secondSut = RelayEvent.newInstance(eventNameDummy, eventDayDummy);

		assertNotEquals(firstSut, secondSut);
	}
}