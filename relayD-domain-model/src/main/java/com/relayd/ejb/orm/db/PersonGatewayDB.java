package com.relayd.ejb.orm.db;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   12.09.2016
 *
 */
public class PersonGatewayDB implements PersonGateway {

	@Override
	public List<Person> getAll() {
		return null;
	}

	@Override
	public Person get(UUID uuid) {
		return null;
	}

	@Override
	public void set(Person person) {
	}

	@Override
	public void remove(UUID uuid) {
	}
}