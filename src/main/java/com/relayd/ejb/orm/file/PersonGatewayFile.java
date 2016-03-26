package com.relayd.ejb.orm.file;

import com.relayd.Person;
import com.relayd.attributes.Surename;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class PersonGatewayFile implements PersonGateway {

	@Override
	public Person get(Surename vorname) {
		for (Person person : FileWriter.get()) {
			if (vorname.equals(person.getSurename())) {
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
