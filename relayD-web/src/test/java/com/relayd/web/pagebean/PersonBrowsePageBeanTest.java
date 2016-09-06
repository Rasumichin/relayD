package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage.Severity;
import javax.faces.event.ActionEvent;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.web.bridge.PersonBridge;

import static org.mockito.Mockito.*;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonBrowsePageBeanTest {
	@InjectMocks
	@Spy
	private PersonBrowsePageBean sut;

	@Mock
	private PersonBridge personBridge;

	@Mock
	private PersonEditPageBean personEditPageBean;

	@Before
	public void setUp() {
		doNothing().when(sut).showMessage(any(Severity.class), anyString(), anyString());
	}

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
	public void testIsRowSelected_ForNonSelectedRow() {
		boolean result = sut.isRowSelectedForOneRow();

		assertFalse("Row should not selected!", result);
	}

	@Test
	public void testIsRowSelected_ForOneSelectedRow() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().build());

		sut.setSelectedPersons(selectedPersons);

		boolean result = sut.isRowSelectedForOneRow();

		assertTrue("Row should selected.", result);
	}

	@Test
	public void testIsRowSelected_ForTwoSelectedRows() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
		sut.setSelectedPersons(selectedPersons);

		boolean result = sut.isRowSelectedForOneRow();

		assertFalse("Not Valid for 2 Rows!", result);
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

	@Test
	public void testEmailExport() {
		sut.emailExport(null);
		verify(personBridge).getEmailList();
		verify(sut).showMessage(any(Severity.class), anyString(), anyString());

	}

	@Test
	public void testEditRow_ForNonSelectedRow() {
		ActionEvent dummyActionEvent = null;

		sut.edit(dummyActionEvent);

		verify(personEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut).showMessageErrorNoRowSelected();
	}

	@Test
	public void testEditRow_ForOneSelectedRow() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().build());

		sut.setSelectedPersons(selectedPersons);

		ActionEvent dummyActionEvent = null;

		sut.edit(dummyActionEvent);

		verify(personEditPageBean).openDialogFor(any(UUID.class));
		verify(sut, never()).showMessageErrorNoRowSelected();

	}

	@Test
	public void testEditRow_ForTwoSelectedRows() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
		sut.setSelectedPersons(selectedPersons);

		ActionEvent dummyActionEvent = null;

		sut.edit(dummyActionEvent);

		verify(personEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut).showMessageErrorNoRowSelected();
	}

	@Test
	public void testRemoveRow_ForNonSelectedRow() {
		ActionEvent dummyActionEvent = null;

		sut.remove(dummyActionEvent);

		verify(personBridge, never()).remove(any(Person.class));
		verify(sut, never()).refreshPersons();
		verify(sut).showMessageErrorNoRowSelected();
	}

	@Test
	public void testRemoveRow_ForOneSelectedRow() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().build());
		sut.setSelectedPersons(selectedPersons);
		ActionEvent dummyActionEvent = null;

		sut.remove(dummyActionEvent);

		verify(personBridge).remove(any(Person.class));
		verify(sut).showMessage(any(Severity.class), anyString(), anyString());
		verify(sut).refreshPersons();
	}

	@Test
	public void testRemoveRow_ForTwoSelectedRows() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
		sut.setSelectedPersons(selectedPersons);
		ActionEvent dummyActionEvent = null;

		sut.remove(dummyActionEvent);

		verify(personBridge, never()).remove(any(Person.class));
		verify(sut, never()).refreshPersons();
		verify(sut).showMessageErrorNoRowSelected();
	}
}