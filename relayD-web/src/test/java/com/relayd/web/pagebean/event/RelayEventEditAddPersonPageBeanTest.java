package com.relayd.web.pagebean.event;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Person;

/**
 * Ein guter Diener aber ein schlechter Meister
 *  - Alan Watts
 *
 * @author schmollc
 * @since 27.07.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventEditAddPersonPageBeanTest {
	private RelayEventEditAddPersonPageBean sut = new RelayEventEditAddPersonPageBean();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testSelectedPerson() {
		Person expected = Person.newInstance();

		sut.setSelectedPerson(expected);

		Person actual = sut.getSelectedPerson();

		assertEquals("[selectedPerson] nicht korrekt!", expected, actual);
	}
}