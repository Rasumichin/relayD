package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.RelayEvent;
import com.relayd.attributes.Comment;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Forename;
import com.relayd.ejb.orm.memory.MemorySingleton;

/**
 * 14. Es gibt keine endgültigen Entscheidungen
 * Keine Entscheidung ist in Stein gemeißelt. Nehmen Sie an, sie sei in den Sand geschrieben, und bereiten Sie sich auf Veränderung vor.
 *  - Andrew Hunt, Der Pragmatische Programmierer, Seite 42
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class ParticipantGatewayTest {
	private UUID firstParticipantUUID = null;

	public abstract ParticipantGateway getSut();

	@Before
	public void setUp() {
		Eventname expectedEventname = Eventname.newInstance("Rund um Ennepetal");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		RelayEvent rundUmEnnepetal = RelayEvent.newInstance(expectedEventname, expectedEventDay);

		Participant firstParticipant = createParticipantOne();
		firstParticipantUUID = firstParticipant.getUuid();
		rundUmEnnepetal.addParticipant(firstParticipant);
		rundUmEnnepetal.addParticipant(createParticipantTwo());
		MemorySingleton.getInstance().getEvents().put(rundUmEnnepetal.getUuid(), rundUmEnnepetal);
	}

	@Test
	public void testGet_ForExistingEntry() {

		Participant result = getSut().get(firstParticipantUUID);

		assertEquals("[get] not correct.", firstParticipantUUID, result.getUuid());
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createParticipantOne());

		Participant result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	@Test
	public void testSet() {
		Participant participant = getSut().get(firstParticipantUUID);

		Comment expected = Comment.newInstance("Ein Kommentar");
		participant.setComment(expected);

		getSut().set(participant);

		Participant updatedParticipant = getSut().get(firstParticipantUUID);

		assertEquals("[set] not correct.", expected, updatedParticipant.getComment());
	}

	protected Participant createParticipantOne() {
		Person justus = Person.newInstance();
		justus.setForename(Forename.newInstance("Justus"));
		Participant participant = Participant.newInstance(justus);
		return participant;
	}

	protected Participant createParticipantTwo() {
		Person peter = Person.newInstance();
		peter.setForename(Forename.newInstance("Peter"));
		Participant participant = Participant.newInstance(peter);
		return participant;
	}
}