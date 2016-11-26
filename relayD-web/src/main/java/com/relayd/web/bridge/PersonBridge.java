package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.GatewayType;
import com.relayd.web.browse.PersonBrowse;

/**
 * This Bridge could handle the REST - Serivce
 * Actual its only a layer for the PersonGateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 * 
 */
public interface PersonBridge {

	List<PersonBrowse> allPersonBrowse();

	List<Person> all();

	void persistPerson(Person person);

	Person get(UUID uuid);

	void remove(Person person);

	ValidationResult validateEMail(Person newPerson);

	String getEmailList(List<Person> somePersons);

	GatewayType getGatewayType();
}