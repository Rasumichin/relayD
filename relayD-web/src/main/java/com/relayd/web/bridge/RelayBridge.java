package com.relayd.web.bridge;

import org.primefaces.model.TreeNode;

import com.relayd.Relay;
import com.relayd.ejb.GatewayType;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
public interface RelayBridge {

	TreeNode all();

	void create(Relay aWorkingRelay);

	void persist(TreeNode relay);

	GatewayType getGatewayType();

}