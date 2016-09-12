package com.relayd.ejb;

import com.relayd.ejb.orm.db.PersonGatewayDB;
import com.relayd.ejb.orm.file.PersonGatewayFile;
import com.relayd.ejb.orm.memory.PersonGatewayMemory;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
public class PersonGatewayFactory {

	public static PersonGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new PersonGatewayMemory();

			case FILE:
				return new PersonGatewayFile();

			case DB:
				return new PersonGatewayDB();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}