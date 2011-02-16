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

public class UnusualOccurenceWorkflowProcessActionBase
		extends
			BaseJbpmProcessAction implements java.io.Serializable {

	@CreateProcess(definition = "unusualOccurenceWorkflow")
	public void startProcess() {

	}

	@StartTask
	public void startReviewOccurence() {

	}

	@EndTask(transition = "requestMoreInfo")
	public void requestMoreInfoReviewOccurence() {

	}
	@EndTask(transition = "closeOccurence")
	public void closeOccurenceReviewOccurence() {

	}

	@StartTask
	public void startProvideInfo() {

	}

	@EndTask(transition = "done")
	public void doneProvideInfo() {

	}

}
