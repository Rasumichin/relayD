package com.relayd.ejb.orm.file;

import java.util.List;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   21.11.2016
 *
 */
public class RelayGatewayFile implements RelayGateway {

	private RelayToRelayMapper relayToRelayMapper = RelayToRelayMapper.newInstance();

	public RelayGatewayFile() {
	}

	RelayGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relay> getAll() {
		return FileSingleton.getInstance().getRelays();
	}

	@Override
	public void set(Relay updateRelay) {
		List<Relay> someRelays = getAll();

		if (someRelays.contains(updateRelay)) {
			for (Relay eachRelay : someRelays) {
				if (updateRelay.equals(eachRelay)) {
					getRelayToRelayMapper().mapRelayToRelay(updateRelay, eachRelay);
					break;
				}
			}
		} else {
			someRelays.add(updateRelay);
		}
		FileSingleton.getInstance().setRelays(someRelays);

	}

	@Override
	public Relay get(UUID uuid) {
		for (Relay relay : getAll()) {
			if (uuid.equals(relay.getUuid())) {
				return relay;
			}
		}
		return null;
	}

	private RelayToRelayMapper getRelayToRelayMapper() {
		return relayToRelayMapper;
	}
}