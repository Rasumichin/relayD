package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.entity.MemberEntity;
import com.relayd.entity.PersonEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToMemberMapperTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private EntityToMemberMapper sut = EntityToMemberMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapToMember_whenMemberEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[memberEntity] must not be 'null'.");

		sut.mapToMember(null);
	}

	@Test
	public void testMapToMember_check_id() {
		UUID expectedPerson = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expectedPerson);
		MemberEntity memberEntity = MemberEntity.newInstance();
		memberEntity.setPersonEntity(personEntity);

		Member member = sut.mapToMember(memberEntity);

		UUID actualPerson = member.getUuidPerson();
		assertEquals("Mapping of [uuidPerson] is not correct!", expectedPerson, actualPerson);

		UUID memberUUID = member.getUuid();
		String actualMember = memberUUID.toString();
		assertEquals("Mapping of [uuidPerson] is not correct!", memberEntity.getId(), actualMember);
	}

	@Test
	public void testMapToMember_check_person() {
		String expected = "Robert";
		PersonEntity personEntity = PersonEntity.newInstance();
		personEntity.setForename(expected);

		MemberEntity memberEntity = MemberEntity.newInstance();
		memberEntity.setPersonEntity(personEntity);

		Member member = sut.mapToMember(memberEntity);

		String actual = member.getForename().toString();
		assertEquals("Mapping of [person] is not correct!", expected, actual);
	}

	@Test
	public void testMapToMember_check_duration() {
		MemberEntity memberEntity = MemberEntity.newInstance();
		PersonEntity dummyEntity = PersonEntity.newInstance();
		memberEntity.setPersonEntity(dummyEntity);
		Long oneSecond = 1000L;
		memberEntity.setDuration(oneSecond * 15);

		Member member = sut.mapToMember(memberEntity);

		Duration actual = member.getDuration();
		Duration expected = Duration.ofSeconds(15);

		assertEquals("Mapping of [duration] is not corret!", expected, actual);
	}
}
