package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
public class RelayBridgeMock implements RelayBridge {

	@Override
	public List<Relay> all() {
		List<Relay> result = new ArrayList<Relay>();

		for (int i = 0; i < 5; i++) {
			Relay relay = Relay.newInstance();
			relay.setRelayname(Relayname.newInstance("Relay nummer:" + (i + 1)));
			result.add(relay);
		}
		return result;
	}
}
