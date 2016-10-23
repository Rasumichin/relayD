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
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToEntityMapperTest {
	
	private PersonToEntityMapper sut = PersonToEntityMapper.newInstance();
	private Person person = Person.newInstance();

	@Test
	public void testMapPersonToEntity_id() {
		PersonEntity exisitingEntity = PersonEntity.newInstance(person.getUuid());
		String expected = person.getUuid().toString();

		sut.mapPersonToEntity(person, exisitingEntity);

		String result = exisitingEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_forename() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = "Steve";
		person.setForename(Forename.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_forenameNullObject() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		existingEntity.setForename("Steve");
		person.setForename(Forename.newInstance(null));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getForename();
		assertNull("Mapping of [forename] is not correct!", result);
	}

	@Test
	public void testMapPersonToEntity_surename() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = "Jobs";
		person.setSurename(Surename.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_email() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = Email.createFromLocalAndDomainPart("steve.jobs", "apple.com").toString();
		person.setEmail(Email.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getEmail();
		assertEquals("Mapping of [email] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_yearOfBirth() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		Integer expected = Integer.valueOf(1965);
		person.setYearOfBirth(YearOfBirth.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getYearOfBirth();
		assertEquals("Mapping of [yearOfBirth] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_comment() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = "Just a remark";
		person.setComment(Comment.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_relayname() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = "Fists of Fury";
		person.setRelayname(Relayname.newInstance(expected));

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_position() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		Integer expected = Position.FIRST.getValue();
		person.setPosition(Position.decode(expected));

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getPos();
		assertEquals("Mapping of [position] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_shirtsize() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		Integer expected = Shirtsize.HerrenL.getSize();
		person.setShirtsize(Shirtsize.decode(expected));

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, result);
	}
}
