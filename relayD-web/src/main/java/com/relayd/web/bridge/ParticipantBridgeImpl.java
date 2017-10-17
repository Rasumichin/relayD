package com.relayd.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Settings;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.ParticipantGateway;
import com.relayd.ejb.ParticipantGatewayFactory;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 01.10.2017
 *
 */
public class ParticipantBridgeImpl implements Serializable, ParticipantBridge {

	private static final long serialVersionUID = 1496586084568676473L;

	@Override
	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

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
		return getGateway().get(uuid);
	}

	@Override
	public void set(Participant participant) {
		getGateway().set(participant);
	}

	private ParticipantGateway getGateway() {
		return ParticipantGatewayFactory.get(getGatewayType());
	}
}