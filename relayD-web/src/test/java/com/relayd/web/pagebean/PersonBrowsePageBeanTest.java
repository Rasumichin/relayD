package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Position;
import com.relayd.web.bridge.PersonBridge;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonBrowsePageBeanTest {
	@InjectMocks
	private PersonBrowsePageBean sut;

	@Mock
	private PersonBridge personBridge;

	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(0), numberOfResults);
	}

	@Test
	public void testGetNumberOfResults_ForFilledResultList() {
		Mockito.doReturn(createResultList(size(5))).when(personBridge).all();
		sut.getPersons();

		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(5), numberOfResults);
	}

	@Test
	public void testIsRowSelected_ForNotSelectedRow() {
		boolean result = sut.isRowSelected();

		assertFalse("Row should not selected.", result);
	}

	@Test
	public void testIsRowSelected_ForSelectedRow() {
		sut.setSelectedPerson(new PersonBuilder().build());

		boolean result = sut.isRowSelected();

		assertTrue("Row should selected.", result);
	}

	private List<Person> createResultList(Integer aSize) {
		List<Person> result = new ArrayList<Person>();
		PersonBuilder personBuilder = new PersonBuilder();

		for (int i = 0; i < aSize; i++) {
			Person person = personBuilder.build();
			result.add(person);
		}
		return result;
	}

	private Integer size(Integer aValue) {
		return aValue;
	}

	@Test
	public void testSortByRelayname_FirstRelaynameIsNull() {
		Person first = Person.newInstance();
		Person second = Person.newInstance();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", -1, position);
	}

	@Test
	public void testSortByRelayname_SecondRelaynameIsNull() {
		Person first = new PersonBuilder().withRelayname("Die 4 ????").build();

		Person second = Person.newInstance();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 1, position);
	}

	@Test
	public void testSortByRelayname_WithSameName() {
		Person first = new PersonBuilder().withRelayname("A").build();

		Person second = new PersonBuilder().withRelayname("A").build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testSortByRelayname_WithSecondPositionIsNull() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		Person second = new PersonBuilder().withRelayname("A").build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testSortByRelayname_WithBothPositions() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		Person second = new PersonBuilder().withRelayname("A").withPosition(Position.SECOND).build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 2, position);
	}

	@Test
	public void testSortByRelayname_WithBothPositions2() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.SECOND).build();

		Person second = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", -2, position);
	}

}