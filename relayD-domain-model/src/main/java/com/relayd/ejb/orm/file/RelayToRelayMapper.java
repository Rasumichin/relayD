package com.relayd.ejb.orm.file;

import com.relayd.Relay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 28.12.2016
 *
 */
public class RelayToRelayMapper {

	private RelayToRelayMapper() {
	}

	public static RelayToRelayMapper newInstance() {
		return new RelayToRelayMapper();
	}

	public void mapRelayToRelay(Relay source, Relay target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}
		target.setRelayname(source.getRelayname());
	}
}