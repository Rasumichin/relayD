package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Participant;
import com.relayd.attributes.Comment;
import com.relayd.web.bridge.ParticipantBridge;
import com.relayd.web.bridge.ParticipantBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.10.2017
 *
 */
@ManagedBean(name = "participantEditPageBean")
@SessionScoped
public class ParticipantEditPageBean implements Serializable {
	private static final long serialVersionUID = -8750740596662373383L;

	private Participant workingParticipant;

	private ParticipantBridge participantBridge;

	public ParticipantEditPageBean() {
		participantBridge = new ParticipantBridgeImpl();
	}

	void openDialogFor(UUID uuid) {
		workingParticipant = getParticipant(uuid);
		openDialog();
	}

	private Participant getParticipant(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(240).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.PARTICIPANT_EDIT_DIALOG_ID, options, null);
	}

	public void save() {
		persistParticipant();
		closeDialog();
	}

	void persistParticipant() {
		getBridge().set(workingParticipant);
	}

	public void cancel() {
		closeDialog();
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingParticipant);
	}

	public String getName() {
		return workingParticipant.getForename() + " " + workingParticipant.getSurename();
	}

	public void setComment(Comment comment) {
		workingParticipant.setComment(comment);
	}

	public Comment getComment() {
		return workingParticipant.getComment();
	}

	private ParticipantBridge getBridge() {
		return participantBridge;
	}

	public Integer getMaxLengthForComment() {
		return Comment.MAX_LENGTH;
	}

}