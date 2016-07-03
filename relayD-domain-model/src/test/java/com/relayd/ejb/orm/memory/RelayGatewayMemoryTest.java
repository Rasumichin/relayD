package com.relayd.ejb.orm.memory;

import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.RelayGatewayTest;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   03.07.2016
 * status   initial
 */
public class RelayGatewayMemoryTest extends RelayGatewayTest {

	private RelayGatewayMemory sut = new RelayGatewayMemory();

	@Override
	public RelayGateway getSut() {
		return sut;
	}
}