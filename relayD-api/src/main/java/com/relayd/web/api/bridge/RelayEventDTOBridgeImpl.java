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
	
	RelayEventGateway getGateway() {
		// TODO (Erik Lotz): Answer gateway based on Settings.
		return RelayEventGatewayFactory.get(GatewayType.JPA);
	}

	@Override
	public List<RelayEventDTO> all() {
		List<RelayEvent> relayEvents = getGateway().getAll();
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
