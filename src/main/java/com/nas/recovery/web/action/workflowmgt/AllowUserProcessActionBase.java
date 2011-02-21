package com.nas.recovery.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class AllowUserProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@CreateProcess(definition = "allowUser")
	public void startProcess() {

	}

	@StartTask
	public void startReviewUserJoinRequest() {

	}

	@EndTask(transition = "allow")
	public void allowReviewUserJoinRequest() {

	}
	@EndTask(transition = "disallow")
	public void disallowReviewUserJoinRequest() {

	}

}
