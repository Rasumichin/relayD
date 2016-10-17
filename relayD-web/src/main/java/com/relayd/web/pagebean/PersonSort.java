package com.relayd.web.pagebean;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 07.09.2016
 *
 */
public class PersonSort {

	public int sortByRelayname(Person person1, Person person2) {
		if (person1.getRelayname() == null) {
			return -1;
		}
		if (person2.getRelayname() == null) {
			return 1;
		}
		int position = person1.getRelayname().compareTo(person2.getRelayname());
		if (person1.getPosition() == null || person2.getPosition() == null) {
			return position;
		}
		position = position + person1.getPosition().compareTo(person2.getPosition());
		return position;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return name1.toString().compareTo(name2.toString());
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return name1.toString().compareTo(name2.toString());
	}

	public int sortByShirtsize(Shirtsize size1, Shirtsize size2) {
		return size1.toString().compareTo(size2.toString());
	}

	public int sortByEmail(Email email1, Email email2) {
		return email1.toString().compareTo(email2.toString());
	}

	public int sortByYearOfBirth(YearOfBirth aYearOfBirth1, YearOfBirth aYearOfBirth2) {
		return aYearOfBirth1.toString().compareTo(aYearOfBirth2.toString());
	}
}