package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

import static org.mockito.Mockito.*;

/**
 * Besser ist nicht Veränderung um Ihrer selbst willen - besser ist besser!
 *  - Philip Toshio Sudo
 *
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberEditPageBeanTest {
	@InjectMocks
	@Spy
	private MemberEditPageBean sut;

	@Mock
	private MemberBridge memberBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).openDialog();
		doNothing().when(sut).closeDialog();
	}

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testOpenDialogFor() {
		UUID uuid = UUID.fromString("12345-42-32-23-32");
		doReturn(Member.newInstance()).when(sut).getMember(uuid);

		assertNull("[workingMember] not corret!", sut.workingMember);

		sut.openDialogFor(uuid);

		assertNotNull("[workingMember] not corret!", sut.workingMember);
		verify(sut).getMember(uuid);
		verify(sut).openDialog();
	}

	@Test
	public void testSave() {

		sut.save();

		verify(sut).persistMember();
		verify(sut).closeDialog();
	}

	@Test
	public void testCancel() {
		sut.cancel();

		verify(sut, never()).persistMember();
		verify(sut).closeDialog();
	}

	@Test
	public void testGetName() {
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		Member member = Member.newInstance(person);
		sut.workingMember = member;

		String actual = sut.getName();
		String expected = "Justus Jonas";

		assertEquals("[getName] not correct!", expected, actual);
	}

	@Test
	public void testDuration() {
		Duration expected = Duration.ZERO;
		Member memberMock = Mockito.mock(Member.class);
		doReturn(expected).when(memberMock).getDuration();
		sut.workingMember = memberMock;

		sut.setDuration(expected);
		verify(memberMock).setDuration(expected);

		Duration actual = sut.getDuration();
		verify(memberMock).getDuration();
		assertEquals("[duration] not correct!", expected, actual);
	}
}
