package com.relayd.ejb.orm.file;

import java.util.UUID;

import com.relayd.Participant;
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
		return null;
	}

	@Override
	public void set(Participant participant) {
	}
}