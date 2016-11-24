package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.TreeNodeRow;

import static org.mockito.Mockito.*;

/**
 * Tests are the Programmer’s stone, transmuting fear into boredom.
 *  - Kent Beck
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayBrowsePageBeanTest {

	@Spy
	private RelayBrowsePageBean sut;

	@Before
	public void setUp() {
		doNothing().when(sut).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
	}

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testAddPersonToRelay_ForWrongSelection() {
		ActionEvent dummyActionEvent = null;

		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());
		TreeNode relayTreeNode = new DefaultTreeNode(new TreeNodeRow(relay), null);
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		sut.setSelectedPersons(createListFor(justusJonas));

		sut.addPersonToRelay(dummyActionEvent);

		verify(sut).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
	}

	@Test
	public void testAddPersonToRelay_ForRightSelection() {
		ActionEvent dummyActionEvent = null;

		Participant personRelay = Participant.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(new TreeNodeRow(personRelay), null);
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		sut.setSelectedPersons(createListFor(justusJonas));

		sut.addPersonToRelay(dummyActionEvent);

		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());

		TreeNodeRow selectedRelayNode = (TreeNodeRow) relayTreeNode.getData();
		Participant actual = selectedRelayNode.getParticipant();

		assertEquals("UUID not correct!", justusJonas.getUuid(), actual.getUuidPerson());
	}

	private List<Person> createListFor(Person person) {
		List<Person> somePersons = new ArrayList<Person>();
		somePersons.add(person);
		return somePersons;
	}
}