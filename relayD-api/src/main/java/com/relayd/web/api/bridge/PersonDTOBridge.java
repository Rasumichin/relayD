package com.relayd.web.api.bridge;

import java.util.List;

import com.relayd.client.jaxb.PersonDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
public interface PersonDTOBridge {
	List<PersonDTO> all();
}
