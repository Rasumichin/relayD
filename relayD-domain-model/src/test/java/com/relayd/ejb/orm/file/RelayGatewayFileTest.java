package com.relayd.ejb.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.RelayGatewayTest;

/**
 * There is nothing in the programming field more despicable than an undocumented program.
 *  - Edward Yourdon
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   21.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayGatewayFileTest extends RelayGatewayTest {

	private RelayGatewayFile sut = new RelayGatewayFile("relayTest.relayD");

	@Before
	public void setUp() {
		sut.clear();
	}

	@Override
	public RelayGateway getSut() {
		return sut;
	}
}