package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Kein Übel ist so groß wie die Angst davor.
 *  - Seneca
 *
 * @author schmollc (Christian@relayd.de)
 * @since  12.09.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantEntityIT extends EntityIT {
	private PersonEntity personEntity;

	private RelayEventEntity relayEventEntity;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		setUpPersonEntity();
		setUpRelayEventEntity();
	}

	private void setUpPersonEntity() {
		PersonEntity personToBeInserted = PersonEntity.newInstance(UUID.randomUUID());
		persistEntity(personToBeInserted);
		personEntity = getEntityManager().find(PersonEntity.class, personToBeInserted.getId());
	}

	private void setUpRelayEventEntity() {
		relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("Foo Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		persistEntity(relayEventEntity);
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, relayEventEntity.getId());
	}

	@Test
	public void testInsertParticipantEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		sut.setComment("a Comment");
		sut.setShirtsize(7);
		sut.setRelayEventEntity(relayEventEntity);
		sut.setPersonEntity(personEntity);

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		ParticipantEntity result = getEntityManager().find(ParticipantEntity.class, sut.getId());
		assertEquals("ParticipantEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}
}