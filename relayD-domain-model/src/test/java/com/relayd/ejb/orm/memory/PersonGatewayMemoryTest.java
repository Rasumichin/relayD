package com.relayd.ejb.orm.memory;

import org.junit.Before;

import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayTest;

public class PersonGatewayMemoryTest extends PersonGatewayTest {

	private PersonGatewayMemory sut = new PersonGatewayMemory();

	@Before
	public void setUp() {
		PersonGatewayMemory.events.clear();
	}

	@Override
	public PersonGateway getSut() {
		return sut;
	}
}