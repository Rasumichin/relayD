package com.relayd.ejb.orm.jpa;

import java.sql.Date;

import com.relayd.RelayEvent;
import com.relayd.entity.RelayEventEntity;

/**
 * @author Rasumichin (Christian@relayd.de)
 * @since 27.03.2017
 *
 */
public class RelayEventToEntityMapper {

	private RelayEventToEntityMapper() {
	}

	public static RelayEventToEntityMapper newInstance() {
		return new RelayEventToEntityMapper();
	}

	public void mapRelayEventToEntity(RelayEvent source, RelayEventEntity target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setEventName(source.getName().isEmpty() ? null : source.getName().toString());

		Date date = source.getEventDay().isEmpty() ? null : Date.valueOf(source.getEventDay().getValue());
		target.setEventDay(date);
	}
}