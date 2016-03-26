package com.relayd.ejb;

import com.relayd.Person;
import com.relayd.attributes.Surename;

/**
 * Dieses Interface wäre das Bindeglich der Buisness Schicht zur Persistenz Schicht.
 *
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public interface PersonGateway {
	Person get(Surename surename);

	void set(Person person);
}
