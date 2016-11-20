package com.relayd.entity;

import static org.junit.Assert.*;

import javax.persistence.EntityTransaction;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * The best way to a breakthrough is constant small improvement - those waiting for the big break are just lazy,
 * they're waiting to be teleported to the top of the hill instead of walking.
 *  - Gary Starkweather
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonEntityIT extends EntityIT {

	@Test
	public void testInsertPersonEntity() {
		PersonEntity sut = PersonEntity.newInstance();
		sut.setForename("Bob");

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		PersonEntity result = getEntityManager().find(PersonEntity.class, sut.getId());
		assertEquals("PersonEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}
}