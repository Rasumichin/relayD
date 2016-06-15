package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.service.PersonService;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonBrowsePageBeanTest {
	@InjectMocks
	private PersonBrowsePageBean sut;

	@Mock
	private PersonService personService;

	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(0), numberOfResults);
	}

	@Test
	public void testGetNumberOfResults_ForFilledResultList() {
		Mockito.doReturn(createResultList(size(5))).when(personService).get();
		sut.search();

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
		sut.setSelected(new PersonBuilder().build());

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
}