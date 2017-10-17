package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.ejb.ParticipantGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class ParticipantGatewayJPA extends GatewayJPA implements ParticipantGateway {

	@Override
	public Participant get(UUID uuid) {
		return null;
	}

	@Override
	public void set(Participant participant) {
	}
}