package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Incident;

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

import com.oreon.trkincidents.incidents.Incident;

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

	private Range<Date> dateOfIncidentRange = new Range<Date>();
	public Range<Date> getDateOfIncidentRange() {
		return dateOfIncidentRange;
	}
	public void setDateOfIncident(Range<Date> dateOfIncidentRange) {
		this.dateOfIncidentRange = dateOfIncidentRange;
	}

	private static final String[] RESTRICTIONS = {
			"incident.id = #{incidentList.incident.id}",

			"incident.incidentType.id = #{incidentList.incident.incidentType.id}",

			"lower(incident.title) like concat(lower(#{incidentList.incident.title}),'%')",

			"incident.patient.id = #{incidentList.incident.patient.id}",

			"incident.createdBy.id = #{incidentList.incident.createdBy.id}",

			"incident.department.id = #{incidentList.incident.department.id}",

			"incident.dateOfIncident >= #{incidentList.dateOfIncidentRange.begin}",
			"incident.dateOfIncident <= #{incidentList.dateOfIncidentRange.end}",

			"incident.reportedTo.id = #{incidentList.incident.reportedTo.id}",

			"incident.drug.id = #{incidentList.incident.drug.id}",

			"incident.proccedure.id = #{incidentList.incident.proccedure.id}",

			"incident.responsibleEmployee.id = #{incidentList.incident.responsibleEmployee.id}",

			"lower(incident.description) like concat(lower(#{incidentList.incident.description}),'%')",

			"incident.severity.id = #{incidentList.incident.severity.id}",

			"incident.dateCreated <= #{incidentList.dateCreatedRange.end}",
			"incident.dateCreated >= #{incidentList.dateCreatedRange.begin}",};

	public List<Incident> getIncidentsByPatient(
			com.oreon.trkincidents.patient.Patient patient) {
		//setMaxResults(10000);
		incident.setPatient(patient);
		return getResultList();
	}

	public List<Incident> getIncidentsCreatedByCreatedBy(
			com.oreon.trkincidents.employee.Employee employee) {
		//setMaxResults(10000);
		incident.setCreatedBy(employee);
		return getResultList();
	}

	public List<Incident> getIncidentsByDepartment(
			com.oreon.trkincidents.employee.Department department) {
		//setMaxResults(10000);
		incident.setDepartment(department);
		return getResultList();
	}

	public List<Incident> getIncidentsByDrug(
			com.oreon.trkincidents.incidents.Drug drug) {
		//setMaxResults(10000);
		incident.setDrug(drug);
		return getResultList();
	}

	public List<Incident> getIncidentsByProccedure(
			com.oreon.trkincidents.incidents.Proccedure proccedure) {
		//setMaxResults(10000);
		incident.setProccedure(proccedure);
		return getResultList();
	}

	public List<Incident> getIncidentsResponsibleForByResponsibleEmployee(
			com.oreon.trkincidents.employee.Employee employee) {
		//setMaxResults(10000);
		incident.setResponsibleEmployee(employee);
		return getResultList();
	}

	@Observer("archivedIncident")
	public void onArchive() {
		refresh();
	}

}
