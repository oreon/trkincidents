package com.nas.recovery.web.action.unusualoccurences;

import java.util.Set;

import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.CustomField;
import com.oreon.trkincidents.customforms.FilledField;
import com.oreon.trkincidents.unusualoccurences.FormField;
import com.oreon.trkincidents.unusualoccurences.FormFieldInstance;
import com.oreon.trkincidents.unusualoccurences.IncidentType;

//@Scope(ScopeType.CONVERSATION)
@Name("incidentAction")
public class IncidentAction extends IncidentActionBase implements
		java.io.Serializable {
	
	
	
	@Override
	public void prePopulateListFormFieldInstances() {
		
		if(getInstance().getId() == null && listFormFieldInstances.isEmpty()){
			IncidentType form = getInstance().getIncidentType();
			if(getInstance().getIncidentType() == null)
				return;
			Set<FormField> flds = getInstance().getIncidentType().getFormFields();
			for (FormField fld : flds) {
				FormFieldInstance ffld = new FormFieldInstance();
				ffld.setFormField(fld);
				ffld.setIncident(this.getInstance());
				listFormFieldInstances.add(ffld);
			}
		}
	}

}
