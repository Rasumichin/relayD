package com.relayd.web.bridge;

import java.util.List;

import com.relayd.Participant;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 01.10.2017
 *
 */
public interface ParticipantBridge {
	String getEmailList(List<Participant> someParticipants);
}
