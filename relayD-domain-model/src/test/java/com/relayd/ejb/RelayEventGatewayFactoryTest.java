package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 17.06.2016
 * status initial
 */
public class RelayEventGatewayFactoryTest {

	@Test
	public void testGetForRelayEventGatewayMemory() {
		RelayEventGatewayMemory instance = RelayEventGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayMemory.class);
	}
}