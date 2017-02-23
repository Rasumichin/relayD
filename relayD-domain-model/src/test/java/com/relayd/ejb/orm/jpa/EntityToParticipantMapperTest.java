package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.*;
import com.relayd.entity.*;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToParticipantMapperTest {

	private EntityToParticipantMapper sut = EntityToParticipantMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToParticipant_whenParticipantEntityIsNull() {
		sut.mapToParticipant(null);
	}

	@Test
	public void testMapToParticipant_check_id() {
		UUID expected = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expected);
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);

		Participant participant = sut.mapToParticipant(participantEntity);

		UUID actual = participant.getUuidPerson();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToParticipant_check_person() {
		String expected = "Robert";
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setForename(expected);
		
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);

		Participant participant = sut.mapToParticipant(participantEntity);

		String actual = participant.getForename().toString();
		assertEquals("Mapping of [person] is not correct!", expected, actual);
	}

	@Ignore
	@Test
	public void testMapToParticipant_check_position() {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));

		Participant participant = sut.mapToParticipant(participantEntity);
		// TODO (EL, 2017-02-23): Unable to check the position since the actual Participant type do NOT hold a corresponding attribute!
	}
}
