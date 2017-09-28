package com.relayd.ejb.orm.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayTest;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEventEntity;

/**
 * Die Tat ist alles, nichts der Ruhm.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventGatewayJPATest extends RelayEventGatewayTest {

	private RelayEventGatewayJPA sut = new RelayEventGatewayJPA() {
		private Map<String, RelayEventEntity> relayEventEntities1 = new HashMap<>();

		@Override
		RelayEventEntity findById(UUID uuid) {
			return relayEventEntities1.get(uuid.toString());
		};

		@Override
		void mergeEntity(RelayEventEntity relayEventEntity) {
			relayEventEntities1.put(relayEventEntity.getId(), relayEventEntity);
		};

		@Override
		PersonEntity findPersonEntityById(UUID uuid) {
			if (uuid.equals(uuidForJustusJonas)) {
				PersonEntity person = PersonEntity.newInstance();
				person.setForename("Justus");
				person.setSurename("Jonas");

				return person;
			}
			if (uuid.equals(uuidForPeterShaw)) {
				PersonEntity person = PersonEntity.newInstance();
				person.setForename("Peter");
				person.setSurename("Shaw");

				return person;
			}

			return null;
		};
	};

	@Override
	@Ignore("Fuer diese Tests muss noch ordentlich gemockt werden.")
	public RelayEventGateway getSut() {
		return sut;
	}

	@Override
	@Test
	@Ignore("Fuer diese Tests muss noch ordentlich gemockt werden.")
	public void testGetAll() {
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	@Ignore("Fuer diese Tests muss noch ordentlich gemockt werden.")
	public void testSet() {
		sut.set(null);
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	@Ignore("Fuer diese Tests muss noch ordentlich gemockt werden.")
	public void testGet_ForExistingEntry() {
		sut.get(null);
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	@Ignore("Fuer diese Tests muss noch ordentlich gemockt werden.")
	public void testGet_ForNonExistingEntry() {
		sut.get(null);
	}
}