package com.nas.recovery.web.action.incidents;

import java.util.Set;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.web.action.employee.EmployeeAction;
import com.oreon.trkincidents.incidents.FormField;
import com.oreon.trkincidents.incidents.FormFieldInstance;
import com.oreon.trkincidents.incidents.IncidentType;
import com.oreon.trkincidents.incidents.ReferenceField;

//@Scope(ScopeType.CONVERSATION)
@Name("incidentAction")
public class IncidentAction extends IncidentActionBase implements
		java.io.Serializable {

	@In(create = true)
	EmployeeAction employeeAction;

	@Override
	public String doSave() {
		getInstance().setCreatedBy(employeeAction.getCurrentLoggedInEmployee());
		return super.doSave();
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

		if (getInstance().getId() == null && listFormFieldInstances.isEmpty()) {
			IncidentType form = getInstance().getIncidentType();
			if (getInstance().getIncidentType() == null)
				return;
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
}
