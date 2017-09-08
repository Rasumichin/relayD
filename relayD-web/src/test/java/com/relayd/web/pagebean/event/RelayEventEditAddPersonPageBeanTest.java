package com.relayd.web.pagebean.event;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	public void testSelectedPersons() {
		List<Person> expected = new ArrayList<>();

		sut.setSelectedPersons(expected);

		List<Person> actual = sut.getSelectedPersons();

		assertEquals("[selectedPersons] nicht korrekt!", expected, actual);
	}
}