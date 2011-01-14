package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.PatientIncident;

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

import com.oreon.trkincidents.incidents.PatientIncident;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class PatientIncidentListQueryBase
		extends
			BaseQuery<PatientIncident, Long> {

	private static final String EJBQL = "select patientIncident from PatientIncident patientIncident";

	protected PatientIncident patientIncident = new PatientIncident();

	public PatientIncident getPatientIncident() {
		return patientIncident;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<PatientIncident> getEntityClass() {
		return PatientIncident.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientIncident.id = #{patientIncidentList.patientIncident.id}",

			"patientIncident.severity.id = #{patientIncidentList.patientIncident.severity.id}",

			"lower(patientIncident.description) like concat(lower(#{patientIncidentList.patientIncident.description}),'%')",

			"patientIncident.patient.id = #{patientIncidentList.patientIncident.patient.id}",

			"patientIncident.dateCreated <= #{patientIncidentList.dateCreatedRange.end}",
			"patientIncident.dateCreated >= #{patientIncidentList.dateCreatedRange.begin}",};

	public List<PatientIncident> getPatientIncidentsByPatient(
			com.oreon.trkincidents.patient.Patient patient) {
		//setMaxResults(10000);
		patientIncident.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedPatientIncident")
	public void onArchive() {
		refresh();
	}

}
