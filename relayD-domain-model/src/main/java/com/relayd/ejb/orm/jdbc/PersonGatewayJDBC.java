package com.relayd.ejb.orm.jdbc;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   15.09.2016
 *
 */
public class PersonGatewayJDBC implements PersonGateway {

	@Override
	public List<Person> getAll() {
		return null;
	}

	@Override
	public Person get(UUID aUuid) {
		return null;
	}

	@Override
	public void set(Person aPerson) {
	}

	@Override
	public void remove(UUID aUuid) {
	}
}