package com.relayd.web.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.FormatConstants;
import com.relayd.attributes.Birthday;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
@FacesConverter("com.relayd.web.converter.BirthdayValueObjectConverter")
public class BirthdayValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(FormatConstants.DATE_FORMAT));
		Birthday eventDay = Birthday.newInstance(localDate);
		return eventDay;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}