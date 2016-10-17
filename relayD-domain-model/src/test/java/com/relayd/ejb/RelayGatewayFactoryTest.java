package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.orm.memory.RelayGatewayMemory;

/**
 * The unit tests are documents.
 * They describe the lowest-level design of the system.
 *  - Robert C. Martin
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayGatewayFactoryTest {

	@Test
	public void testGet_ForPersonGatewayMemory() {
		RelayGateway instance = RelayGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt!", instance.getClass(), RelayGatewayMemory.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGet_ForPersonGatewayFile() {
		RelayGatewayFactory.get(GatewayType.FILE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGet_ForPersonGatewayJPA() {
		RelayGatewayFactory.get(GatewayType.JPA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGet_ForPersonGatewayJDBC() {
		RelayGatewayFactory.get(GatewayType.JDBC);
	}
}