package com.relayd.ejb.orm.file;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class PersonGatewayFile implements PersonGateway {

	@Override
	public Person get(UUID uuid) {
		for (Person person : FileWriter.get()) {
			if (uuid.equals(person.getUUID())) {
				return person;
			}
		}
		return null;
	}

	@Override
	public void set(Person aNewPerson) {
		FileWriter.set(aNewPerson);
	}
}