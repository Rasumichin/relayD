package com.relayd.ejb.orm.memory;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.RelayEvent;
import com.relayd.ejb.ParticipantGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class ParticipantGatewayMemory implements ParticipantGateway {

	@Override
	public Participant get(UUID uuid) {
		for (RelayEvent eachRelayEvent : MemorySingleton.getInstance().getEvents().values()) {
			for (Participant eachParticipant : eachRelayEvent.getParticipants()) {
				if (uuid.equals(eachParticipant.getUuid())) {
					return eachParticipant;
				}
			}
		}
		return null;
	}

	@Override
	public void set(Participant participant) {
	}
}