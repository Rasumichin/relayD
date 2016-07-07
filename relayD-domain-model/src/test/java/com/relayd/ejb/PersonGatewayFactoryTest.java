package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.ejb.orm.file.PersonGatewayFile;
import com.relayd.ejb.orm.memory.PersonGatewayMemory;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 * status   initial
 */
public class PersonGatewayFactoryTest {

	@Test
	public void testGetForPersonGatewayMemory() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), PersonGatewayMemory.class);
	}

	@Test
	public void testGetForPersonGatewayFile() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt.", instance.getClass(), PersonGatewayFile.class);
	}

}