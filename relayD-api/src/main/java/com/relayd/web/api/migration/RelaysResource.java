package com.relayd.web.api.migration;

import javax.ws.rs.*;

import com.relayd.entity.migration.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.01.2017
 *
 */
@Path("relays")
public class RelaysResource {

	@Path("count")
	@GET
	@Produces("application/json")
	public RelayCounter count() {
		RelayMigrationServiceBridge serviceBridge = RelayMigrationServiceBridge.newInstance();
		RelayCounter result = serviceBridge.countOldRelays();
		
		return result;
	}

	@Path("copy")
	@GET
	@Produces("application/json")
	public RelayCounter copy() {
		RelayMigrationServiceBridge serviceBridge = RelayMigrationServiceBridge.newInstance();
		RelayCounter result = serviceBridge.copyAllRelays();
		
		return result;
	}
}
