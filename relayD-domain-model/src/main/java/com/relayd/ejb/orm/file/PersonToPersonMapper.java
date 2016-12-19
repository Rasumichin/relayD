package com.relayd.ejb.orm.file;

import com.relayd.Person;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.12.2016
 *
 */
public class PersonToPersonMapper {

	private PersonToPersonMapper() {
	}

	public static PersonToPersonMapper newInstance() {
		return new PersonToPersonMapper();
	}

	public void mapPersonToPerson(Person source, Person target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setYearOfBirth(source.getYearOfBirth());
		target.setSurename(source.getSurename());
		target.setForename(source.getForename());
		target.setShirtsize(source.getShirtsize());
		target.setEmail(source.getEmail());
		target.setComment(source.getComment());
	}
}