package com.relayd.ejb;

import com.relayd.ejb.orm.memory.RelayGatewayMemory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 08.08.2016
 *
 */
public class RelayGatewayFactory {

	public static RelayGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new RelayGatewayMemory();
			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}