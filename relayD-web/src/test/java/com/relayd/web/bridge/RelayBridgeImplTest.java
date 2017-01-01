package com.relayd.web.bridge;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.ejb.GatewayType;

/**
 * If you let the tests rot, then your code will rot too.
 * Keep your tests clean.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @since 14.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayBridgeImplTest {

	private RelayBridgeImpl sut = new RelayBridgeImpl();

	@Test
	public void testGatewayType() {
		GatewayType result = sut.getGatewayType();

		assertEquals("[gatewayType] not correct!", GatewayType.JPA, result);
	}

	@Test
	public void testGetEmailList() {
		Relay dummyRelay = null;
		String actual = sut.getEmailList(dummyRelay);

		String expected = "Not implemented yet!";

		assertEquals(expected, actual);
	}
}