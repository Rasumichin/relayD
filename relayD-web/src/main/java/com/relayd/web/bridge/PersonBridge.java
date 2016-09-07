package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;

/**
 * This Bridge could handle the REST - Serivce
 * Actual its only a layer for the PersonGateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public interface PersonBridge {

	List<Person> all();

	List<Person> allWithoutRelay();

	void update(Person person);

	void create(Person person);

	Person get(UUID uuid);

	void remove(Person person);

	ValidationResult validateEMail(Person newPerson);

	String getEmailList();

	String getEmailList(List<Person> somePersons);
}