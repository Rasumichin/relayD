package com.relayd.ejb.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.ParticipantGateway;
import com.relayd.ejb.ParticipantGatewayTest;

/**
 * Du musst verstehen, dass es mehr als einen Weg zur Spitze des Berges gibt.
 *  - Miyamoto Musashi
 *
 * @author schmollc (Christian@relayD.de)
 * @since 16.10.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantGatewayMemoryTest extends ParticipantGatewayTest {

	private ParticipantGatewayMemory sut = new ParticipantGatewayMemory();

	@Override
	@Before
	public void setUp() {
		MemorySingleton.getInstance().getMembers().clear();
		super.setUp();
	}

	@Override
	public ParticipantGateway getSut() {
		return sut;
	}
}