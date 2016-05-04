package com.relayd.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 04.05.2016
 * status initial
 */
public class RelayEventEntityTest {

	@Test
	public void testConstructorWithValidTitle() {
		String title = "Marathon 2017";

		RelayEventEntity entity = new RelayEventEntity.Builder(title).build();

		assertNotNull("Erwarte gueltige Instanz.", entity);
		assertNotNull("Erwarte gueltige [id].", entity.getId());
		assertEquals("[title] nicht korrekt.", title, entity.getTitle());
		assertNull("Erwarte null fuer [yearHappened].", entity.getYearHappened());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithInvalidTitle() {
		String title = null;

		new RelayEventEntity.Builder(title).build();
	}

	@Test
	public void testConstructorWithValidYear() {
		String title = "Marathon 2018";
		Integer yearHappened = 2018;

		RelayEventEntity entity = new RelayEventEntity.Builder(title).withYearHappened(yearHappened).build();

		assertNotNull("Erwarte gueltige Instanz.", entity);
		assertNotNull("Erwarte gueltige [id].", entity.getId());
		assertEquals("[title] nicht korrekt.", title, entity.getTitle());
		assertEquals("[yearHappend] nicht korrekt.", yearHappened, entity.getYearHappened());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithInvalidYear() {
		String title = "Marathon 2014";
		Integer yearHappened = 2014;

		new RelayEventEntity.Builder(title).withYearHappened(yearHappened).build();
	}
}