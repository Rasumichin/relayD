package com.relayd.entity;

import static org.junit.Assert.*;

import javax.persistence.EntityTransaction;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Gesundheit erhalten statt Krankheit zu bekämpfen.
 * Guten Code schreiben statt unsauberen zu refactoren.
 *  - Tibetische Weisheit
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEntityIT extends EntityIT {

	@Test
	public void testInsertRelayEntity() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setRelayname("Staubwolke");

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		RelayEntity result = getEntityManager().find(RelayEntity.class, sut.getId());
		assertEquals("RelayEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}
}