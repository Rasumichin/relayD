package com.relayd.ejb.orm.memory;

import org.junit.Before;

import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.RelayGatewayTest;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   03.07.2016
 * status   initial
 */
public class RelayGatewayMemoryTest extends RelayGatewayTest {

	private RelayGatewayMemory sut = new RelayGatewayMemory();

	@Before
	public void setUp() {
		RelayGatewayMemory.relays.clear();
	}

	@Override
	public RelayGateway getSut() {
		return sut;
	}
}