package com.relayd.ejb.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   26.03.2016
 * status   initial
 */
public class PersonGatewayFile implements PersonGateway {

	private String fileName = "person.relayD";

	public PersonGatewayFile() {
		initFile();
	}

	PersonGatewayFile(String aFileName) {
		setFileName(aFileName);
		initFile();
	}

	private void initFile() {
		File file = new File(getFileName());
		if (!file.exists()) {
			List<Person> persons = new ArrayList<>();
			try {
				put(persons);
			} catch (IOException e) {
				throw new RuntimeException("Error", e);
			}
		}
	}

	public void clean() {
		try {
			put(new ArrayList<>());
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}
	}

	private void put(List<Person> somePersons) throws IOException {
		FileOutputStream fileOutputStream;
		fileOutputStream = new FileOutputStream(getFileName());
		SerializationUtils.serialize((Serializable) somePersons, fileOutputStream);
		fileOutputStream.close();
	}

	@Override
	public Person get(UUID uuid) {
		for (Person person : getAll()) {
			if (uuid.equals(person.getUUID())) {
				return person;
			}
		}
		return null;
	}

	@Override
	public void set(Person updatePerson) {
		List<Person> somePersons = getAll();

		if (somePersons.contains(updatePerson)) {
			for (Person person : somePersons) {
				if (updatePerson.equals(person)) {
					// TODO -schmollc- Mapper einbauen
					person.setYearOfBirth(updatePerson.getYearOfBirth());
					person.setSurename(updatePerson.getSurename());
					person.setForename(updatePerson.getForename());
					person.setNationality(updatePerson.getNationality());
					person.setShirtsize(updatePerson.getShirtsize());
					person.setEmail(updatePerson.getEmail());
					person.setRelayname(updatePerson.getRelayname());
					person.setPosition(updatePerson.getPosition());
					person.setComment(updatePerson.getComment());
					break;
				}
			}
		} else {
			somePersons.add(updatePerson);
		}

		try {
			put(somePersons);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}

	}

	@Override
	public void remove(UUID uuid) {
		Person dummyRelayEvent = Person.newInstance();
		dummyRelayEvent.setUUID(uuid);
		List<Person> all = getAll();
		all.remove(dummyRelayEvent);
		try {
			put(all);
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		FileInputStream fileInputStream;
		List<Person> relays = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(getFileName());
			relays = (ArrayList<Person>) SerializationUtils.deserialize(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return relays;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String aFileName) {
		fileName = aFileName;
	}
}