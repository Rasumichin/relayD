package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Tests are the Programmer’s stone, transmuting fear into boredom.
 *  - Kent Beck
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayBrowsePageBeanTest {
	private RelayBrowsePageBean sut = new RelayBrowsePageBean();

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}
}