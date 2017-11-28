package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Comment;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Forename;
import com.relayd.attributes.RelayCount;
import com.relayd.attributes.Shirtsize;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 * 4. Akzeptieren Sie keine zerbrochenen Fensterscheiben
 * Bringen Sie schlechte Entwürfe, falsche Entscheidungen und mangelhaften Quelltext in Ordnung, wenn Sie darauf stoßen.
 *  - Andrew Hunt, Der Pragmatische Programmierer, Seite 4
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToRelayEventMapperTest {

	private EntityToRelayEventMapper sut = EntityToRelayEventMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToRelayEvent_whenRelayEventEntityIsNull() {
		sut.mapToRelayEvent(null);
	}

	@Test
	public void testMapToRelayEvent_check_id() {
		UUID expected = UUID.randomUUID();
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance(expected);
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));
		relayEventEntity.setMaxNumberOfRelays(13);

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		UUID actual = relayEvent.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventName() {
		Eventname expected = Eventname.newInstance("My Event");
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName(expected.toString());
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));
		relayEventEntity.setMaxNumberOfRelays(13);

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		Eventname actual = relayEvent.getName();
		assertEquals("Mapping of [eventName] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventDay() {
		Date today = new Date(System.currentTimeMillis());
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(today);
		relayEventEntity.setMaxNumberOfRelays(13);

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		EventDay expected = EventDay.newInstance(today.toLocalDate());
		EventDay actual = relayEvent.getEventDay();

		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_maxNumberOfRelays() {
		RelayCount expected = RelayCount.newInstance(13);
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));
		relayEventEntity.setMaxNumberOfRelays(expected.intValue());

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		RelayCount actual = relayEvent.getMaxNumberOfRelays();
		assertEquals("Mapping of [maxNumberOfRelays] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_Relays() {
		// Arrange
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));
		relayEventEntity.setMaxNumberOfRelays(13);

		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEventEntity.addRelay(relayEntity);

		// Act
		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		// Assert
		Set<Relay> someRelays = relayEvent.getRelays();
		assertEquals("[Relays] have not right size!", 1, someRelays.size());

		Relay expected = Relay.newInstance();
		expected.setUuid(UUID.fromString(relayEntity.getId()));

		Relay actual = someRelays.iterator().next();

		assertEquals("Mapping of [Relay] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_Participants() {
		// Arrange
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));
		relayEventEntity.setMaxNumberOfRelays(13);

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();

		String expectedComment = "A comment";
		participantEntity.setComment(expectedComment);

		Shirtsize expectedShirtSize = Shirtsize.DamenS;
		participantEntity.setShirtsize(expectedShirtSize.getSize());

		relayEventEntity.addParticipant(participantEntity);

		PersonEntity persontEntity = PersonEntity.newInstance();
		persontEntity.setForename("Justus");

		participantEntity.setPersonEntity(persontEntity);

		// Act
		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		// Assert
		Collection<Participant> someParticipants = relayEvent.getParticipants();
		assertEquals("[Participants] have not right size!", 1, someParticipants.size());

		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		Participant expected = Participant.newInstance(person);
		expected.setUuid(UUID.fromString(participantEntity.getId()));
		expected.setComment(Comment.newInstance(expectedComment));
		expected.setShirtsize(expectedShirtSize);
		Participant actual = someParticipants.iterator().next();

		assertEquals("Mapping of [Participants] is not correct!", expected, actual);
		assertEquals("Comment of [Participants] is not correct!", expected.getComment(), actual.getComment());
		assertEquals("Shirtsize of [Participants] is not correct!", expected.getShirtsize(), actual.getShirtsize());

	}
}