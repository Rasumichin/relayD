package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Classes should be small!
 *  - Jeff Langr (Chapter 10 of Robert C. Martin's "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  06.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEntityIT extends EntityIT {
	private RelayEventEntity relayEventEntity;
	private PersonEntity personEntity;

	private RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
	}

	private PersonEntity getPersonEntity() {
		return personEntity;
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();

		setUpRelayEventEntity();
		setUpPersonEntity();
	}

	private void setUpRelayEventEntity() {
		RelayEventEntity eventToBeInserted = RelayEventEntity.newInstance();
		eventToBeInserted.setEventName("Foo Event");
		eventToBeInserted.setEventDay(new Date(System.currentTimeMillis()));
		persistEntity(eventToBeInserted);
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, eventToBeInserted.getId());
	}

	private void setUpPersonEntity() {
		PersonEntity personToBeInserted = PersonEntity.newInstance();
		personToBeInserted.setForename("Peter");
		personToBeInserted.setSurename("Principle");
		persistEntity(personToBeInserted);
		personEntity = getEntityManager().find(PersonEntity.class, personToBeInserted.getId());
	}

	@Test
	public void testInsertRelayEntity() {
		String expected = UUID.randomUUID().toString();
		insertRelayEntity(expected);

		RelayEntity result = findRelayEntityById(expected);
		assertNotNull("RelayEntity could not be found with 'id=" + expected + "'!", result);

		String actual = result.getId();
		assertEquals("RelayEntity could not be found with 'id=" + expected + "'!", expected, actual);

		String relaynameActual = result.getRelayname();
		String relaynameExpected = "Four Star Runners";

		assertEquals("[relayname] not correct!", relaynameExpected, relaynameActual);

		Long durationActual = result.getDuration();
		Long durationExpected = 17000L;

		assertEquals("[duration] not correct!", durationExpected, durationActual);
	}

	@Test
	public void testInsertRelayEntity_with_one_new_member() {
		int expected = 1;
		RelayEntity sut = getRelayEntityWithMembers(Integer.valueOf(expected));
		String expectedId = sut.getMemberEntities().get(0).getId();

		persistEntity(sut);

		RelayEntity result = findRelayEntityById(sut.getId());
		List<MemberEntity> members = result.getMemberEntities();
		int actual = members.size();
		assertEquals("Relation to 'MemberEntity' has not been inserted correctly!", expected, actual);

		String actualId = members.get(0).getId();
		assertEquals("Relation to 'MemberEntity' has not been inserted correctly!", expectedId, actualId);
	}

	@Test
	public void testInsertRelayEntity_with_multiple_new_members() {
		int expected = 3;
		RelayEntity sut = getRelayEntityWithMembers(Integer.valueOf(expected));

		persistEntity(sut);

		RelayEntity result = findRelayEntityById(sut.getId());
		List<MemberEntity> members = result.getMemberEntities();
		int actual = members.size();
		assertEquals("Relation to 'MemberEntity' has not been inserted correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Set_Relayname() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		String expected = "New Relayname";
		relayEntity.setRelayname(expected);

		RelayEntity result = mergeEntity(relayEntity);

		String actual = result.getRelayname();
		assertEquals("[relayName] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Add_Member() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		assertTrue("Relation to 'MemberEntity' has not been updated correctly!", relayEntity.getMemberEntities().isEmpty());

		MemberEntity memberEntity = getDefaultMemberEntity(UUID.randomUUID().toString());
		relayEntity.addMemberEntity(memberEntity);

		RelayEntity result = mergeEntity(relayEntity);

		List<MemberEntity> members = result.getMemberEntities();
		int expected = 1;
		int actual = members.size();
		assertEquals("Relation to 'MemberEntity' has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Remove_Member() {
		int initialMembers = 3;
		RelayEntity sut = getRelayEntityWithMembers(Integer.valueOf(initialMembers));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		MemberEntity memberToBeRemoved = sut.getMemberEntities().get(0);
		sut.removeMemberEntity(memberToBeRemoved);

		RelayEntity result = mergeEntity(sut);

		List<MemberEntity> members = result.getMemberEntities();
		int expected = 2;
		int actual = members.size();
		assertEquals("Relation to 'MemberEntity' has not been removed correctly!", expected, actual);

		String removedId = memberToBeRemoved.getId();
		members = result.getMemberEntities()
				.stream()
				.filter(eachMember -> eachMember.getId().equals(removedId))
				.collect(Collectors.toList());
		assertTrue("Relation to 'MemberEntity' has not been removed correctly!", members.isEmpty());
	}

	@Test
	public void testUpdateRelayEntity_Update_Member() {
		int initialMembers = 1;
		RelayEntity sut = getRelayEntityWithMembers(Integer.valueOf(initialMembers));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		MemberEntity memberEntity = sut.getMemberEntities().get(0);
		int expected = 4;
		memberEntity.setPosition(Integer.valueOf(expected));

		RelayEntity result = mergeEntity(sut);

		memberEntity = result.getMemberEntities().get(0);
		int actual = memberEntity.getPosition().intValue();
		assertEquals("Relation to 'MemberEntity' has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Set_RelayEventEntity() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		RelayEventEntity expected = RelayEventEntity.newInstance();
		expected.setEventName("New Event");
		expected.setEventDay(new Date(System.currentTimeMillis()));
		persistEntity(expected);

		relayEntity.setRelayEventEntity(expected);
		RelayEntity result = mergeEntity(relayEntity);

		RelayEventEntity actual = result.getRelayEventEntity();
		assertEquals("[relayEventEntity] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testDeleteRelayEntity() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		removeEntity(relayEntity);

		RelayEntity result = findRelayEntityById(id);
		assertNull("RelayEntity has not been deleted correctly!", result);
	}

	@Test
	public void testDeleteRelayEntity_with_one_member() {
		int initialMembers = 1;
		RelayEntity sut = getRelayEntityWithMembers(Integer.valueOf(initialMembers));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		MemberEntity memberEntity = sut.getMemberEntities().get(0);
		String memberIdNotToBeFound = memberEntity.getId();

		removeEntity(sut);

		MemberEntity result = getEntityManager().find(MemberEntity.class, memberIdNotToBeFound);
		assertNull("RelayEntity has not been deleted correctly!", result);
	}

	@Test
	public void testRelationToRelayEvent() {
		RelayEventEntity expected = getRelayEventEntity();

		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		RelayEventEntity actual = relayEntity.getRelayEventEntity();

		assertEquals("Relation to 'RelayEventEntity' has not been resolved correctly!", expected, actual);
	}

	private RelayEntity insertRelayEntity(String anId) {
		RelayEntity relayEntity = getDefaultRelayEntity(anId);
		persistEntity(relayEntity);

		return relayEntity;
	}

	private RelayEntity getDefaultRelayEntity(String anId) {
		RelayEntity relayEntity = RelayEntity.newInstance(UUID.fromString(anId));
		relayEntity.setRelayname("Four Star Runners");
		relayEntity.setRelayEventEntity(getRelayEventEntity());
		relayEntity.setDuration(Duration.ofSeconds(17).toMillis());

		return relayEntity;
	}

	private RelayEntity findRelayEntityById(String anId) {
		return getEntityManager().find(RelayEntity.class, anId);
	}

	private MemberEntity getDefaultMemberEntity(String anId) {
		MemberEntity memberEntity = MemberEntity.newInstance(anId);
		memberEntity.setPosition(Integer.valueOf(1));
		memberEntity.setPersonEntity(getPersonEntity());

		return memberEntity;
	}

	private RelayEntity getRelayEntityWithMembers(Integer numberOfMembers) {
		RelayEntity relayEntity = getDefaultRelayEntity(UUID.randomUUID().toString());

		for (int i = 0; i < numberOfMembers.intValue(); i++) {
			MemberEntity memberEntity = getDefaultMemberEntity(UUID.randomUUID().toString());
			memberEntity.setPosition(Integer.valueOf(i + 1));
			relayEntity.addMemberEntity(memberEntity);
		}

		return relayEntity;
	}
}
