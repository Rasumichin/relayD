package com.relayd.ejb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.orm.file.MemberGatewayFile;
import com.relayd.ejb.orm.jpa.MemberGatewayJPA;
import com.relayd.ejb.orm.memory.MemberGatewayMemory;

/**
 * Wischen Sie Staub, bevor Sie Staub sehen.
 * Denken Sie nicht "Es ist ja schon sauber", sondern vielmehr: "Halte es sauber"
 *  - Philip Toshio Sudo
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberGatewayFactoryTest {

	@Test
	public void testGet_ForMemberGatewayMemory() {
		MemberGateway instance = MemberGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt!", instance.getClass(), MemberGatewayMemory.class);
	}

	@Test
	public void testGet_ForMemberGatewayFile() {
		MemberGateway instance = MemberGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt!", instance.getClass(), MemberGatewayFile.class);
	}

	@Test
	public void testGet_ForMemberGatewayJPA() {
		MemberGateway instance = MemberGatewayFactory.get(GatewayType.JPA);

		assertEquals("Instance not korrekt!", instance.getClass(), MemberGatewayJPA.class);
	}
}