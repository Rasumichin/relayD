package com.relayd.service;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.ejb.*;
import com.relayd.ejb.orm.file.*;

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

	public Person get(Surename surename) {
		return gateway.get(surename);
	}
}