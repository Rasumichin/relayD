package com.relayd.web.api.bridge;

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
}
