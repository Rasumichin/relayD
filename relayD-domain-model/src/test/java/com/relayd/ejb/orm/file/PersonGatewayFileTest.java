package com.relayd.ejb.orm.file;

import org.junit.Before;

import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayTest;

public class PersonGatewayFileTest extends PersonGatewayTest {

	private PersonGatewayFile sut = new PersonGatewayFile("personTest.relayD");

	@Before
	public void setUp() {
		sut.clean();
	}

	@Override
	public PersonGateway getSut() {
		return sut;
	}

}
