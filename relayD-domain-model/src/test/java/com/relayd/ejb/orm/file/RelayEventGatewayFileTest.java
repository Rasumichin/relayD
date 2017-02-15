package com.relayd.ejb.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;

/**
 * Every large system that works started as a small system that worked.
 *  - Anonymous
 *
 * @author  schmollc
 * @since   10.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventGatewayFileTest extends RelayEventGatewayTest {

	private RelayEventGatewayFile sut = new RelayEventGatewayFile("relayEventTest.relayD");

	@Before
	public void setUp() {
		sut.clear();
	}

	@Override
	public RelayEventGateway getSut() {
		return sut;
	}
}
