package com.relayd.ejb;

import java.util.UUID;

import com.relayd.Participant;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   1610.2017
 *
 */
public interface ParticipantGateway {

	Participant get(UUID aUuid);

	void set(Participant participant);

}
