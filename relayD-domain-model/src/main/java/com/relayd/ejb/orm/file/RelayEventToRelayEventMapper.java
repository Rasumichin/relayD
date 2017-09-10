package com.relayd.ejb.orm.file;

import java.util.ArrayList;
import java.util.List;

import com.relayd.Participant;
import com.relayd.RelayEvent;

/**
 * @author schmollc
 * @since 10.02.2017
 *
 */
public class RelayEventToRelayEventMapper {
	private RelayEventToRelayEventMapper() {
		super();
	}

	public static RelayEventToRelayEventMapper newInstance() {
		return new RelayEventToRelayEventMapper();
	}

	public void mapRelayEventToRelayEvent(RelayEvent source, RelayEvent target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setEventDay(source.getEventDay());
		target.setName(source.getName());

		List<Participant> allParticipant = new ArrayList<>();

		for (Participant each : target.getParticipants()) {
			allParticipant.add(each);
		}

		for (Participant each : allParticipant) {
			target.removeParticipant(each);
		}

		for (Participant each : source.getParticipants()) {
			target.addParticipant(each);
		}
	}
}