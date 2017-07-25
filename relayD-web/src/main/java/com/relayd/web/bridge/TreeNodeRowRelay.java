package com.relayd.web.bridge;

import com.relayd.Relay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
public class TreeNodeRowRelay extends TreeNodeRow {
	private static final long serialVersionUID = -222411690063227304L;

	private Relay relay; // TODO - REL-280 - Mit Erik sprechen. Eine Relay hat auch ein NOP?

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

	// TODO - REL-281 - Im Zuge der GUI Darstellung auf String umgestellt. Sollte eher ein Domain/GUI Objekt werden/sein?
	@Override
	public String getRelayname() {
		return relay.toString();
	}

	@Override
	public String getDuration() {
		return relay.getDurationFormatted();
	}

	@Override
	public String getStatus() {
		int relayMembers = relay.memberCount().intValue();
		if (relayMembers == 0) {
			return "ui-icon ui-icon-help";
		} else if (relayMembers == 4) {
			return "ui-icon ui-icon-check";
		}
		return "ui-icon ui-icon-notice";
	}
}