package com.relayd.web.bridge;

import java.util.List;

import org.primefaces.model.TreeNode;

import com.relayd.Relay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
public interface RelayBridge {
	List<Relay> all();

	TreeNode allRelays();
}
