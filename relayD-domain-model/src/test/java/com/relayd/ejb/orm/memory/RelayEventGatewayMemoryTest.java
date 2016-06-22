package com.relayd.ejb.orm.memory;

import org.junit.Before;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public class RelayEventGatewayMemoryTest extends RelayEventGatewayTest {
	private RelayEventGatewayMemory sut = new RelayEventGatewayMemory();

	@Before
	public void setUp() {
		RelayEventGatewayMemory.events.clear();
	}

	@Override
	public RelayEventGateway getSut() {
		return sut;
	}
}