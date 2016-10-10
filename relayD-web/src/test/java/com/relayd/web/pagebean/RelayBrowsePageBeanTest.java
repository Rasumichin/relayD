package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Relay;
import com.relayd.web.bridge.RelayBridge;

import static org.mockito.Mockito.*;

/**
 * Tests are the Programmer’s stone, transmuting fear into boredom.
 *  - Kent Beck
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayBrowsePageBeanTest {
	@InjectMocks
	private RelayBrowsePageBean sut = new RelayBrowsePageBean();

	@Mock
	private RelayBridge relayBridge;

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer actual = sut.getNumberOfResults();

		assertEquals("Numboer of results for empty result list not correct!", Integer.valueOf(0), actual);
	}

	@Test
	public void testGetNumberOfResults_ForFilledResultList() {
		doReturn(createResultList(size(5))).when(relayBridge).all();
		sut.refreshRelays();

		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(5), numberOfResults);
	}

	private List<Relay> createResultList(Integer aSize) {
		List<Relay> result = new ArrayList<Relay>();

		for (int i = 0; i < aSize; i++) {
			Relay relay = Relay.newInstance();
			result.add(relay);
		}
		return result;
	}

	private Integer size(Integer aValue) {
		return aValue;
	}
}