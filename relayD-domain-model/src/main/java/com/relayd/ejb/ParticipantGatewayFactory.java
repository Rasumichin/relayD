package com.relayd.ejb;

import com.relayd.ejb.orm.file.ParticipantGatewayFile;
import com.relayd.ejb.orm.jpa.ParticipantGatewayJPA;
import com.relayd.ejb.orm.memory.ParticipantGatewayMemory;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   1610.2017
 *
 */
public class ParticipantGatewayFactory {

	public static ParticipantGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new ParticipantGatewayMemory();

			case FILE:
				return new ParticipantGatewayFile();

			case JPA:
				return new ParticipantGatewayJPA();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}