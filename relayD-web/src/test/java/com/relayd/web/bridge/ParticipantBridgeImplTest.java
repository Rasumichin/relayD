package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.ParticipantBuilder;
import com.relayd.attributes.Forename;

/**
 * Thus spake the master programmer:
 * “Though a program be but three lines long, someday it will have to be maintained.”
 *  - The Tao of Programming
 *
 * @author schmollc (Christian@relayD.de)
 * @since 01.10.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantBridgeImplTest extends ParticipantBridgeImpl {

	private static final String EMAIL_JUSTUS = "Justus.Jonas@RockyBeach.com";

	private static final String EMAIL_PETER = "Peter.Shaw@RockyBeach.com";

	private ParticipantBridgeImpl sut = new ParticipantBridgeImpl();

	@Test
	public void testGetEmailList() {
		List<Participant> someParticipants = listWithParticipants();

		String result = sut.getEmailList(someParticipants);

		assertNotNull("[result] invalid!", result);
		assertFalse("[result] has not be empty!", result.isEmpty());
		assertEquals("[result] not correct!", "Peter.Shaw@RockyBeach.com, Justus.Jonas@RockyBeach.com", result);
	}

	private List<Participant> listWithParticipants() {
		List<Participant> someParticipants = new ArrayList<>();
		ParticipantBuilder builder = new ParticipantBuilder();

		Participant participantOne = builder.withForename(Forename.newInstance("Dirk")).build();
		someParticipants.add(participantOne);

		Participant participantTwo = builder.withForename(Forename.newInstance("Christian")).build();
		someParticipants.add(participantTwo);

		Participant participantThree = builder.withForename(Forename.newInstance("Peter")).withEmail(EMAIL_PETER).build();
		someParticipants.add(participantThree);

		Participant participantFour = builder.withForename(Forename.newInstance("Justus")).withEmail(EMAIL_JUSTUS).build();
		someParticipants.add(participantFour);

		builder = new ParticipantBuilder();

		Participant participantFive = builder.withForename(Forename.newInstance("Michi")).build();
		someParticipants.add(participantFive);

		Participant participantSix = builder.withForename(Forename.newInstance("Smudo")).build();
		someParticipants.add(participantSix);

		Participant participantSeven = builder.withForename(Forename.newInstance("Andy")).build();
		someParticipants.add(participantSeven);

		Participant participantEight = builder.withForename(Forename.newInstance("Thomas")).build();
		someParticipants.add(participantEight);

		return someParticipants;
	}

}
