package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Forename;

/**
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 * status initial
 * 
 */
@FacesConverter("com.relayd.web.converter.ForenameValueObjectConverter")
public class ForenameValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Forename name = null;
		if (!value.trim().isEmpty()) {
			name = Forename.newInstance(value);
		}
		
		return name;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		return value.toString();
	}
}