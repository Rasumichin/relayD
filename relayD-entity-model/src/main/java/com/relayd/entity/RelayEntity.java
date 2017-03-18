package com.relayd.entity;

import java.util.*;

import javax.persistence.*;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  03.01.2017
 *
 */
@Entity
@Table(name = "relay2")
public class RelayEntity {

	@Id
	@Column(length=36)
	private String id;

	@Column(nullable=false, length=256)
	private String relayname;

	@ManyToOne
	@Column(name="eventId", nullable=false, length=36)
	@ForeignKey
	private RelayEventEntity relayEventEntity;

	@OneToMany(mappedBy="relay2Entity", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<ParticipantEntity> participantEntities = new ArrayList<>();
	
	public static RelayEntity newInstance() {
		RelayEntity relayEntity = new RelayEntity();
		relayEntity.setId(UUID.randomUUID().toString());

		return relayEntity;
	}

	public static RelayEntity newInstance(String anId) {
		if (anId == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		try {
			UUID.fromString(anId);
		} catch (IllegalArgumentException iAEx) {
			throw new IllegalArgumentException("[anId] is not a valid representation of an UUID.");
		}
		RelayEntity relayEntity = new RelayEntity();
		relayEntity.setId(anId);

		return relayEntity;
	}

	public String getId() {
		return id;
	}

	void setId(String anId) {
		id = anId;
	}

	public String getRelayname() {
		return relayname;
	}

	public void setRelayname(String aRelayname) {
		if (aRelayname == null) {
			throw new IllegalArgumentException("[aRelayname] must not be 'null'.");
		}
		relayname = aRelayname;
	}

	public void setRelayEventEntity(RelayEventEntity aRelayEventEntity) {
		if (aRelayEventEntity == null) {
			throw new IllegalArgumentException("[aRelayEventEntity] must not be 'null'.");
		}
		relayEventEntity = aRelayEventEntity;
	}
	
	public RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
	}
	
	// TODO EL (2017-01-08): Discuss with CS - better remove boolean result of 'add' operation here?
	public void addParticipantEntity(ParticipantEntity participantEntity) {
		// TODO EL (2017-01-08): Discuss with CS - validation checks here (up to 4 participants, no duplicate positions)?
		participantEntities.add(participantEntity);
		participantEntity.setRelay2Entity(this);
	}

	public void removeParticipantEntity(ParticipantEntity participantEntity) {
		int indexInList = getParticipantEntities().indexOf(participantEntity);
		if (indexInList >= 0) {
			ParticipantEntity participantEntityToBeRemoved = getParticipantEntities().get(indexInList);
			participantEntityToBeRemoved.setRelay2Entity(null);
			participantEntities.remove(indexInList);
		}
	}

	public List<ParticipantEntity> getParticipantEntities() {
		return Collections.unmodifiableList(participantEntities);
	}

	public Optional<ParticipantEntity> getParticipantEntityAtPosition(Integer aPosition) {
		return getParticipantEntities()
		.stream()
		.filter(eachEntity -> eachEntity.getPosition().equals(aPosition))
		.findFirst();
	}

	public void possiblyRemoveParticipantEntity(Optional<ParticipantEntity> aParticipantEntity) {
		if (aParticipantEntity.isPresent()) {
			ParticipantEntity participantEntity = aParticipantEntity.get();
			removeParticipantEntity(participantEntity);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", relayname=" + relayname + "]";
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
		RelayEntity other = (RelayEntity) obj;
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