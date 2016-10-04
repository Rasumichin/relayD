package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.relayd.ejb.orm.jpa.RelayEventGatewayJPA;
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

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testGetForRelayEventGatewayMemory() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayMemory.class);
	}

	@Test
	public void testGetForRelayEventGatewayFile() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[FILE] is unsupported Gateway Type.");

		RelayEventGatewayFactory.get(GatewayType.FILE);
	}

	@Test
	public void testGetForRelayEventGatewayJPA() {
		RelayEventGateway instance = RelayEventGatewayFactory.get(GatewayType.JPA);

		assertEquals("Instance not korrekt.", instance.getClass(), RelayEventGatewayJPA.class);
	}

	@Test
	public void testGetForRelayEventGatewayJDBC() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[JDBC] is unsupported Gateway Type.");

		RelayEventGatewayFactory.get(GatewayType.JDBC);
	}
}