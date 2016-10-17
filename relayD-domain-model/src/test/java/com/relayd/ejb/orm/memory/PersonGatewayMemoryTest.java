package com.relayd.ejb.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayTest;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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