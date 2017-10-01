package com.relayd.web.bridge;

import java.util.List;

import com.relayd.Participant;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 01.10.2017
 *
 */
public class ParticipantBridgeImpl implements ParticipantBridge {

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

}
