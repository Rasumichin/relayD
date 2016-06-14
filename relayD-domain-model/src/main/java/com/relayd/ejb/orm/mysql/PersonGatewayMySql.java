package com.relayd.ejb.orm.mysql;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class PersonGatewayMySql implements PersonGateway {

	//	@Inject
	//	private EntityManager em;

	@Override
	public Person get(UUID uuid) {
		//		QPerson person = QPerson.person;
		//		JPAQuery query = new JPAQuery(em).from(person).where(person.uuid.eq(uuid));
		//
		//		Person result = query.uniqueResult(person);
		//
		//		return EntityToVoMapper.map(result);
		return null;
	}

	@Override
	public void set(Person aPerson) {
		//		em.persist(VOToEntityMapper.map(aPerson));
	}

}
