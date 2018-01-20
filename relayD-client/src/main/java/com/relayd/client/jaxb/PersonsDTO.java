package com.relayd.client.jaxb;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
@XmlRootElement(name = "persons")
public class PersonsDTO implements Serializable {
	private static final long serialVersionUID = -7228416984757372589L;
	
	private List<PersonDTO> persons = new ArrayList<>();

	@XmlElement
	public List<PersonDTO> getPersons() {
		return persons;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " []";
	}
}
