package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.GatewayType;

/**
 * Dienen Sie dem Benutzer, nicht weil Sie es müssen, sondern weil Sie es wollen.
 *  - Philip Toshio Sudo
 *
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberBridgeImplTest {

	private MemberBridgeImpl sut = new MemberBridgeImpl();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testGatewayType() {
		GatewayType result = sut.getGatewayType();

		assertEquals("[gatewayType] not correct!", GatewayType.JPA, result);
	}
}