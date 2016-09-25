package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.09.2016
 *
 */
public class PersonToEntityMapperTest {
	private PersonToEntityMapper sut = PersonToEntityMapper.newInstance();
	private Person person = Person.newInstance();

	@Test
	public void testMapPersonToEntity_id() {
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		UUID expected = person.getUUID();
		UUID result = UUID.fromString(personEntity.getId());
		assertEquals("Mapping of [uuid] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_forename() {
		person.setForename(Forename.newInstance("Steve"));
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		Forename expected = person.getForename();
		Forename result = Forename.newInstance(personEntity.getForename());
		assertEquals("Mapping of [forename] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_surename() {
		person.setSurename(Surename.newInstance("Jobs"));
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		Surename expected = person.getSurename();
		Surename result = Surename.newInstance(personEntity.getSurename());
		assertEquals("Mapping of [surename] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_email() {
		person.setEmail(Email.createFromLocalAndDomainPart("steve.jobs", "apple.com"));
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		Email expected = person.getEmail();
		Email result = Email.newInstance(personEntity.getEmail());
		assertEquals("Mapping of [email] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_yearOfBirth() {
		person.setYearOfBirth(YearOfBirth.newInstance(1965));
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		YearOfBirth expected = person.getYearOfBirth();
		YearOfBirth result = YearOfBirth.newInstance(personEntity.getYearOfBirth());
		assertEquals("Mapping of [yearOfBirth] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_comment() {
		person.setComment(Comment.newInstance("Just a remark"));
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		Comment expected = person.getComment();
		Comment result = Comment.newInstance(personEntity.getComment());
		assertEquals("Mapping of [comment] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_nationality() {
		person.setNationality(Locale.FRANCE);
		
		PersonEntity personEntity = sut.mapPersonToEntity(person);
		Locale expected = person.getNationality();
		Locale result = new Locale.Builder().setLanguageTag(personEntity.getNationality()).build();
		assertEquals("Mapping of [nationality] is not correct.", expected, result);
	}
}
