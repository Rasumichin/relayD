package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.jpa.GenericJpaDao;

import static org.mockito.Mockito.*;

/**
 * Tue nichts, das nutzlos ist.
 *  - Miyamoto Musashi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class MemberGatewayJPATest {

	@Spy
	private MemberGatewayJPA sut;

	@Test
	public void testGetEntityToMemberMapper() {
		EntityToMemberMapper actual = sut.getEntityToMemberMapper();
		assertNotNull("[entityToMemberMapper] has not been initialized.", actual);
	}

	@Test
	public void testGetMemberToEntityMapper() {
		MemberToEntityMapper actual = sut.getMemberToEntityMapper();
		assertNotNull("[memberToEntityMapper] has not been initialized.", actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGet_ForIllegalNullValue() {
		sut.get(null);
	}

	@Test
	public void testGet() {
		UUID expectedUuidPerson = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expectedUuidPerson);

		UUID expectedUuidParticipant = UUID.randomUUID();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance(expectedUuidParticipant.toString());
		participantEntity.setPersonEntity(personEntity);
		doReturn(participantEntity).when(sut).findById(expectedUuidParticipant);

		Member actual = sut.get(expectedUuidParticipant);
		assertNotNull("Actual [Member] must not be 'null'!", actual);

		UUID actualUuidParticipant = actual.getUuid();
		assertEquals("Mapping from ParticipantEntity to Member was not correct.", expectedUuidParticipant, actualUuidParticipant);

		UUID actualUuidPerson = actual.getUuidPerson();
		assertEquals("Mapping from PersonEntity to Person was not correct.", expectedUuidPerson, actualUuidPerson);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWithIllegalNullValue() {
		sut.set(null);
	}

	@Test
	public void testSet() {

		ParticipantEntity expectedParticipantEntity = ParticipantEntity.newInstance();
		Person person = Person.newInstance();
		Member member = Member.newInstance(person);
		GenericJpaDao jpaDaoMock = mock(GenericJpaDao.class);
		doReturn(jpaDaoMock).when(sut).getJpaDao();
		doReturn(expectedParticipantEntity).when(sut).findById(member.getUuid());

		sut.set(member);

		verify(jpaDaoMock).mergeEntity(expectedParticipantEntity);
	}

	@Test
	public void testFindById() {
		ParticipantEntity expected = ParticipantEntity.newInstance();
		GenericJpaDao genericJpaDaoMock = Mockito.mock(GenericJpaDao.class);
		doReturn(expected).when(genericJpaDaoMock).findById(ParticipantEntity.class, expected.getId());
		doReturn(genericJpaDaoMock).when(sut).getJpaDao();

		ParticipantEntity actual = sut.findById(UUID.fromString(expected.getId()));

		assertEquals("[findById] not correct!", expected, actual);
	}
}