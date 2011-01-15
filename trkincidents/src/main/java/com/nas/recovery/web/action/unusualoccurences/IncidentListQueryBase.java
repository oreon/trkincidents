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

	private com.oreon.trkincidents.patient.Patient patientToSearch;

	public void setPatientToSearch(
			com.oreon.trkincidents.patient.Patient patientToSearch) {
		this.patientToSearch = patientToSearch;
	}

	public com.oreon.trkincidents.patient.Patient getPatientToSearch() {
		return patientToSearch;
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

			"incident.severity = #{incidentList.incident.severity}",

			"#{incidentList.patientToSearch} in elements(incident.patients)",

			"incident.createdBy.id = #{incidentList.incident.createdBy.id}",

			"lower(incident.description) like concat(lower(#{incidentList.incident.description}),'%')",

			"incident.department.id = #{incidentList.incident.department.id}",

			"incident.dateOfIncident >= #{incidentList.dateOfIncidentRange.begin}",
			"incident.dateOfIncident <= #{incidentList.dateOfIncidentRange.end}",

			"incident.reportedTo.id = #{incidentList.incident.reportedTo.id}",

			"incident.dateCreated <= #{incidentList.dateCreatedRange.end}",
			"incident.dateCreated >= #{incidentList.dateCreatedRange.begin}",};

	public List<Incident> getIncidentsByCreatedBy(
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

	@Observer("archivedIncident")
	public void onArchive() {
		refresh();
	}

}
