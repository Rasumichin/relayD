package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * Einen Fehler begangen haben und ihn nicht korrigieren: Erst das ist ein Fehler.
 * - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @since 17.06.2016
 *
 */
public class RelayEventGatewayFactoryTest {

	@Test
	public void testGetForRelayEventGatewayMemory() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayMemory.class);
	}
}