package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Tue nichts, das nutzlos ist.
 *  - Miyamoto Musashi
 *
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantEditPageBeanTest {

	private ParticipantEditPageBean sut = new ParticipantEditPageBean();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}
}