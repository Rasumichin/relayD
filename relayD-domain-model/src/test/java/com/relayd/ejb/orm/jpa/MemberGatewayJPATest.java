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
import com.relayd.Participant;
import com.relayd.entity.MemberEntity;
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

		UUID expectedUuidMember = UUID.randomUUID();
		MemberEntity memberEntity = MemberEntity.newInstance(expectedUuidMember);
		memberEntity.setPersonEntity(personEntity);
		doReturn(memberEntity).when(sut).findById(expectedUuidMember);

		Member actual = sut.get(expectedUuidMember);
		assertNotNull("Actual [Member] must not be 'null'!", actual);

		UUID actualUuidMember = actual.getUuid();
		assertEquals("Mapping from ParticipantEntity to Member was not correct.", expectedUuidMember, actualUuidMember);

		UUID actualUuidPerson = actual.getUuidPerson();
		assertEquals("Mapping from PersonEntity to Person was not correct.", expectedUuidPerson, actualUuidPerson);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWithIllegalNullValue() {
		sut.set(null);
	}

	@Test
	public void testSet() {

		MemberEntity expectedMemberEntity = MemberEntity.newInstance();
		Member member = Member.newInstance(Participant.newInstance());
		GenericJpaDao jpaDaoMock = mock(GenericJpaDao.class);
		doReturn(jpaDaoMock).when(sut).getJpaDao();
		doReturn(expectedMemberEntity).when(sut).findById(member.getUuid());

		sut.set(member);

		verify(jpaDaoMock).mergeEntity(expectedMemberEntity);
	}

	@Test
	public void testFindById() {
		MemberEntity expected = MemberEntity.newInstance();
		GenericJpaDao genericJpaDaoMock = Mockito.mock(GenericJpaDao.class);
		doReturn(expected).when(genericJpaDaoMock).findById(MemberEntity.class, expected.getId());
		doReturn(genericJpaDaoMock).when(sut).getJpaDao();

		MemberEntity actual = sut.findById(UUID.fromString(expected.getId()));

		assertEquals("[findById] not correct!", expected, actual);
	}
}