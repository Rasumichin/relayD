package com.relayd.ejb;

import com.relayd.ejb.orm.jdbc.RelayEventGatewayJDBC;
import com.relayd.ejb.orm.jpa.RelayEventGatewayJPA;
import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 17.06.2016
 *
 */
public class RelayEventGatewayFactory {

	public static RelayEventGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new RelayEventGatewayMemory();

			case JPA:
				return new RelayEventGatewayJPA();

			case JDBC:
				return new RelayEventGatewayJDBC();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}