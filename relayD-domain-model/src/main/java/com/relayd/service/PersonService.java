package com.relayd.service;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.orm.file.PersonGatewayFile;

/**
 * Diese Klasse befindt sich noch in der Businessschicht und leitet die
 * Persistenzanfragen weiter.
 *
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class PersonService {
	private PersonGateway gateway = null;

	private PersonService() {
		super();
		// das wird dann spaeter per Injection oder FactoryPattern entsprechend geholt.
		gateway = new PersonGatewayFile();
	}

	public static PersonService newInstance() {
		return new PersonService();
	}

	public void set(Person aNewPerson) {
		gateway.set(aNewPerson);
	}

	public Person get(UUID uuid) {
		return gateway.get(uuid);
	}

	public List<Person> get() {
		// TODO -schmollc- Implementierung in dem PersonGateway nachholen.
		throw new IllegalAccessError("Not Supported yet!");
	}
}