package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.ejb.orm.memory.PersonGatewayMemory;

public class PersonGatewayFactoryTest {

	@Test
	public void testGetForPersonGatewayMemory() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), PersonGatewayMemory.class);
	}
}