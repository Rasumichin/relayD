package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.09.2016
 *
 */
public class YearOfBirth implements Serializable {
	private static final long serialVersionUID = 6134749998574531013L;

	Integer value;

	private YearOfBirth() {
	}

	public static YearOfBirth newInstance() {
		return YearOfBirthNullObject.instance();
	}

	public static YearOfBirth newInstance(Integer year) {
		if (year == null) {
			return YearOfBirthNullObject.instance();
		}

		return new YearOfBirth(year);
	}

	private YearOfBirth(Integer aYear) {
		value = aYear;
	}

	public Integer getValue() {
		return value;
	}

	public boolean isEmpty() {
		return false;
	}

	public static int sortByYearOfBirth(YearOfBirth yearOfBirth1, YearOfBirth yearOfBirth2) {
		return yearOfBirth1.toString().compareTo(yearOfBirth2.toString());
	}

	@Override
	public String toString() {
		return getValue().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
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
		YearOfBirth other = (YearOfBirth) obj;
		if (getValue() == null) {
			if (other.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(other.getValue())) {
			return false;
		}
		return true;
	}

	static final class YearOfBirthNullObject extends YearOfBirth {
		private static final long serialVersionUID = -3383338875493013809L;

		private static final YearOfBirthNullObject SINGLETON = new YearOfBirthNullObject();

		private static YearOfBirthNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}