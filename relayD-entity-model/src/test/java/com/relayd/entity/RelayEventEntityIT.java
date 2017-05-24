package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.persistence.EntityTransaction;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Thus spake the master programmer:
 * “Let the programmers be many and the managers few - then all will be productive.”
 *  - The Tao of Programming
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventEntityIT extends EntityIT {

	@Test
	public void testInsertRelayEventEntity() {
		RelayEventEntity sut = RelayEventEntity.newInstance();
		sut.setEventName("title");
		sut.setEventDay(new Date(System.currentTimeMillis()));

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		RelayEventEntity result = getEntityManager().find(RelayEventEntity.class, sut.getId());
		assertEquals("RelayEventEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}

	@Test
	public void testInsertRelayEventEntity_WithRelay() {
		RelayEventEntity sut = RelayEventEntity.newInstance();
		sut.setEventName("title");
		sut.setEventDay(new Date(System.currentTimeMillis()));

		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setRelayname("Foo Relay");

		sut.addRelay(relayEntity);

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		RelayEventEntity result = getEntityManager().find(RelayEventEntity.class, sut.getId());
		assertEquals("RelayEventEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
		assertFalse("RelayEntites List contains no element!", result.getRelayEntities().isEmpty());
	}

}