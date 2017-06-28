package com.relayd.ejb;

import com.relayd.ejb.orm.file.MemberGatewayFile;
import com.relayd.ejb.orm.jpa.MemberGatewayJPA;
import com.relayd.ejb.orm.memory.MemberGatewayMemory;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayFactory {

	public static MemberGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new MemberGatewayMemory();

			case FILE:
				return new MemberGatewayFile();

			case JPA:
				return new MemberGatewayJPA();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}

}
