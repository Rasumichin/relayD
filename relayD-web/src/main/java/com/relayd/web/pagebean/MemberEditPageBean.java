package com.relayd.web.pagebean;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Member;
import com.relayd.web.bridge.MemberBridge;
import com.relayd.web.bridge.MemberBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
@ManagedBean(name = "memberEditPageBean")
@SessionScoped
public class MemberEditPageBean implements Serializable {
	private static final long serialVersionUID = 6024842613034029467L;

	Member workingMember;

	private MemberBridge memberBridge;

	public MemberEditPageBean() {
		memberBridge = new MemberBridgeImpl();
	}

	public void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(140).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.MEMBER_EDIT_DIALOG_ID, options, null);
	}

	void openDialogFor(UUID uuid) {
		workingMember = getMember(uuid);
		openDialog();
	}

	Member getMember(UUID uuid) {
		return getBridge().get(uuid);
	}

	private MemberBridge getBridge() {
		return memberBridge;
	}

	public void save() {
		persistMember();
		closeDialog();
	}

	void persistMember() {
		getBridge().set(workingMember);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingMember);
	}

	public void cancel() {
		closeDialog();
	}

	public String getName() {
		return workingMember.getForename() + " " + workingMember.getSurename();
	}

	public void setDuration(Duration aDuration) {
		workingMember.setDuration(aDuration);
	}

	public Duration getDuration() {
		return workingMember.getDuration();
	}
}