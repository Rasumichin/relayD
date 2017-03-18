package com.relayd.ejb.orm.jpa;

import com.relayd.Relay;
import com.relayd.entity.RelayEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.11.2016
 *
 */
public class RelayToEntityMapper {

	private RelayToEntityMapper() {
	}

	public static RelayToEntityMapper newInstance() {
		return new RelayToEntityMapper();
	}

	public void mapRelayToEntity2(Relay relay, RelayEntity relayEntity) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'!");
		}
		if (relayEntity == null) {
			throw new IllegalArgumentException("[relayEntity] must not be 'null'!");
		}
		if (relayEntity.getRelayEventEntity() == null) {
			throw new IllegalStateException("[relayEventEntity] must not be 'null' at this point!");
		}

		relayEntity.setRelayname(relay.getRelayname().isEmpty() ? null : relay.getRelayname().toString());
	}
}