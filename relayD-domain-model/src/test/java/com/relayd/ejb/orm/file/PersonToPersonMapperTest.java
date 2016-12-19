package com.relayd.ejb.orm.file;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * Things I hate
 * 1. No Tests
 * 2. Lists
 * 3. Irony
 * 4. Lists
 * 5. Repetition
 * F. Inconsisty
 *  -  (Un)known TDD Developer
 *
 * @author schmollc (Christian@relayd.de)
 * @since 19.12.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToPersonMapperTest {

	private PersonToPersonMapper sut = PersonToPersonMapper.newInstance();

	private Person source = Person.newInstance();
	private Person target = Person.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapPersonToPerson_ForPersonSourceIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		sut.mapPersonToPerson(null, target);
	}

	@Test
	public void testMapPersonToPerson_ForPersonTargetIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		sut.mapPersonToPerson(source, null);
	}

	@Test
	public void testMapPersonToPerson_YearOfBirth() {
		YearOfBirth expected = YearOfBirth.newInstance(1971);
		source.setYearOfBirth(expected);

		sut.mapPersonToPerson(source, target);

		YearOfBirth actual = target.getYearOfBirth();
		assertEquals("Mapping of [yearOfBirth] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Surename() {
		Surename expected = Surename.newInstance("Justus");
		source.setSurename(expected);

		sut.mapPersonToPerson(source, target);

		Surename actual = target.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Forename() {
		Forename expected = Forename.newInstance("Jonas");
		source.setForename(expected);

		sut.mapPersonToPerson(source, target);

		Forename actual = target.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Shirtsize() {
		Shirtsize expected = Shirtsize.HerrenXXL;
		source.setShirtsize(expected);

		sut.mapPersonToPerson(source, target);

		Shirtsize actual = target.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Email() {
		Email expected = Email.newInstance("Justus.Jonas@RockyBeach.com");
		source.setEmail(expected);

		sut.mapPersonToPerson(source, target);

		Email actual = target.getEmail();
		assertEquals("Mapping of [email] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Comment() {
		Comment expected = Comment.newInstance("1. Detektiv");
		source.setComment(expected);

		sut.mapPersonToPerson(source, target);

		Comment actual = target.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, actual);
	}
}