package com.relayd.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  03.01.2017
 *
 */
@Entity
@Table(name = "relay")
public class RelayEntity {

	@Id
	@Column(length = 36)
	private String id;

	@Column(nullable = false, length = 256)
	private String relayname;

	@Column(nullable = false)
	private Long duration = 0L;

	@ManyToOne
	@Column(name = "eventId", nullable = false, length = 36)
	@ForeignKey
	private RelayEventEntity relayEventEntity;

	@OneToMany(mappedBy = "relayEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<MemberEntity> memberEntities = new ArrayList<>();

	public static RelayEntity newInstance() {
		return RelayEntity.newInstance(UUID.randomUUID());
	}

	public static RelayEntity newInstance(UUID anId) {
		if (anId == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		RelayEntity relayEntity = new RelayEntity();
		relayEntity.setId(anId.toString());

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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long aDuration) {
		if (aDuration == null) {
			throw new IllegalArgumentException("[aDuration] must not be 'null'.");
		}
		duration = aDuration;
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

	// TODO - REL-286 - Discuss with CS - better remove boolean result of 'add' operation here?
	public void addMemberEntity(MemberEntity memberEntity) {
		// TODO - REL-285 - Discuss with CS - validation checks here (up to 4 members, no duplicate positions)?
		memberEntities.add(memberEntity);
		memberEntity.setRelayEntity(this);
	}

	public void removeMemberEntity(MemberEntity memberEntity) {
		int indexInList = getMemberEntities().indexOf(memberEntity);
		if (indexInList >= 0) {
			MemberEntity memberEntityToBeRemoved = getMemberEntities().get(indexInList);
			memberEntityToBeRemoved.setRelayEntity(null);
			memberEntities.remove(indexInList);
		}
	}

	public List<MemberEntity> getMemberEntities() {
		return Collections.unmodifiableList(memberEntities);
	}

	public Optional<MemberEntity> getMemberEntityAtPosition(Integer aPosition) {
		return getMemberEntities()
				.stream()
				.filter(eachEntity -> eachEntity.getPosition().equals(aPosition))
				.findFirst();
	}

	public void possiblyRemoveMemberEntity(Optional<MemberEntity> aMemberEntity) {
		if (aMemberEntity.isPresent()) {
			MemberEntity memberEntity = aMemberEntity.get();
			removeMemberEntity(memberEntity);
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