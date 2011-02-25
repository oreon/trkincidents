package com.nas.recovery.web.action.workflowmgt.unusualOccurenceWorkflow.assign;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.oreon.trkincidents.incidents.Incident;

public class IncidentReviewerAssignment implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		
		Incident issueToken = (Incident) executionContext.getVariable("processToken");
		if (issueToken.getReportedTo() != null)
			assignable.setActorId(issueToken.getReportedTo().getUser()
					.getUserName());
		else
			assignable.setPooledActors(new String[] { "manager" });

	}

}
