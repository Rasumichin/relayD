package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.entity.PersonEntity;

/**
 * The value of layers is that each specializes in a particular aspect of a computer program.
 * 		- Eric Evans (Domain-Driven Design) -
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToEntityMapperTest {
	
	private PersonToEntityMapper sut = PersonToEntityMapper.newInstance();
	private Person person = Person.newInstance();
	private PersonEntity personEntity = PersonEntity.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMapPersonToEntity_whenPersonIsNull() {
		sut.mapPersonToEntity(null, personEntity);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMapPersonToEntity_whenPersonEntityIsNull() {
		sut.mapPersonToEntity(person, null);
	}
	
	@Test
	public void testMapPersonToEntity_id() {
		PersonEntity personEntity = PersonEntity.newInstance(person.getUuid());
		String expected = person.getUuid().toString();

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_forename() {
		String expected = "Steve";
		person.setForename(Forename.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_surename() {
		String expected = "Jobs";
		person.setSurename(Surename.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_email() {
		String expected = Email.createFromLocalAndDomainPart("steve.jobs", "apple.com").toString();
		person.setEmail(Email.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getEmail();
		assertEquals("Mapping of [email] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_yearOfBirth() {
		Integer expected = Integer.valueOf(1965);
		person.setYearOfBirth(YearOfBirth.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		Integer actual = personEntity.getYearOfBirth();
		assertEquals("Mapping of [yearOfBirth] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_comment() {
		String expected = "Just a remark";
		person.setComment(Comment.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_relayname() {
		String expected = "Fists of Fury";
		person.setRelayname(Relayname.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_position() {
		Integer expected = Position.FIRST.getValue();
		person.setPosition(Position.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		Integer actual = personEntity.getPos();
		assertEquals("Mapping of [position] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_shirtsize() {
		Integer expected = Shirtsize.HerrenL.getSize();
		person.setShirtsize(Shirtsize.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		Integer actual = personEntity.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, actual);
	}
}
