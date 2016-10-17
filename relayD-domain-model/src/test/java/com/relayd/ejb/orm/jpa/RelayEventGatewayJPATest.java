package com.relayd.ejb.orm.jpa;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;

/**
 * Die Tat ist alles, nichts der Ruhm.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventGatewayJPATest extends RelayEventGatewayTest {
	private RelayEventGatewayJPA sut = new RelayEventGatewayJPA();

	@Override
	public RelayEventGateway getSut() {
		return sut;
	}
}