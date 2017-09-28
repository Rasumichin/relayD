package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * Es bleibt einem jeden immer noch soviel Kraft, das auszuführen, wovon er überzeugt ist.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   21.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class RelayEventGatewayTest {

	public abstract RelayEventGateway getSut();

	protected UUID uuidForJustusJonas = UUID.randomUUID();
	protected UUID uuidForPeterShaw = UUID.randomUUID();

	@Test
	public void testGetAll() {
		Eventname expectedEventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(expectedEventname, expectedEventDay);
		getSut().set(rundUmEnnepetal);

		List<RelayEvent> actualRelayEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, actualRelayEventList.size());

		RelayEvent actualRelayEvent = actualRelayEventList.get(0);

		assertEquals("[name] not correct!", expectedEventname, actualRelayEvent.getName());
		assertEquals("[eventDay] not correct!", expectedEventDay, actualRelayEvent.getEventDay());
	}

	@Test
	public void testSet() {
		Eventname expectedEventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(expectedEventname, expectedEventDay);

		getSut().set(rundUmEnnepetal);
		List<RelayEvent> actualRelayEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, actualRelayEventList.size());

		RelayEvent actualRelayEventRundUmEnnepetal = actualRelayEventList.get(0);

		assertEquals("[name] for Rund um Ennepetal not correct!", expectedEventname, actualRelayEventRundUmEnnepetal.getName());
		assertEquals("[eventDay] for Rund um Ennepetal not correct!", expectedEventDay, actualRelayEventRundUmEnnepetal.getEventDay());

	}

	@Test
	public void testGet_ForExistingEntry() {
		RelayEvent expected = createRundUmEnnepetal();

		getSut().set(createDuesseldorfMarathon());
		getSut().set(expected);

		RelayEvent actual = getSut().get(expected.getUuid());

		assertNotNull("[get] has incorrect return value!", actual);
		assertEquals("[get] not correct!", expected, actual);
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createDuesseldorfMarathon());
		getSut().set(createRundUmEnnepetal());

		RelayEvent actual = getSut().get(UUID.randomUUID());

		assertNull(actual);
	}

	@Test
	@Ignore
	public void testSet_ForAddedParticipant() {
		// Arrange
		RelayEvent relayEvent = createRundUmEnnepetal();

		getSut().set(relayEvent);

		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		Participant participant = Participant.newInstance(person);
		relayEvent.addParticipant(participant);

		// Act
		getSut().set(relayEvent);

		//Assert
		RelayEvent relayEventFromGateway = getSut().get(relayEvent.getUuid());

		Collection<Participant> someParticipants = relayEventFromGateway.getParticipants();
		assertNotNull("Collection should not be null!", someParticipants);
		boolean condition = someParticipants.isEmpty();
		assertFalse("Collection should not be empty!", condition);
		Participant actual = someParticipants.iterator().next();
		assertEquals("Participant should be equals!", participant, actual);
	}

	@Test
	public void testSet_ForRemoveParticipantForListWithOneParticipant() {
		// Arrange
		RelayEvent relayEvent = createRundUmEnnepetal();
		Person person = Person.newInstance();
		person.setUuid(uuidForJustusJonas);
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		Participant participant = Participant.newInstance(person);
		relayEvent.addParticipant(participant);
		getSut().set(relayEvent);
		relayEvent.removeParticipant(participant);

		//Act
		getSut().set(relayEvent);

		// Assert
		RelayEvent relayEventFromGateway = getSut().get(relayEvent.getUuid());
		Collection<Participant> someParticipants = relayEventFromGateway.getParticipants();

		assertNotNull("Collection should not be null!", someParticipants);
		boolean condition = someParticipants.isEmpty();
		assertTrue("Collection should be empty!", condition);
	}

	@Test
	public void testSet_ForRemoveParticipantForListWithTwoParticipant() {
		// Arrange
		RelayEvent relayEvent = createRundUmEnnepetal();
		Person personOne = Person.newInstance();
		personOne.setUuid(uuidForJustusJonas);
		personOne.setForename(Forename.newInstance("Justus"));
		personOne.setSurename(Surename.newInstance("Jonas"));
		Participant participantOne = Participant.newInstance(personOne);
		relayEvent.addParticipant(participantOne);
		Person personTwo = Person.newInstance();
		personTwo.setUuid(uuidForPeterShaw);
		personTwo.setForename(Forename.newInstance("Peter"));
		personTwo.setSurename(Surename.newInstance("Shaw"));
		Participant participantTwo = Participant.newInstance(personTwo);
		relayEvent.addParticipant(participantTwo);

		getSut().set(relayEvent);
		relayEvent.removeParticipant(participantOne);

		//Act
		getSut().set(relayEvent);

		// Assert
		RelayEvent relayEventFromGateway = getSut().get(relayEvent.getUuid());
		Collection<Participant> someParticipants = relayEventFromGateway.getParticipants();

		assertNotNull("Collection should not be null!", someParticipants);
		boolean condition = someParticipants.isEmpty();
		assertFalse("Collection should be empty!", condition);
		int actualSize = someParticipants.size();
		assertEquals("Size of Collection not correct!", 1, actualSize);
		Participant actual = someParticipants.iterator().next();
		assertEquals("Participant should be equals!", participantTwo, actual);
	}

	private RelayEvent createDuesseldorfMarathon() {
		Eventname eventname = Eventname.newInstance("Metro Group Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));
		RelayEvent duesseldorfMarathon = RelayEvent.newInstance(eventname, eventDay);

		return duesseldorfMarathon;
	}

	private RelayEvent createRundUmEnnepetal() {
		Eventname eventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(eventname, eventDay);
		return rundUmEnnepetal;
	}
}