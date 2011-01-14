
	
package com.nas.recovery.web.action.workflowmgt;


	import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.CreateProcess;


@Name("unusualOccurenceWorkflowProcessAction")	
//@Scope(ScopeType.CONVERSATION)
public class UnusualOccurenceWorkflow extends UnusualOccurenceWorkflowBase {

	@CreateProcess(definition = "unusualOccurenceWorkflow" 
	
 )
	public void startProcess() {
	
	}
}
