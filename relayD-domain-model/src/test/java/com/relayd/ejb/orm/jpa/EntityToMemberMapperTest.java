package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToMemberMapperTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private EntityToMemberMapper sut = EntityToMemberMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapToMember_whenParticipantEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[participantEntity] must not be 'null'.");

		sut.mapToMember(null);
	}

	@Test
	public void testMapToMember_check_id() {
		UUID expected = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expected);
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);

		Member member = sut.mapToMember(participantEntity);

		UUID actual = member.getUuidPerson();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToMember_check_person() {
		String expected = "Robert";
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setForename(expected);

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);

		Member member = sut.mapToMember(participantEntity);

		String actual = member.getForename().toString();
		assertEquals("Mapping of [person] is not correct!", expected, actual);
	}

	@Test
	public void testMapToMember_check_duration() {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		PersonEntity dummyEntity = PersonEntity.newInstance();
		participantEntity.setPersonEntity(dummyEntity);
		Long oneSecond = 1000L;
		participantEntity.setDuration(oneSecond * 15);

		Member member = sut.mapToMember(participantEntity);

		Duration actual = member.getDuration();
		Duration expected = Duration.ofSeconds(15);

		assertEquals("Mapping of [duration] is not corret!", expected, actual);
	}
}
