package com.relayd.ejb.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.RelayGatewayTest;

/**
 * If you don't like unit testing your product, most likely your customers won't like to test it either.
 *  - Anonymous
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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