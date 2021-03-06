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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.web.bridge.PersonBridge;

import static org.mockito.Mockito.*;

/**
 * Quality is never an accident; it is always the result of intelligent effort.
 *  - John Ruskin
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 15.06.2016
 *
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
		doNothing().when(sut).showDialog(any(Severity.class), anyString(), anyString());
	}

	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer actual = sut.getNumberOfResults();

		assertEquals("Numboer of results for empty result list not correct!", Integer.valueOf(0), actual);
	}

	@Test
	public void testGetNumberOfResults_ForFilledResultList() {
		doReturn(createResultList(size(5))).when(personBridge).all();
		sut.refreshPersons();

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

	@SuppressWarnings("unchecked")
	@Test
	public void testEmailExport_ForAll() {
		ActionEvent dummyActionEvent = null;

		sut.emailExport(dummyActionEvent);

		verify(personBridge).getEmailList(anyList());
		verify(sut).showDialog(any(Severity.class), anyString(), anyString());
	}

	@Test
	public void testEmailExport_ForSelectedRows() {
		List<Person> selectedPersons = new ArrayList<Person>();
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
		sut.setSelectedPersons(selectedPersons);

		ActionEvent dummyActionEvent = null;

		sut.emailExport(dummyActionEvent);

		verify(personBridge).getEmailList(selectedPersons);
		verify(sut).showDialog(any(Severity.class), anyString(), anyString());
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

	@Test
	public void testShowAll() {
		sut.showAll();
		verify(sut).refreshPersons();
	}

	@Test
	public void testSortByYearOfBirth() {
		YearOfBirth yearOfBirth1 = YearOfBirth.newInstance(1971);
		YearOfBirth yearOfBirth2 = YearOfBirth.newInstance(1973);

		int actual = sut.sortByYearOfBirth(yearOfBirth1, yearOfBirth2);

		assertEquals("[sortByYearOfBirth] not correct!", -2, actual);
	}

	@Test
	public void testSortByEmail() {
		Email email1 = Email.newInstance("Justus.Jonas@RockyBeach.com");
		Email email2 = Email.newInstance("Peter.Shaw@RockyBeach.com");

		int actual = sut.sortByEmail(email1, email2);

		assertEquals("[sortByEmail] not correct!", -6, actual);
	}

	@Test
	public void testSortByForename() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("Peter");

		int actual = sut.sortByForename(name1, name2);

		assertEquals("[sortByForename] not correct!", -6, actual);
	}

	@Test
	public void testSortBySurename() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("Shaw");

		int actual = sut.sortBySurename(name1, name2);

		assertEquals("[sortBySurename] not correct!", -9, actual);
	}

	@Test
	public void testSortByShirtsize() {
		Shirtsize size1 = Shirtsize.DamenL;
		Shirtsize size2 = Shirtsize.HerrenM;

		int actual = sut.sortByShirtsize(size1, size2);

		assertEquals("[sortByShirtsize] not correct!", -4, actual);
	}
}