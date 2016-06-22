package com.relayd.ejb.orm.file;

import org.junit.Before;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;

public class RelayEventGatewayFileTest extends RelayEventGatewayTest {
	private RelayEventGatewayFile sut = new RelayEventGatewayFile("TestFile.relayD");

	@Before
	public void setUp() {
		sut.clean();
	}

	@Override
	public RelayEventGateway getSut() {
		return sut;
	}
}
