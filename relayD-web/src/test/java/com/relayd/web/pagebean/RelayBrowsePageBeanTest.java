package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.event.ActionEvent;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.*;
import com.relayd.web.local.I18N;

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
	@InjectMocks
	private RelayBrowsePageBean sut;

	@Mock
	private RelayEditPageBean relayEditPageBean;

	@Mock
	private MemberEditPageBean memberEditPageBean;

	@Mock
	private RelayBridge relayBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).showMessage(any(Severity.class), anyString(), anyString());
		doNothing().when(sut).showDialog(any(Severity.class), anyString(), anyString());

	}

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testAddParticipantToRelay_ForMemberIsSelectedAndNoPersonIsSelected() {
		ActionEvent dummyActionEvent = null;

		Member member = Member.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(member, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		sut.setSelectedParticipants(null);

		sut.addParticipantToRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Please select a Person!");
	}

	@Test
	public void testAddParticipantToRelay_ForMemberIsSelectedAndTwoPersonsAreSelected() {
		ActionEvent dummyActionEvent = null;

		Member member = Member.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(member, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		Participant participantJustusJonas = Participant.newInstance(justusJonas);

		Person peterShaw = Person.newInstance();
		peterShaw.setForename(Forename.newInstance("Peter"));
		peterShaw.setSurename(Surename.newInstance("Shaw"));
		Participant participantPeterShaw = Participant.newInstance(peterShaw);
		sut.setSelectedParticipants(createListFor(participantJustusJonas, participantPeterShaw));

		sut.addParticipantToRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Please select a single Person!");
	}

	@Test
	public void testAddParticipantToRelay_ForNoSelection() {
		ActionEvent dummyActionEvent = null;

		sut.addParticipantToRelay(dummyActionEvent);

		verify(relayBridge, never()).set(any(TreeNode.class));
		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Please select a Row!");
	}

	@Test
	public void testAddParticipantToRelay_ForMemberIsSelected() {
		ActionEvent dummyActionEvent = null;

		Member member = Member.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(member, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		Participant participant = Participant.newInstance(justusJonas);
		sut.setSelectedParticipants(createListFor(participant));

		sut.addParticipantToRelay(dummyActionEvent);

		TreeNodeRow selectedRelayNode = (TreeNodeRow) relayTreeNode.getData();
		Member actual = selectedRelayNode.getMember();

		assertEquals("UUID not correct!", justusJonas.getUuid(), actual.getUuidPerson());
		verify(relayBridge).set(any(TreeNode.class));
		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
	}

	private List<Participant> createListFor(Participant... participant) {
		List<Participant> someParticipants = new ArrayList<Participant>();
		someParticipants.addAll(Arrays.asList(participant));
		return someParticipants;
	}

	@Test
	public void testAddParticipantToRelay_ForRelayIsSelected() {
		ActionEvent dummyActionEvent = null;

		Relay relay = Relay.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay));
		sut.setSelectedNode(relayTreeNode);

		sut.addParticipantToRelay(dummyActionEvent);

		verify(relayBridge, never()).set(any(TreeNode.class));
		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Only for Member Row possible!");
	}

	@Test
	public void testRemovePersonFromRelay_ForNoRowIsSelected() {
		ActionEvent dummyActionEvent = null;

		sut.removeParticipantFromRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Please select a Row!");
		verify(relayBridge, never()).set(any(TreeNode.class));
	}

	@Test
	public void testRemoveParticipantFromRelay_ForRelayRowIsSelected() {
		ActionEvent dummyActionEvent = null;
		Relay relay = Relay.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removeParticipantFromRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Only for Member Row possible!");
		verify(relayBridge, never()).set(any(TreeNode.class));
	}

	@Test
	public void testRemoveParticipantFromRelay_ForMemberRowIsSelected() {
		ActionEvent dummyActionEvent = null;
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		Participant participant = Participant.newInstance(person);

		Member personRelay = Member.newInstance(participant);

		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(personRelay, Position.FIRST), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removeParticipantFromRelay(dummyActionEvent);

		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
		verify(relayBridge).set(any(TreeNode.class));
	}

	@Test
	public void testRemoveParticipantFromRelay_ForRelayMemberRowIsSelectedAndEmpty() {
		ActionEvent dummyActionEvent = null;
		Member personRelay = Member.newInstance(null);
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(personRelay, Position.FIRST), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removeParticipantFromRelay(dummyActionEvent);

		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
		verify(relayBridge, never()).set(any(TreeNode.class));
	}

	@Test
	public void testIsRelayRowSelected_ForNoRowSelected() {
		boolean condition = sut.isRelayRowSelected();

		assertFalse("Relay selected not correct!", condition);
	}

	@Test
	public void testIsRelayRowSelected_ForRowMemberSelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Member.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayRowSelected();

		assertFalse("Relay selected not correct!", condition);
	}

	@Test
	public void testIsRelayRowSelected_ForRowRelaySelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayRowSelected();

		assertTrue("Relay selected not correct!", condition);
	}

	@Test
	public void testIsRelayTableRowSelected() {
		sut.setSelectedNode(null);

		boolean condition = sut.isRelayTableRowSelected();

		assertFalse("RelayTableRow selected not corret!", condition);
	}

	@Test
	public void testIsMemberRowSelected_ForRowMemberSelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Member.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayTableRowSelected();

		assertTrue("RelayTableRow selected not correct!", condition);
	}

	@Test
	public void testIsMemberRowSelected_ForRowRelaySelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayTableRowSelected();

		assertTrue("RelayTableRow selected not correct!", condition);
	}

	@Test
	public void testEditRow_ForNonSelectedRow() {
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(relayEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEditRow_ForSelectedRowMember() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Member.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(memberEditPageBean).openDialogFor(any(UUID.class));

		verify(relayEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut, never()).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEditRow_ForSelectedRowRelay() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(relayEditPageBean).openDialogFor(any(UUID.class));

		verify(memberEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut, never()).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEmailExport_ForNonSelectedRelay() {
		sut.setSelectedNode(null);

		ActionEvent dummyActionEvent = null;

		sut.emailExportRelay(dummyActionEvent);

		verify(sut).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEmailExport_ForSelectedRelay() {
		Relay relayMock = Mockito.mock(Relay.class);
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relayMock));
		sut.setSelectedNode(selectedTreeNode);

		ActionEvent dummyActionEvent = null;

		sut.emailExportRelay(dummyActionEvent);

		verify(relayMock).getEmailList();
		verify(sut, never()).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testShowAllWithoutRelay() {
		sut.showAllWithoutRelay();
		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, "Not implemented yet!");
	}
	
	@Test
	public void testSortByDuration_both_sort_elements_have_wrong_type() {
		int expected = 0;
		TreeNodeRow firstTreeNodeRow = TreeNodeRowMember.newInstance(null, null);
		TreeNodeRow secondTreeNodeRow = TreeNodeRowMember.newInstance(null, null);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}

	@Test
	public void testSortByDuration_first_sort_element_has_wrong_type() {
		int expected = 0;
		TreeNodeRow firstTreeNodeRow = TreeNodeRowMember.newInstance(null, null);
		TreeNodeRow secondTreeNodeRow = TreeNodeRowRelay.newInstance(null);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}

	@Test
	public void testSortByDuration_second_sort_element_has_wrong_type() {
		int expected = 0;
		TreeNodeRow firstTreeNodeRow = TreeNodeRowRelay.newInstance(null);
		TreeNodeRow secondTreeNodeRow = TreeNodeRowMember.newInstance(null, null);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}

	@Test
	public void testSortByDuration_both_sort_elements_are_equal() {
		int expected = 0;
		Relay firstRelay = Relay.newInstance();
		firstRelay.setDuration(Duration.ZERO);
		TreeNodeRow firstTreeNodeRow = TreeNodeRowRelay.newInstance(firstRelay);
		Relay secondRelay = Relay.newInstance();
		secondRelay.setDuration(Duration.ZERO);
		TreeNodeRow secondTreeNodeRow = TreeNodeRowRelay.newInstance(secondRelay);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}

	@Test
	public void testSortByDuration_first_sort_element_is_greater() {
		int expected = 1;
		Relay firstRelay = Relay.newInstance();
		firstRelay.setDuration(Duration.ofHours(1l));
		TreeNodeRow firstTreeNodeRow = TreeNodeRowRelay.newInstance(firstRelay);
		Relay secondRelay = Relay.newInstance();
		secondRelay.setDuration(Duration.ZERO);
		TreeNodeRow secondTreeNodeRow = TreeNodeRowRelay.newInstance(secondRelay);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}

	@Test
	public void testSortByDuration_second_sort_element_is_greater() {
		int expected = -1;
		Relay firstRelay = Relay.newInstance();
		firstRelay.setDuration(Duration.ZERO);
		TreeNodeRow firstTreeNodeRow = TreeNodeRowRelay.newInstance(firstRelay);
		Relay secondRelay = Relay.newInstance();
		secondRelay.setDuration(Duration.ofHours(1l));
		TreeNodeRow secondTreeNodeRow = TreeNodeRowRelay.newInstance(secondRelay);
		
		int actual = sut.sortByDuration(firstTreeNodeRow, secondTreeNodeRow);
		
		assertEquals("Sorting by 'duration' was not correct!", expected, actual);
	}
}