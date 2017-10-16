package com.relayd.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.relayd.Participant;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 01.10.2017
 *
 */
public class ParticipantBridgeImpl implements Serializable, ParticipantBridge {

	private static final long serialVersionUID = 1496586084568676473L;

	@Override
	public String getEmailList(List<Participant> someParticipants) {
		StringBuilder builder = new StringBuilder();
		for (Participant participant : someParticipants) {
			if (participant.hasEmail()) {
				builder.append(", " + participant.getEmail());
			}
		}
		String output = builder.toString();
		output = output.replaceFirst(", ", "");

		return output;

	}

	@Override
	public Participant get(UUID uuid) {
		return null;
	}

	@Override
	public void set(Participant participant) {
	}
}
