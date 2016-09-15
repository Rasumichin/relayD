package com.relayd.ejb;

import com.relayd.ejb.orm.file.PersonGatewayFile;
import com.relayd.ejb.orm.jdbc.PersonGatewayJDBC;
import com.relayd.ejb.orm.jpa.PersonGatewayJPA;
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

			case JPA:
				return new PersonGatewayJPA();

			case JDBC:
				return new PersonGatewayJDBC();
			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}