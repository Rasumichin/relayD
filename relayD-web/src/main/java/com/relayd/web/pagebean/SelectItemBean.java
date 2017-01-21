package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;
import com.relayd.ejb.GatewayType;

/**
 * Holds the methods for Comboboxes in the GUI.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 01.09.2016
 *
 */
@ManagedBean(name = "selectItemBean")
@SessionScoped
public class SelectItemBean implements Serializable {
	private static final long serialVersionUID = -8028809269452661427L;

	private List<Shirtsize> shirtsizes;
	private List<Position> positions;
	private List<GatewayType> gateways;

	public SelectItemBean() {
		initShirtsizes();
		initPositions();
		initGateways();
	}

	private void initGateways() {
		gateways = new ArrayList<GatewayType>();

		for (GatewayType eachGatewayType : GatewayType.values()) {
			gateways.add(eachGatewayType);
		}
	}

	private void initShirtsizes() {
		shirtsizes = new ArrayList<Shirtsize>();

		for (Shirtsize eachShirtsize : Shirtsize.values()) {
			shirtsizes.add(eachShirtsize);
		}
	}

	private void initPositions() {
		positions = new ArrayList<Position>();

		for (Position eachPosition : Position.values()) {
			positions.add(eachPosition);
		}
	}

	public List<Shirtsize> getShirtsizes() {
		return Collections.unmodifiableList(shirtsizes);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}

	public List<GatewayType> getGatewayTypes() {
		return Collections.unmodifiableList(gateways);
	}
}