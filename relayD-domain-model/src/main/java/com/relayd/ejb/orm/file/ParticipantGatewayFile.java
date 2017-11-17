package com.relayd.ejb.orm.file;

import java.util.List;
import java.util.UUID;

import com.relayd.Participant;
import com.relayd.RelayEvent;
import com.relayd.ejb.ParticipantGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class ParticipantGatewayFile implements ParticipantGateway {

	public ParticipantGatewayFile() {

	}

	ParticipantGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	@Override
	public Participant get(UUID uuid) {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();

		RelayEvent relayEvent = someRelayEvents.get(0);
		for (Participant eachParticipant : relayEvent.getParticipants()) {
			if (uuid.equals(eachParticipant.getUuid())) {
				return eachParticipant;
			}
		}
		return null;
	}

	@Override
	public void set(Participant participant) {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();

		RelayEvent relayEvent = someRelayEvents.get(0);
		for (Participant eachParticipant : relayEvent.getParticipants()) {
			if (participant.getUuid().equals(eachParticipant.getUuid())) {
				mapParticipantToParticipant(eachParticipant, participant);
			}
		}

		FileSingleton.getInstance().setRelayEvents(someRelayEvents);
	}

	private void mapParticipantToParticipant(Participant target, Participant source) {
		target.setComment(source.getComment());
		target.setShirtsize(source.getShirtsize());
	}
}