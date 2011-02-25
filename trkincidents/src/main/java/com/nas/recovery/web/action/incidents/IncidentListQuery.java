
package com.nas.recovery.web.action.incidents;



import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.trkincidents.incidents.Incident;
	
	
@Name("incidentList")
@Scope(ScopeType.CONVERSATION)
public class IncidentListQuery extends IncidentListQueryBase implements java.io.Serializable{
	
	@Override
	public Incident getIncident() {
		if (!isPostBack()) {
			incident.setDateOfIncident(null);
		}
		return incident;
	}
	
}
