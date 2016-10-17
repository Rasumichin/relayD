package com.relayd.ejb.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayTest;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 20.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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