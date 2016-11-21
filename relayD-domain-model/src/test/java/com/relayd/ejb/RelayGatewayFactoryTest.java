package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.orm.file.RelayGatewayFile;
import com.relayd.ejb.orm.jpa.RelayGatewayJPA;
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

	@Test
	public void testGet_ForPersonGatewayFile() {
		RelayGateway instance = RelayGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt!", instance.getClass(), RelayGatewayFile.class);
	}

	@Test
	public void testGet_ForPersonGatewayJPA() {
		RelayGateway instance = RelayGatewayFactory.get(GatewayType.JPA);

		assertEquals("Instance not korrekt!", instance.getClass(), RelayGatewayJPA.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGet_ForPersonGatewayJDBC() {
		RelayGatewayFactory.get(GatewayType.JDBC);
	}
}