package com.nas.recovery.web.action.incidents;

import java.util.Set;

import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.FormField;
import com.oreon.trkincidents.incidents.FormFieldInstance;
import com.oreon.trkincidents.incidents.IncidentType;

//@Scope(ScopeType.CONVERSATION)
@Name("incidentAction")
public class IncidentAction extends IncidentActionBase implements
		java.io.Serializable {
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
