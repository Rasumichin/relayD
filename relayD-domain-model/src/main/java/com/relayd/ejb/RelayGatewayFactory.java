package com.relayd.ejb;

import com.relayd.ejb.orm.memory.RelayGatewayMemory;

/**
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