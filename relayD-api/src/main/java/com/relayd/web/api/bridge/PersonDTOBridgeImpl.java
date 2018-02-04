package com.relayd.web.api.bridge;

import java.util.List;

import com.relayd.client.jaxb.PersonDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
public class PersonDTOBridgeImpl implements PersonDTOBridge {

	private PersonDTOBridgeImpl() {
	}

	public static PersonDTOBridgeImpl newInstance() {
		return new PersonDTOBridgeImpl();
	}

	@Override
	public List<PersonDTO> all() {
		return null;
	}
}
