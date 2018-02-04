package com.relayd.web.api.bridge.mapper;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.client.jaxb.PersonDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  04.02.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToDTOMapperTest {
	private PersonToDTOMapper sut = PersonToDTOMapper.newInstance();

	@Test
	public void testNewInstance() {
		PersonToDTOMapper object = PersonToDTOMapper.newInstance();
		
		assertNotNull("Instance creation was not correct!", object);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMapPersonToDTO_person_is_null() {
		sut.mapPersonToDTO(null);
	}

	@Test
	public void testMapPersonToDTO_dto_is_not_null() {
		Person person = Person.newInstance();
		
		PersonDTO object = sut.mapPersonToDTO(person);
		
		assertNotNull("Mapping of 'person' was not correct!", object);
	}

	@Test
	public void testMapPersonToDTO_id() {
		Person person = Person.newInstance();
		String expected = person.getUuid().toString();
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getId();
		assertEquals("Mapping of 'person' (id) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_forename() {
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Ruby"));
		
		String expected = "Ruby";
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getForename();
		assertEquals("Mapping of 'person' (forename) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_surename() {
		Person person = Person.newInstance();
		person.setSurename(Surename.newInstance("Smithee"));
		
		String expected = "Smithee";
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getSurename();
		assertEquals("Mapping of 'person' (surename) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_email() {
		Person person = Person.newInstance();
		person.setEmail(Email.newInstance("stranger.things@is.great"));
		
		String expected = "stranger.things@is.great";
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getEmail();
		assertEquals("Mapping of 'person' (email) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_shirtsize() {
		Person person = Person.newInstance();
		person.setShirtsize(Shirtsize.DamenM);
		
		String expected = "Damen M";
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getShirtSize();
		assertEquals("Mapping of 'person' (shirtsize) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_comment() {
		Person person = Person.newInstance();
		person.setComment(Comment.newInstance("Sushi is BAE"));
		
		String expected = "Sushi is BAE";
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		String actual = personDTO.getComment();
		assertEquals("Mapping of 'person' (comment) was not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToDTO_yearOfBirth() {
		Person person = Person.newInstance();
		person.setYearOfBirth(YearOfBirth.newInstance(1965));
		
		Integer expected = Integer.valueOf(1965);
		
		PersonDTO personDTO = sut.mapPersonToDTO(person);
		
		Integer actual = personDTO.getYearOfBirth();
		assertEquals("Mapping of 'person' (yearOfBirth) was not correct!", expected, actual);
	}
}
