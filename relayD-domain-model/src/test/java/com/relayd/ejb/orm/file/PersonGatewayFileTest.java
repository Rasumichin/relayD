package com.relayd.ejb.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayTest;

/**
 * Write your code so that it reflects, or rises above, the best parts of your personal character.
 *  - Daniel Read
 *
 * @author schmollc (Christian@relayd.de)
 * @since 20.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayFileTest extends PersonGatewayTest {

	private PersonGatewayFile sut = new PersonGatewayFile("bigDataTest.relayD");

	@Before
	public void setUp() {
		FileSingleton.getInstance().clear();
	}

	@Override
	public PersonGateway getSut() {
		return sut;
	}
}