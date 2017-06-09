package com.relayd.ejb.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.MemberGateway;
import com.relayd.ejb.MemberGatewayTest;

/**
 * Merke: Das einzige wirklich sichere Ergebnis einer Optimierung ist überlicherweise ein erschwertes Verständnis des Quelltextes.
 *  - Steve McConnell - Code Complete Seite 777
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberGatewayMemoryTest extends MemberGatewayTest {

	private MemberGatewayMemory sut = new MemberGatewayMemory();

	@Override
	@Before
	public void setUp() {
		MemorySingleton.getInstance().getMembers().clear();
		super.setUp();
	}

	@Override
	public MemberGateway getSut() {
		return sut;
	}
}