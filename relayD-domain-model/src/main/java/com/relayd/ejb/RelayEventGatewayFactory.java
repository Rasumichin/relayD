package com.relayd.ejb;

import com.relayd.ejb.orm.file.RelayEventGatewayFile;
import com.relayd.ejb.orm.jpa.RelayEventGatewayJPA;
import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * @author schmollc
 * @since 17.06.2016
 *
 */
public class RelayEventGatewayFactory {

	public static RelayEventGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new RelayEventGatewayMemory();

			case FILE:
				return new RelayEventGatewayFile();

			case JPA:
				return new RelayEventGatewayJPA();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}