package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.RelayCount;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   25.08.2016
 *
 */
@FacesConverter("com.relayd.web.converter.RelayCountValueObjectConverter")
public class RelayCountValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		RelayCount relayCount = RelayCount.newInstance(Integer.valueOf(value));
		return relayCount;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}