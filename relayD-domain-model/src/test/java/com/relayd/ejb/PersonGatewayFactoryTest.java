package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.orm.file.PersonGatewayFile;
import com.relayd.ejb.orm.jpa.PersonGatewayJPA;
import com.relayd.ejb.orm.memory.PersonGatewayMemory;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayFactoryTest {

	@Test
	public void testGet_ForPersonGatewayMemory() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt!", instance.getClass(), PersonGatewayMemory.class);
	}

	@Test
	public void testGet_ForPersonGatewayFile() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt!", instance.getClass(), PersonGatewayFile.class);
	}

	@Test
	public void testGet_ForPersonGatewayJPA() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.JPA);

		assertEquals("Instance not korrekt!", instance.getClass(), PersonGatewayJPA.class);
	}
}