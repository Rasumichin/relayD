package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Shirtsize;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;

/**
 * Wenn Du müde bist: Schlafe!
 * Wenn Du Hunger hast: Esse!
 * Wenn Du Progammierst: Teste!
 *  - Zen Weisheit
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToParticipantMapperTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private EntityToParticipantMapper sut = EntityToParticipantMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapToParticipant_whenParticipantEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[participantEntity] must not be 'null'.");

		sut.mapToParticipant(null);
	}

	@Test
	public void testMapToParticipant_check_id() {
		UUID expectedPerson = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expectedPerson);
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);

		Participant participant = sut.mapToParticipant(participantEntity);

		UUID actualPerson = participant.getUuidPerson();
		assertEquals("Mapping of [uuidPerson] is not correct!", expectedPerson, actualPerson);

		UUID participantUUID = participant.getUuid();
		String actualParticipant = participantUUID.toString();
		assertEquals("Mapping of [uuidPerson] is not correct!", participantEntity.getId(), actualParticipant);
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

	@Test
	public void testMapToParticipant_check_comment() {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		PersonEntity dummyEntity = PersonEntity.newInstance();
		participantEntity.setPersonEntity(dummyEntity);
		String comment = "a comment";
		participantEntity.setComment(comment);

		Participant participant = sut.mapToParticipant(participantEntity);

		Comment actual = participant.getComment();
		Comment expected = Comment.newInstance(comment);

		assertEquals("Mapping of [comment] is not corret!", expected, actual);
	}

	@Test
	public void testMapToParticipant_check_shirtsize() {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		PersonEntity dummyEntity = PersonEntity.newInstance();
		participantEntity.setPersonEntity(dummyEntity);
		Integer shirtsize = 7;
		participantEntity.setShirtsize(shirtsize);

		Participant participant = sut.mapToParticipant(participantEntity);

		Shirtsize actual = participant.getShirtsize();
		Shirtsize expected = Shirtsize.newInstance(shirtsize);

		assertEquals("Mapping of [shirtsize] is not corret!", expected, actual);
	}

}
