package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
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

	private EntityToMemberMapper sut = EntityToMemberMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToMember_whenParticipantEntityIsNull() {
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

	@Ignore
	@Test
	public void testMapToMember_check_position() {
		// TODO (EL, 2017-02-23): Unable to check the position since the actual Participant type do NOT hold a corresponding attribute!
	}
}
