package com.relayd.web.bridge;

import com.relayd.Participant;
import com.relayd.attributes.Position;

/**
 *
 */
public class TreeNodeRowParticipant extends TreeNodeRow {
	private static final long serialVersionUID = -6690562419929394518L;

	private Participant participant;
	private Position position = Position.UNKNOWN;

	public TreeNodeRowParticipant(Participant aParticipant, Position aPosition) {
		participant = aParticipant;
		position = aPosition;
	}

	@Override
	public Participant getParticipant() {
		return participant;
	}

	@Override
	public void setParticipant(Participant aParticipant) {
		participant = aParticipant;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position aPosition) {
		position = aPosition;
	}
}
