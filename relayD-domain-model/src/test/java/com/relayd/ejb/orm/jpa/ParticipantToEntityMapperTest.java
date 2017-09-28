package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.attributes.Comment;
import com.relayd.entity.ParticipantEntity;

/**
 * The time to write good code is at the time you are writing it.
 *  - Daniel Read
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   27.09.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantToEntityMapperTest {
	private ParticipantToEntityMapper sut = ParticipantToEntityMapper.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapParticipantToEntity_ForParticipantIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		ParticipantEntity dummyParticipantEntity = null;

		sut.mapParticipantToEntity(null, dummyParticipantEntity);
	}

	@Test
	public void testMapParticipantToEntity_ForParticipantEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		Participant dummyParticipant = Participant.newInstance();

		sut.mapParticipantToEntity(dummyParticipant, null);
	}

	@Test
	public void testMapParticipantToEntity_ForCommentIsFilled() {
		String expected = "Comment for Participant";
		Participant participant = Participant.newInstance();
		participant.setComment(Comment.newInstance(expected));

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		sut.mapParticipantToEntity(participant, participantEntity);

		String actual = participantEntity.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, actual);
	}

	@Test
	public void testMapParticipantToEntity_ForCommentIsNull() {
		Participant participant = Participant.newInstance();
		participant.setComment(Comment.newInstance(null));

		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		sut.mapParticipantToEntity(participant, participantEntity);

		String actual = participantEntity.getComment();
		assertNull("Mapping of [comment] is not correct!", actual);
	}
}