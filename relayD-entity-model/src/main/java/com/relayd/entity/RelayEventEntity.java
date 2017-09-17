package com.relayd.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 *
 */
@Entity
@Table(name = "relay_event")
public class RelayEventEntity {

	@Id
	@Column(length = 36)
	private String id;

	@Column(length = 256, nullable = false)
	private String eventName;

	@Column(nullable = false)
	private Date eventDay;

	@OneToMany(mappedBy = "relayEventEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<RelayEntity> relayEntities = new ArrayList<>();

	@OneToMany(mappedBy = "relayEventEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ParticipantEntity> participantEntities = new ArrayList<>();

	public List<ParticipantEntity> getParticipantEntities() {
		return Collections.unmodifiableList(participantEntities);
	}

	public static RelayEventEntity newInstance() {
		return RelayEventEntity.newInstance(UUID.randomUUID());
	}

	public static RelayEventEntity newInstance(UUID anUuid) {
		if (anUuid == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		RelayEventEntity relayEventEntity = new RelayEventEntity();
		relayEventEntity.setId(anUuid.toString());

		return relayEventEntity;
	}

	public String getId() {
		return id;
	}

	void setId(String anId) {
		id = anId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String anEventName) {
		eventName = anEventName;
	}

	public Date getEventDay() {
		return eventDay;
	}

	public void setEventDay(Date anEventDay) {
		eventDay = anEventDay;
	}

	public void addRelay(RelayEntity relayEntity) {
		relayEntities.add(relayEntity);
		relayEntity.setRelayEventEntity(this);
	}

	public Optional<ParticipantEntity> getParticipantEntity(UUID uuid) {
		for (ParticipantEntity each : participantEntities) {
			if (each.getId().equals(uuid.toString())) {
				return Optional.of(each);
			}
		}
		return Optional.empty();
	}

	public void addParticipant(ParticipantEntity participantEntitiy) {
		participantEntities.add(participantEntitiy);
		participantEntitiy.setRelayEventEntity(this);
	}

	public void removeParticipant(ParticipantEntity participantEntity) {
		int indexInList = getParticipantEntities().indexOf(participantEntity);
		if (indexInList >= 0) {
			participantEntities.remove(indexInList);
		}
	}

	public List<RelayEntity> getRelayEntities() {
		return Collections.unmodifiableList(relayEntities);
	}

	public void resetParticipantEnteties() {
		participantEntities.clear();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", eventName=" + eventName + ", eventDay=" + eventDay + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RelayEventEntity other = (RelayEventEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}