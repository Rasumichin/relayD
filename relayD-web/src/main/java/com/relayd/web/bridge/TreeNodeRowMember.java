package com.relayd.web.bridge;

import com.relayd.Member;
import com.relayd.attributes.Position;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 29.11.2016
 *
 */
public class TreeNodeRowMember extends TreeNodeRow {
	private static final long serialVersionUID = -6690562419929394518L;

	private Member member;
	private Position position = Position.UNKNOWN;

	public TreeNodeRowMember(Member aMember, Position aPosition) {
		member = aMember;
		position = aPosition;
	}

	@Override
	public Member getMember() {
		return member;
	}

	@Override
	public void setMember(Member aMember) {
		member = aMember;
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
