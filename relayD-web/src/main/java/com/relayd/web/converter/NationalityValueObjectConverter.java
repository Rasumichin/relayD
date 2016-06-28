package com.relayd.web.converter;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   28.06.2016
 * status   initial
 */
@FacesConverter("com.relayd.web.converter.NationalityValueObjectConverter")
public class NationalityValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		Locale locale = new Locale(value);
		return locale;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}