package com.relayd.ejb;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   26.03.2016
 *
 */
public interface PersonGateway {
	List<Person> getAll();

	Person get(UUID uuid);

	void set(Person person);

	void remove(UUID uuid);
}