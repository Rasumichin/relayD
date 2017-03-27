package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEntity;

import static org.mockito.Mockito.*;

/**
 * Master Ninjei: If the application does not run correctly, do not blame the operating system.
 *  - Geoffrey James (The Zen of Programming, P. 29)
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   04.03.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayGatewayJPATest {
	private Relay relay = Relay.newInstance();
	private RelayEntity relayEntity = RelayEntity.newInstance();
	private RelayGatewayJPA sutSpy = spy(RelayGatewayJPA.class);

	@Test
	public void testMapMembersToEntities_no_participants_no_participant_entities() {
		Integer expected = relay.memberCount();

		sutSpy.mapMembersToEntities(relay, relayEntity);

		Integer actual = Integer.valueOf(relayEntity.getParticipantEntities().size());
		assertEquals("Participant mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_no_participants_one_participant_entity() {
		Integer expected = relay.memberCount();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		relayEntity.addParticipantEntity(participantEntity);

		sutSpy.mapMembersToEntities(relay, relayEntity);

		Integer actual = Integer.valueOf(relayEntity.getParticipantEntities().size());
		assertEquals("Participant mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_one_participant_no_participant_entities() {
		doReturn(PersonEntity.newInstance()).when(sutSpy).findPersonEntityFor(any());
		relay.addMember(Member.newInstance(Person.newInstance()));
		Integer expected = relay.memberCount();

		sutSpy.mapMembersToEntities(relay, relayEntity);

		Integer actual = Integer.valueOf(relayEntity.getParticipantEntities().size());
		assertEquals("Participant mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_one_participant_one_participant_entity_no_changes() {
		// Arrange
		Member member = Member.newInstance(Person.newInstance());
		relay.addMember(member);
		UUID expectedUuid = member.getUuidPerson();
		Integer expected = relay.memberCount();

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(PersonEntity.newInstance(expectedUuid));
		relayEntity.addParticipantEntity(participantEntity);

		// Act
		sutSpy.mapMembersToEntities(relay, relayEntity);

		// Assert
		Integer actual = Integer.valueOf(relayEntity.getParticipantEntities().size());
		assertEquals("Participant mapping is not correct!", expected, actual);

		ParticipantEntity mappedParticipantEntity = relayEntity.getParticipantEntityAtPosition(Integer.valueOf(1)).get();
		UUID actualUuid = mappedParticipantEntity.getUuidPerson();
		assertEquals("Participant mapping is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testMapMembersToEntities_one_participant_one_participant_entity_changes() {
		// Arrange
		Member member = Member.newInstance(Person.newInstance());
		relay.addMember(member);
		UUID expectedUuid = member.getUuidPerson();
		Integer expected = relay.memberCount();

		doReturn(PersonEntity.newInstance(expectedUuid)).when(sutSpy).findPersonEntityFor(any());

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(PersonEntity.newInstance(UUID.randomUUID()));
		relayEntity.addParticipantEntity(participantEntity);

		// Act
		sutSpy.mapMembersToEntities(relay, relayEntity);

		// Assert
		Integer actual = Integer.valueOf(relayEntity.getParticipantEntities().size());
		assertEquals("Participant mapping is not correct!", expected, actual);

		ParticipantEntity mappedParticipantEntity = relayEntity.getParticipantEntityAtPosition(Integer.valueOf(1)).get();
		UUID actualUuid = mappedParticipantEntity.getUuidPerson();
		assertEquals("Participant mapping is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testSetNewPersonEntityById() {
		UUID expectedUuid = UUID.randomUUID();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(PersonEntity.newInstance(UUID.randomUUID()));
		doReturn(PersonEntity.newInstance(expectedUuid)).when(sutSpy).findPersonEntityFor(any());

		sutSpy.setNewPersonEntityById(participantEntity, expectedUuid);

		UUID actualUuid = participantEntity.getUuidPerson();
		assertEquals("Setting of new [personEntity] is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testGetNewParticipantEntity() {
		Integer expectedPosition = Integer.valueOf(1);
		UUID somePersonUuid = UUID.randomUUID();
		PersonEntity expectedPersonEntity = PersonEntity.newInstance(somePersonUuid);
		doReturn(expectedPersonEntity).when(sutSpy).findPersonEntityFor(any());

		ParticipantEntity result = sutSpy.getNewParticipantEntity(expectedPosition, somePersonUuid);

		assertEquals("Creation of new [participantEntity] has failed. [position] is not correct!", expectedPosition, result.getPosition());
		assertEquals("Creation of new [participantEntity] has failed. [personEntity] is not correct!", expectedPersonEntity, result.getPersonEntity());
	}
}
