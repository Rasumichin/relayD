package com.relayd.web.api.bridge;

import java.util.*;

import com.relayd.RelayEvent;
import com.relayd.client.jaxb.RelayEventDTO;
import com.relayd.ejb.*;
import com.relayd.web.api.bridge.mapper.RelayEventToDTOMapper;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  11.06.2016
 *
 */
public class RelayEventDTOBridgeImpl implements RelayEventDTOBridge {
	RelayEventGateway relayEventGateway;
	
	private RelayEventDTOBridgeImpl(RelayEventGateway gateway) {
		relayEventGateway = gateway;
	}

	public static RelayEventDTOBridge newInstance(RelayEventGateway gateway) {
		if (gateway == null) {
			throw new IllegalArgumentException("[gateway] must not be 'null'.");
		}
		
		return new RelayEventDTOBridgeImpl(gateway);
	}

	RelayEventGateway getRelayEventGateway() {
		return relayEventGateway;
	}

	@Override
	public List<RelayEventDTO> all() {
		List<RelayEvent> relayEvents = getRelayEventGateway().getAll();
		RelayEventToDTOMapper mapper = RelayEventToDTOMapper.newInstance();
		List<RelayEventDTO> result = new ArrayList<>();
		
		relayEvents.forEach(eachRelayEvent -> {
			RelayEventDTO eventDTO = RelayEventDTO.newInstance();
			mapper.mapRelayEventToDTO(eachRelayEvent, eventDTO);
			result.add(eventDTO);
		});
		
		return result;
	}
}
