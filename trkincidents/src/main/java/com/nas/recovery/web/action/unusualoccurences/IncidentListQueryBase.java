package com.nas.recovery.web.action.unusualoccurences;

import com.oreon.trkincidents.unusualoccurences.Incident;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.trkincidents.unusualoccurences.Incident;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class IncidentListQueryBase extends BaseQuery<Incident, Long> {

	private static final String EJBQL = "select incident from Incident incident";

	protected Incident incident = new Incident();

	public Incident getIncident() {
		return incident;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Incident> getEntityClass() {
		return Incident.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"incident.id = #{incidentList.incident.id}",

			"incident.occurenceType.id = #{incidentList.incident.occurenceType.id}",

			"incident.category = #{incidentList.incident.category}",

			"incident.severity = #{incidentList.incident.severity}",

			"lower(incident.description) like concat(lower(#{incidentList.incident.description}),'%')",

			"incident.patient.id = #{incidentList.incident.patient.id}",

			"incident.createdBy.id = #{incidentList.incident.createdBy.id}",

			"incident.dateCreated <= #{incidentList.dateCreatedRange.end}",
			"incident.dateCreated >= #{incidentList.dateCreatedRange.begin}",};

	public List<Incident> getIncidentsByPatient(
			com.oreon.trkincidents.patient.Patient patient) {
		//setMaxResults(10000);
		incident.setPatient(patient);
		return getResultList();
	}

	public List<Incident> getIncidentsByCreatedBy(
			com.oreon.trkincidents.employee.Employee employee) {
		//setMaxResults(10000);
		incident.setCreatedBy(employee);
		return getResultList();
	}

	@Observer("archivedIncident")
	public void onArchive() {
		refresh();
	}

}
