package com.relayd.ejb.orm.jpa;

import com.relayd.Participant;
import com.relayd.entity.ParticipantEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   27.09.2017
 *
 */
public class ParticipantToEntityMapper {

	private ParticipantToEntityMapper() {
		//restrict access
	}

	public static ParticipantToEntityMapper newInstance() {
		return new ParticipantToEntityMapper();
	}

	public void mapParticipantToEntity(Participant source, ParticipantEntity target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}
		target.setComment(source.getComment().isEmpty() ? null : source.getComment().toString());
	}
}