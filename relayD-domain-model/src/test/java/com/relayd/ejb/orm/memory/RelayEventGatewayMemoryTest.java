package com.relayd.ejb.orm.memory;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;

/**
 * Wenn du den Feind und dich kennst, brauchst du den Ausgang von hundert Tests nicht zu fürchten.
 *  - Sunzi (Sun Tzu)
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 *
 */
public class RelayEventGatewayMemoryTest extends RelayEventGatewayTest {
	private RelayEventGatewayMemory sut = new RelayEventGatewayMemory();

	@Override
	public RelayEventGateway getSut() {
		return sut;
	}
}