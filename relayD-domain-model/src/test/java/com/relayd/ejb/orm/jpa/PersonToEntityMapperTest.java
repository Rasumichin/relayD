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
		String expected = person.getUUID().toString();
		PersonEntity exisitingEntity = new PersonEntity.Builder().withId(expected).build();

		sut.mapPersonToEntity(person, exisitingEntity);

		String result = exisitingEntity.getId();
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
		String expected = "Steve";
		person.setForename(Forename.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getForename();
		assertEquals("Mapping of [forename] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_forenameNullObject() {
		person.setForename(Forename.newInstance(null));
		PersonEntity existingEntity = new PersonEntity.Builder().withForename("Steve").build();

		sut.mapPersonToEntity(person, existingEntity);
		String result = existingEntity.getForename();
		assertNull("Mapping of [forename] is not correct.", result);
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
	public void testMapEntityToPerson_forenameIsNull() {
		PersonEntity personEntity = new PersonEntity.Builder().build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		Forename result = mappedPerson.getForename();
		assertNotNull("Mapping of [forename] is not correct.", result);
	}

	@Test
	public void testMapPersonToEntity_surename() {
		String expected = "Jobs";
		person.setSurename(Surename.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getSurename();
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
		String expected = Email.createFromLocalAndDomainPart("steve.jobs", "apple.com").toString();
		person.setEmail(Email.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getEmail();
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
		Integer expected = Integer.valueOf(1965);
		person.setYearOfBirth(YearOfBirth.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getYearOfBirth();
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
		String expected = "Just a remark";
		person.setComment(Comment.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getComment();
		assertEquals("Mapping of [comment] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_comment() {
		PersonEntity personEntity = new PersonEntity.Builder().withComment("Just a remark").build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getComment();
		String result = mappedPerson.getComment().toString();
		assertEquals("Mapping of [comment] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_nationality() {
		String expected = Locale.FRANCE.toLanguageTag();
		person.setNationality(Locale.FRANCE);
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getNationality();
		assertEquals("Mapping of [nationality] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_nationality() {
		String nationalityValue = Locale.FRANCE.toLanguageTag();
		PersonEntity personEntity = new PersonEntity.Builder().withNationality(nationalityValue).build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getNationality();
		String result = mappedPerson.getNationality().toLanguageTag();
		assertEquals("Mapping of [nationality] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_relayname() {
		String expected = "Fists of Fury";
		person.setRelayname(Relayname.newInstance(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		String result = existingEntity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_relayname() {
		PersonEntity personEntity = new PersonEntity.Builder().withRelayname("Fists of Fury").build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		String expected = personEntity.getRelayname();
		String result = mappedPerson.getRelayname().toString();
		assertEquals("Mapping of [relayname] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_position() {
		Integer expected = Position.FIRST.getValue();
		person.setPosition(Position.decode(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getPos();
		assertEquals("Mapping of [position] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_position() {
		PersonEntity personEntity = new PersonEntity.Builder().withPos(Integer.valueOf(1)).build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		Integer expected = personEntity.getPos();
		Integer result = mappedPerson.getPosition().getValue();
		assertEquals("Mapping of [pos] is not correct.", expected, result);
	}

	@Test
	public void testMapPersonToEntity_shirtsize() {
		Integer expected = Shirtsize.HerrenL.getSize();
		person.setShirtsize(Shirtsize.decode(expected));
		PersonEntity existingEntity = new PersonEntity.Builder().build();

		sut.mapPersonToEntity(person, existingEntity);

		Integer result = existingEntity.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct.", expected, result);
	}

	@Test
	public void testMapEntityToPerson_shirtsize() {
		Integer shirtsizeValue = Shirtsize.HerrenXL.getSize();
		PersonEntity personEntity = new PersonEntity.Builder().withShirtsize(Integer.valueOf(shirtsizeValue)).build();

		Person mappedPerson = sut.mapEntityToPerson(personEntity);
		Integer expected = personEntity.getShirtsize();
		Integer result = mappedPerson.getShirtsize().getSize().intValue();
		assertEquals("Mapping of [shirtsize] is not correct.", expected, result);
	}
}
