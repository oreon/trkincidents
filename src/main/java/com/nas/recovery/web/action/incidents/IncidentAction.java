package com.nas.recovery.web.action.incidents;

import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.web.action.employee.EmployeeAction;
import com.nas.recovery.web.action.workflowmgt.UnusualOccurenceWorkflowProcessAction;
import com.oreon.trkincidents.incidents.FormField;
import com.oreon.trkincidents.incidents.FormFieldInstance;
import com.oreon.trkincidents.incidents.Incident;
import com.oreon.trkincidents.incidents.ReferenceField;

//@Scope(ScopeType.CONVERSATION)
@Name("incidentAction")
public class IncidentAction extends IncidentActionBase implements
		java.io.Serializable {

	@In(create = true)
	EmployeeAction employeeAction;
	
	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	Incident processToken;
	
	@In(create = true)
	UnusualOccurenceWorkflowProcessAction unusualOccurenceWorkflowProcessAction;

	@Override
	public String save() {
		getInstance().setCreatedBy(employeeAction.getCurrentLoggedInEmployee());
		boolean isnew = isNew();
		String ret = super.save();
		if (isnew) {
			unusualOccurenceWorkflowProcessAction.setProcessToken(this.getInstance());
			unusualOccurenceWorkflowProcessAction.startProcess();
		}
		return ret;
	}

	public boolean referenceFieldRequired(String referenceName) {
		if (getInstance().getIncidentType() == null)
			return false;

		Set<ReferenceField> refFlds = getInstance().getIncidentType()
				.getReferenceFields();

		for (ReferenceField referenceField : refFlds) {
			if (referenceField.getReferencesEntity().getName().equals(
					referenceName))
				return referenceField.getRequired();
		}
		return false;

	}

	public boolean containsReference(String referenceName) {

		if (getInstance().getIncidentType() == null)
			return false;
		Set<ReferenceField> refFlds = getInstance().getIncidentType()
				.getReferenceFields();

		for (ReferenceField referenceField : refFlds) {
			if (referenceField.getReferencesEntity().getName().equals(
					referenceName))
				return true;
		}
		return false;
	}

	@Override
	public void prePopulateListFormFieldInstances() {

		if (getInstance().getId() == null) {
			//IncidentType form = getInstance().getIncidentType();
			if (getInstance().getIncidentType() == null)
				return;
			listFormFieldInstances.clear();
			Set<FormField> flds = getInstance().getIncidentType()
					.getFormFields();
			for (FormField fld : flds) {
				FormFieldInstance ffld = new FormFieldInstance();
				ffld.setFormField(fld);
				ffld.setIncident(this.getInstance());

				listFormFieldInstances.add(ffld);
			}
		}
	}
	
	@Override
	public void saveComment() {
		sendMail("mailTemplates/commentAdded.xhtml");
		super.saveComment();
	}
	


}
