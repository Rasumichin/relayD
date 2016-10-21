package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.Locale;

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
		PersonEntity exisitingEntity = PersonEntity.newInstance(person.getUUID());
		String expected = person.getUUID().toString();

		sut.mapPersonToEntity(person, exisitingEntity);

		String result = exisitingEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, result);
	}

	@Test
	public void testMapEntityToPerson_id() {
		PersonEntity personEntity = PersonEntity.newInstance();
		String expected = personEntity.getId();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		
		String result = mappedPerson.getUUID().toString();
		assertEquals("Mapping of [id] is not correct!", expected, result);
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
	public void testMapEntityToPerson_forename() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setForename("Steve");
		String expected = personEntity.getForename();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		String result = mappedPerson.getForename().toString();
		assertEquals("Mapping of [forename] is not correct!", expected, result);
	}

	@Test
	public void testMapEntityToPerson_forenameIsNull() {
		PersonEntity personEntity = PersonEntity.newInstance();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		Forename result = mappedPerson.getForename();
		assertNotNull("Mapping of [forename] is not correct!", result);
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
	public void testMapEntityToPerson_surename() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setSurename("Jobs");
		String expected = personEntity.getSurename();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		String result = mappedPerson.getSurename().toString();
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
	public void testMapEntityToPerson_email() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setEmail("me" + Email.AT_SIGN + "mail.com");
		String expected = personEntity.getEmail();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		String result = mappedPerson.getEmail().toString();
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
	public void testMapEntityToPerson_yearOfBirth() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setYearOfBirth(1965);
		Integer expected = personEntity.getYearOfBirth();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		Integer result = mappedPerson.getYearOfBirth().getValue();
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
	public void testMapEntityToPerson_comment() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setComment("Just a remark");
		String expected = personEntity.getComment();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		String result = mappedPerson.getComment().toString();
		assertEquals("Mapping of [comment] is not correct!", expected, result);
	}

	@Test
	public void testMapPersonToEntity_nationality() {
		PersonEntity existingEntity = PersonEntity.newInstance();
		String expected = Locale.FRANCE.toLanguageTag();
		person.setNationality(Locale.FRANCE);

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getNationality();
		assertEquals("Mapping of [nationality] is not correct!", expected, result);
	}

	@Test
	public void testMapEntityToPerson_nationality() {
		PersonEntity personEntity = PersonEntity.newInstance();
		String nationalityValue = Locale.FRANCE.toLanguageTag();
		personEntity.setNationality(nationalityValue);
		String expected = personEntity.getNationality();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		
		String result = mappedPerson.getNationality().toLanguageTag();
		assertEquals("Mapping of [nationality] is not correct!", expected, result);
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
	public void testMapEntityToPerson_relayname() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setRelayname("Fists of Fury");
		String expected = personEntity.getRelayname();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		String result = mappedPerson.getRelayname().toString();
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
	public void testMapEntityToPerson_position() {
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setPos(Integer.valueOf(1));
		Integer expected = personEntity.getPos();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		Integer result = mappedPerson.getPosition().getValue();
		assertEquals("Mapping of [pos] is not correct!", expected, result);
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

	@Test
	public void testMapEntityToPerson_shirtsize() {
		PersonEntity personEntity = PersonEntity.newInstance();
		Integer shirtsizeValue = Shirtsize.HerrenXL.getSize();
		personEntity.setShirtsize(Integer.valueOf(shirtsizeValue));
		Integer expected = personEntity.getShirtsize();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);

		Integer result = mappedPerson.getShirtsize().getSize().intValue();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, result);
	}
}
