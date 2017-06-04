package com.relayd.ejb;

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
