package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.entity.MemberEntity;
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

		Integer actual = Integer.valueOf(relayEntity.getMemberEntities().size());
		assertEquals("Member mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_no_participants_one_participant_entity() {
		Integer expected = relay.memberCount();
		MemberEntity participantEntity = MemberEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		relayEntity.addMemberEntity(participantEntity);

		sutSpy.mapMembersToEntities(relay, relayEntity);

		Integer actual = Integer.valueOf(relayEntity.getMemberEntities().size());
		assertEquals("Member mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_one_participant_no_participant_entities() {
		doReturn(PersonEntity.newInstance()).when(sutSpy).findPersonEntityFor(any());
		relay.addMember(Member.newInstance(Participant.newInstance()));
		Integer expected = relay.memberCount();

		sutSpy.mapMembersToEntities(relay, relayEntity);

		Integer actual = Integer.valueOf(relayEntity.getMemberEntities().size());
		assertEquals("Member mapping is not correct!", expected, actual);
	}

	@Test
	public void testMapMembersToEntities_one_participant_one_participant_entity_no_changes() {
		// Arrange
		Person person = Person.newInstance();
		Member member = Member.newInstance(Participant.newInstance(person));
		relay.addMember(member);
		UUID expectedUuid = member.getUuidPerson();
		Integer expected = relay.memberCount();

		MemberEntity participantEntity = MemberEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(PersonEntity.newInstance(expectedUuid));
		relayEntity.addMemberEntity(participantEntity);

		// Act
		sutSpy.mapMembersToEntities(relay, relayEntity);

		// Assert
		Integer actual = Integer.valueOf(relayEntity.getMemberEntities().size());
		assertEquals("Member mapping is not correct!", expected, actual);

		MemberEntity mappedMemberEntity = relayEntity.getMemberEntityAtPosition(Integer.valueOf(1)).get();
		UUID actualUuid = mappedMemberEntity.getUuidPerson();
		assertEquals("Member mapping is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testMapMembersToEntities_one_participant_one_participant_entity_changes() {
		// Arrange
		Person person = Person.newInstance();
		Member member = Member.newInstance(Participant.newInstance(person));
		relay.addMember(member);
		UUID expectedUuid = member.getUuidPerson();
		Integer expected = relay.memberCount();

		doReturn(PersonEntity.newInstance(expectedUuid)).when(sutSpy).findPersonEntityFor(any());

		MemberEntity participantEntity = MemberEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(PersonEntity.newInstance(UUID.randomUUID()));
		relayEntity.addMemberEntity(participantEntity);

		// Act
		sutSpy.mapMembersToEntities(relay, relayEntity);

		// Assert
		Integer actual = Integer.valueOf(relayEntity.getMemberEntities().size());
		assertEquals("Member mapping is not correct!", expected, actual);

		MemberEntity mappedMemberEntity = relayEntity.getMemberEntityAtPosition(Integer.valueOf(1)).get();
		UUID actualUuid = mappedMemberEntity.getUuidPerson();
		assertEquals("Member mapping is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testSetNewPersonEntityById() {
		UUID expectedUuid = UUID.randomUUID();
		MemberEntity participantEntity = MemberEntity.newInstance();
		participantEntity.setPersonEntity(PersonEntity.newInstance(UUID.randomUUID()));
		doReturn(PersonEntity.newInstance(expectedUuid)).when(sutSpy).findPersonEntityFor(any());

		sutSpy.setNewPersonEntityById(participantEntity, expectedUuid);

		UUID actualUuid = participantEntity.getUuidPerson();
		assertEquals("Setting of new [personEntity] is not correct!", expectedUuid, actualUuid);
	}

	@Test
	public void testGetNewMemberEntity() {
		Integer expectedPosition = Integer.valueOf(1);
		UUID somePersonUuid = UUID.randomUUID();
		PersonEntity expectedPersonEntity = PersonEntity.newInstance(somePersonUuid);
		doReturn(expectedPersonEntity).when(sutSpy).findPersonEntityFor(any());

		MemberEntity result = sutSpy.getNewMemberEntity(expectedPosition, somePersonUuid);

		assertEquals("Creation of new [participantEntity] has failed. [position] is not correct!", expectedPosition, result.getPosition());
		assertEquals("Creation of new [participantEntity] has failed. [personEntity] is not correct!", expectedPersonEntity, result.getPersonEntity());
	}
}
