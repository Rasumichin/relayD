package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
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

	@Mock
	private PersonSort personSort;

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
	public void testEmailExport_ForAll() {
		ActionEvent dummyActionEvent = null;

		sut.emailExport(dummyActionEvent);

		verify(personBridge).getEmailList(anyList());
		verify(sut).showMessage(any(Severity.class), anyString(), anyString());
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

	@Test
	public void testShowAll() {
		sut.showAll();
		verify(sut).refreshPersons();
	}

	@Test
	public void testShowRelaysWithSpace() {
		sut.showRelaysWithSpace();
		verify(personBridge).relaysWithSpace();
	}

	@Test
	public void testShowAllWithoutRelay() {
		sut.showAllWithoutRelay();
		verify(personBridge).allWithoutRelay();
	}

	@Test
	public void testSortByBirthday() {
		Birthday birthday1 = Birthday.newInstance(LocalDate.of(2016, Calendar.AUGUST, 23));
		Birthday birthday2 = Birthday.newInstance(LocalDate.of(2016, Calendar.AUGUST, 25));

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortByBirthday(birthday1, birthday2);

		verify(personSort).sortByBirthday(birthday1, birthday2);
	}

	@Test
	public void testSortByEmail() {
		Email email1 = Email.newInstance("Justus.Jonas@RockyBeach.com");
		Email email2 = Email.newInstance("Peter.Shaw@RockyBeach.com");

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortByEmail(email1, email2);

		verify(personSort).sortByEmail(email1, email2);
	}

	@Test
	public void testSortByForename() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("Peter");

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortByForename(name1, name2);

		verify(personSort).sortByForename(name1, name2);
	}

	@Test
	public void testSortBySurename() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("Shaw");

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortBySurename(name1, name2);

		verify(personSort).sortBySurename(name1, name2);
	}

	@Test
	public void testSortByRelayname() {
		Person person1 = new PersonBuilder().withRelayname("A").build();
		Person person2 = new PersonBuilder().withRelayname("B").build();

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortByRelayname(person1, person2);

		verify(personSort).sortByRelayname(person1, person2);
	}

	@Test
	public void testSortByshirtsize() {
		Shirtsize size1 = Shirtsize.DamenL;
		Shirtsize size2 = Shirtsize.HerrenM;

		@SuppressWarnings("unused")
		int resultForDocumentation = sut.sortByShirtsize(size1, size2);

		verify(personSort).sortByShirtsize(size1, size2);
	}

}