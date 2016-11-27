package com.relayd.web.bridge;

import java.io.Serializable;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.attributes.Position;

/**
 * Klasse übernommen aus dem Primefaces-Beispiel.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 */
public class TreeNodeRow implements Serializable {
	private static final long serialVersionUID = 1L;

	private Participant participant;
	private Position position = Position.UNKNOWN;
	private Relay relay; // TODO (Christian, Version 1.3): mit Erik sprechen. Eine Relay hat auch ein NOP?

	public TreeNodeRow(Participant aParticipant, Position aPosition) {
		participant = aParticipant;
		position = aPosition;
	}

	public TreeNodeRow(Relay aRelay) {
		relay = aRelay;
	}

	public static TreeNodeRow newInstance(Participant personRelay, Position aPosition) {
		return new TreeNodeRow(personRelay, aPosition);
	}

	public static TreeNodeRow newInstance(Relay relay) {
		return new TreeNodeRow(relay);
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant aParticipant) {
		participant = aParticipant;
	}

	public Relay getRelay() {
		return relay;
	}

	public boolean isRelay() {
		return relay != null;
	}

	// TODO (Christian, Version 1.3): Im Zuge der GUI Darstellung auf String umgestellt. Sollte eher ein Domain/GUI Objekt werden/sein?
	public String getRelayname() {
		if (relay == null) {
			return "";
		}
		return relay.toString();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position aPosition) {
		position = aPosition;
	}
}