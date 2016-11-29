package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
 * The key to avoiding nervousness is: Be prepared.
 * 		- Patrick McDermott (Zen and the Art of Systems Analysis) -
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToPersonMapperTest {
	
	private EntityToPersonMapper sut = EntityToPersonMapper.newInstance();
	private PersonEntity personEntity = PersonEntity.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMapToPerson_whenPersonIsNull() {
		sut.mapToPerson(null);
	}
	
	@Test
	public void testMapToPerson_id() {
		UUID expected = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expected);
		
		Person person = sut.mapToPerson(personEntity);
		
		UUID actual = person.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToPerson_comment() {
		personEntity.setComment("Just a remark");
		Comment expected = Comment.newInstance(personEntity.getComment());

		Person person = sut.mapToPerson(personEntity);

		Comment actual = person.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToPerson_email() {
		personEntity.setEmail("me" + Email.AT_SIGN + "mail.com");
		Email expected = Email.newInstance(personEntity.getEmail());

		Person person = sut.mapToPerson(personEntity);

		Email actual = person.getEmail();
		assertEquals("Mapping of [email] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToPerson_forename() {
		personEntity.setForename("Steve");
		Forename expected = Forename.newInstance(personEntity.getForename());

		Person person = sut.mapToPerson(personEntity);

		Forename actual = person.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToPerson_shirtsize() {
		Integer shirtsizeValue = Shirtsize.HerrenXL.getSize();
		personEntity.setShirtsize(Integer.valueOf(shirtsizeValue));
		Shirtsize expected = Shirtsize.newInstance(personEntity.getShirtsize());

		Person person = sut.mapToPerson(personEntity);

		Shirtsize actual = person.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, actual);
	}

	@Test
	public void testMapToPerson_surename() {
		personEntity.setSurename("Jobs");
		Surename expected = Surename.newInstance(personEntity.getSurename());

		Person person = sut.mapToPerson(personEntity);

		Surename actual = person.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToPerson_yearOfBirth() {
		personEntity.setYearOfBirth(1965);
		YearOfBirth expected = YearOfBirth.newInstance(personEntity.getYearOfBirth());

		Person person = sut.mapToPerson(personEntity);

		YearOfBirth actual = person.getYearOfBirth();
		assertEquals("Mapping of [yearOfBirth] is not correct!", expected, actual);
	}
}
