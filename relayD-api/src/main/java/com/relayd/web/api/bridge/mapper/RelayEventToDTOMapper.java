package com.relayd.web.api.bridge.mapper;

import com.relayd.RelayEvent;
import com.relayd.client.jaxb.RelayEventDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  11.06.2016
 *
 */
public class RelayEventToDTOMapper {

	private RelayEventToDTOMapper() {
	}

	public static RelayEventToDTOMapper newInstance() {
		return new RelayEventToDTOMapper();
	}

	public void mapRelayEventToDTO(RelayEvent relayEvent, RelayEventDTO relayEventDTO) {
		if (relayEvent == null) {
			throw new IllegalArgumentException("[relayEvent] must not be 'null'.");
		}
		if (relayEventDTO == null) {
			throw new IllegalArgumentException("[relayEventDTO] must not be 'null'.");
		}
		
		relayEventDTO.setId(relayEvent.getUuid().toString());
		relayEventDTO.setName(relayEvent.getName().toString());
		relayEventDTO.setEventDay(relayEvent.getEventDay().getValue());
		relayEventDTO.setNumberOfRelays(relayEvent.getRelays().size());
	}
}
