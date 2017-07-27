package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.orm.file.RelayEventGatewayFile;
import com.relayd.ejb.orm.jpa.RelayEventGatewayJPA;
import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * Einen Fehler begangen haben und ihn nicht korrigieren: Erst das ist ein Fehler.
 * - Konfuzius
 *
 * @author schmollc
 * @since 17.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventGatewayFactoryTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testGetForRelayEventGatewayMemory() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayMemory.class);
	}

	@Test
	public void testGetForRelayEventGatewayFile() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayFile.class);
	}

	@Test
	public void testGetForRelayEventGatewayJPA() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.JPA);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayJPA.class);
	}
}