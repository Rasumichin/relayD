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
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		UUID expected = person.getUUID();
		UUID result = UUID.fromString(mappedPersonEntity.getId());
		assertEquals("Mapping of [uuid] is not correct.", expected, result);
	}
	
	@Test
	public void testMapEntityToPerson_id() {
		PersonEntity personEntity = new PersonEntity.Builder().build();
		
		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getId();
		String result = mappedPerson.getUUID().toString();
		assertEquals("Mapping of [id] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_forename() {
		person.setForename(Forename.newInstance("Steve"));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Forename expected = person.getForename();
		Forename result = Forename.newInstance(mappedPersonEntity.getForename());
		assertEquals("Mapping of [forename] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_forename() {
		PersonEntity personEntity = new PersonEntity.Builder().withForename("Steve").build();
		
		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getForename();
		String result = mappedPerson.getForename().toString();
		assertEquals("Mapping of [forename] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_surename() {
		person.setSurename(Surename.newInstance("Jobs"));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Surename expected = person.getSurename();
		Surename result = Surename.newInstance(mappedPersonEntity.getSurename());
		assertEquals("Mapping of [surename] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_surename() {
		PersonEntity personEntity = new PersonEntity.Builder().withSurename("Jobs").build();
		
		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getSurename();
		String result = mappedPerson.getSurename().toString();
		assertEquals("Mapping of [surename] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_email() {
		person.setEmail(Email.createFromLocalAndDomainPart("steve.jobs", "apple.com"));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Email expected = person.getEmail();
		Email result = Email.newInstance(mappedPersonEntity.getEmail());
		assertEquals("Mapping of [email] is not correct.", expected, result);
	}


	@Test
	public void testMapEntityToPerson_email() {
		PersonEntity personEntity = new PersonEntity.Builder().withEmail("me" + Email.AT_SIGN + "mail.com").build();
		
		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getEmail();
		String result = mappedPerson.getEmail().toString();
		assertEquals("Mapping of [email] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_yearOfBirth() {
		person.setYearOfBirth(YearOfBirth.newInstance(1965));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		YearOfBirth expected = person.getYearOfBirth();
		YearOfBirth result = YearOfBirth.newInstance(mappedPersonEntity.getYearOfBirth());
		assertEquals("Mapping of [yearOfBirth] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_yearOfBirth() {
		PersonEntity personEntity = new PersonEntity.Builder().withYearOfBirth(1965).build();
		
		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		Integer expected = personEntity.getYearOfBirth();
		Integer result = mappedPerson.getYearOfBirth().getValue();
		assertEquals("Mapping of [yearOfBirth] is not correct.", expected, result);
	}
	
	@Test
	public void testMapPersonToEntity_comment() {
		person.setComment(Comment.newInstance("Just a remark"));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Comment expected = person.getComment();
		Comment result = Comment.newInstance(mappedPersonEntity.getComment());
		assertEquals("Mapping of [comment] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_nationality() {
		person.setNationality(Locale.FRANCE);
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Locale expected = person.getNationality();
		Locale result = new Locale.Builder().setLanguageTag(mappedPersonEntity.getNationality()).build();
		assertEquals("Mapping of [nationality] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_relayname() {
		person.setRelayname(Relayname.newInstance("Four are not a Fist"));
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Relayname expected = person.getRelayname();
		Relayname result = Relayname.newInstance(mappedPersonEntity.getRelayname());
		assertEquals("Mapping of [relayname] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_position() {
		person.setPosition(Position.FIRST);
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Position expected = person.getPosition();
		Position result = Position.decode(mappedPersonEntity.getPos());
		assertEquals("Mapping of [position] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_shirtsize() {
		person.setShirtsize(Shirtsize.HerrenL);
		
		PersonEntity mappedPersonEntity = sut.mapPersonToEntity(person);
		Shirtsize expected = person.getShirtsize();
		Shirtsize result = Shirtsize.decode(mappedPersonEntity.getShirtsize().shortValue());
		assertEquals("Mapping of [shirtsize] is not correct.", expected, result);
	}
}
