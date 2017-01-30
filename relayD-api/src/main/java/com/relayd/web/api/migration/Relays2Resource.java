package com.relayd.web.api.migration;

import javax.ws.rs.*;

import com.relayd.entity.migration.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 30.01.2017
 *
 */
@Path("relay2s")
public class Relays2Resource {

	@Path("count")
	@GET
	@Produces("application/json")
	public RelayCounter count() {
		RelayMigrationServiceBridge serviceBridge = RelayMigrationServiceBridge.newInstance();
		RelayCounter result = serviceBridge.countNewRelays();
		
		return result;
	}
}
