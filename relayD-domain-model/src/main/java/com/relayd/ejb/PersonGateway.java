package com.relayd.ejb;

import java.util.UUID;

import com.relayd.Person;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public interface PersonGateway {
	Person get(UUID uuid);

	void set(Person person);
}
