package com.relayd.web.bridge;

import java.util.UUID;

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

	void persistRelay(Relay aWorkingRelay);

	void persist(TreeNode relay);

	GatewayType getGatewayType();

	Relay get(UUID uuid);
}