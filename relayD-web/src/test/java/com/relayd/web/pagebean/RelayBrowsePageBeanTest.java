package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.web.bridge.RelayBridge;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@RunWith(MockitoJUnitRunner.class)
public class RelayBrowsePageBeanTest {
	@InjectMocks
	private RelayBrowsePageBean sut;

	@Mock
	private RelayBridge relayBridge;

	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(0), numberOfResults);
	}

	//	@Test
	//	public void testGetNumberOfResults_ForFilledResultList() {
	//		Mockito.doReturn(createResultList(size(5))).when(personBridge).all();
	//		sut.getPersons();
	//
	//		Integer numberOfResults = sut.getNumberOfResults();
	//
	//		assertEquals(Integer.valueOf(5), numberOfResults);
	//	}
	//
	//	@Test
	//	public void testIsRowSelected_ForNotSelectedRow() {
	//		boolean result = sut.isRowSelected();
	//
	//		assertFalse("Row should not selected.", result);
	//	}
	//
	//	@Test
	//	public void testIsRowSelected_ForSelectedRow() {
	//		sut.setSelectedPerson(new PersonBuilder().build());
	//
	//		boolean result = sut.isRowSelected();
	//
	//		assertTrue("Row should selected.", result);
	//	}
	//
	//	private List<Person> createResultList(Integer aSize) {
	//		List<Person> result = new ArrayList<Person>();
	//		PersonBuilder personBuilder = new PersonBuilder();
	//
	//		for (int i = 0; i < aSize; i++) {
	//			Person person = personBuilder.build();
	//			result.add(person);
	//		}
	//		return result;
	//	}
	//
	//	private Integer size(Integer aValue) {
	//		return aValue;
	//	}
	//
}
