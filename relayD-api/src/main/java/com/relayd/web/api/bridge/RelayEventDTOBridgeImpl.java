package com.relayd.web.api.bridge;

import java.util.List;

import com.relayd.client.jaxb.RelayEventDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  11.06.2016
 *
 */
public class RelayEventDTOBridgeImpl implements RelayEventDTOBridge {

	@Override
	public List<RelayEventDTO> all() {
		return RelayEventDTO.getRandomEvents();
	}
}
