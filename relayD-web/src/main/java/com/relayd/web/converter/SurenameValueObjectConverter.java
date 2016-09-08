package com.relayd.web.converter;

import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayD.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   26.06.2016
 * status   initial
 * 
 */
@FacesConverter("com.relayd.web.converter.SurenameValueObjectConverter")
public class SurenameValueObjectConverter extends NameValueObjectConverter {

	@Override
	Surename getName(String nameValue) {
		return Surename.newInstance(nameValue);
	}
}