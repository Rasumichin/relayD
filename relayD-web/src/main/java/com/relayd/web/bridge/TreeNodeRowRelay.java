package com.relayd.web.bridge;

import com.relayd.Relay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
public class TreeNodeRowRelay extends TreeNodeRow {
	private static final long serialVersionUID = -222411690063227304L;

	private Relay relay; // TODO (Christian, Version 1.3): mit Erik sprechen. Eine Relay hat auch ein NOP?

	TreeNodeRowRelay(Relay aRelay) {
		relay = aRelay;
	}

	@Override
	public Relay getRelay() {
		return relay;
	}

	@Override
	public boolean isRelay() {
		return true;
	}

	// TODO (Christian, Version 1.3): Im Zuge der GUI Darstellung auf String umgestellt. Sollte eher ein Domain/GUI Objekt werden/sein?
	@Override
	public String getRelayname() {
		return relay.toString();
	}
}