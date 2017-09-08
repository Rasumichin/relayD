package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * What makes a clean test? Three things. Readability, readability and readability.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  07.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberEntityIT extends EntityIT {
	private PersonEntity personEntity;
	private RelayEntity relayEntity;

	private PersonEntity getPersonEntity() {
		return personEntity;
	}

	private RelayEntity getRelayEntity() {
		return relayEntity;
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();

		setUpPersonEntity();
		RelayEventEntity relayEventEntity = setUpRelayEventEntity();
		setUpRelayEntity(relayEventEntity);
	}

	private void setUpPersonEntity() {
		PersonEntity personToBeInserted = PersonEntity.newInstance(UUID.randomUUID());
		persistEntity(personToBeInserted);
		personEntity = getEntityManager().find(PersonEntity.class, personToBeInserted.getId());
	}

	private RelayEventEntity setUpRelayEventEntity() {
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("Foo Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		persistEntity(relayEventEntity);
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, relayEventEntity.getId());

		return relayEventEntity;
	}

	private void setUpRelayEntity(RelayEventEntity relayEventEntity) {
		RelayEntity relayToBeInserted = RelayEntity.newInstance(UUID.randomUUID());
		relayToBeInserted.setRelayname("Foo Relay");
		relayToBeInserted.setRelayEventEntity(relayEventEntity);
		persistEntity(relayToBeInserted);
		relayEntity = getEntityManager().find(RelayEntity.class, relayToBeInserted.getId());
	}

	@Test
	public void testInsertMemberEntity() {
		String expectedId = UUID.randomUUID().toString();
		persistEntity(getDefaultMemberEntity(expectedId));

		MemberEntity result = findById(expectedId);
		assertNotNull("MemberEntity could not be found with 'id=" + expectedId + "'!", result);

		String resultId = result.getId();
		assertEquals("MemberEntity could not be found with 'id=" + expectedId + "'!", expectedId, resultId);
	}

	@Test
	public void testRelationToPersonEntity() {
		personEntity = getPersonEntity();
		String expected = personEntity.getId();

		String uuid = UUID.randomUUID().toString();
		persistEntity(getDefaultMemberEntity(uuid));
		MemberEntity result = findById(uuid);

		String actual = result.getPersonEntity().getId();

		assertEquals("Relation to 'PersonEntity' has not been correctly resolved!", expected, actual);
	}

	@Test
	public void testRelationToRelayEntity() {
		relayEntity = getRelayEntity();
		String expected = relayEntity.getId();

		String uuid = UUID.randomUUID().toString();
		persistEntity(getDefaultMemberEntity(uuid));
		MemberEntity result = findById(uuid);

		String actual = result.getRelayEntity().getId();

		assertEquals("Relation to 'RelayEntity' has not been correctly resolved!", expected, actual);
	}

	@Test
	public void testUpdateMemberEntity_Set_Position() {
		String id = UUID.randomUUID().toString();
		MemberEntity memberEntity = getDefaultMemberEntity(id);
		persistEntity(memberEntity);
		MemberEntity sut = findById(id);

		Integer expected = Integer.valueOf(4);
		sut.setPosition(expected);

		MemberEntity result = mergeEntity(sut);

		Integer actual = result.getPosition();
		assertEquals("[position] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testDeleteMemberEntity() {
		String id = UUID.randomUUID().toString();
		MemberEntity memberEntity = getDefaultMemberEntity(id);
		persistEntity(memberEntity);
		MemberEntity sut = findById(id);

		removeEntity(sut);

		MemberEntity result = findById(id);
		assertNull("MemberEntity has not been deleted correctly!", result);
	}

	private MemberEntity getDefaultMemberEntity(String anId) {
		MemberEntity memberEntity = MemberEntity.newInstance(anId);
		memberEntity.setPosition(Integer.valueOf(1));
		memberEntity.setPersonEntity(getPersonEntity());
		memberEntity.setRelayEntity(getRelayEntity());

		return memberEntity;
	}

	private MemberEntity findById(String anId) {
		return getEntityManager().find(MemberEntity.class, anId);
	}
}
