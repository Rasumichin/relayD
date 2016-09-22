package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.YearOfBirth;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 22.09.2016
 * status initial
 *
 */
@FacesConverter("com.relayd.web.converter.YearOfBirthValueObjectConverter")
public class YearOfBirthValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		Integer year = Integer.valueOf(value);
		YearOfBirth yearOfBirth = YearOfBirth.newInstance(year);
		return yearOfBirth;

	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}
