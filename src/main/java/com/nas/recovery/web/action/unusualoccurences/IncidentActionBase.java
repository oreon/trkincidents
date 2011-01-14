package com.nas.recovery.web.action.unusualoccurences;

import com.oreon.trkincidents.unusualoccurences.Incident;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class IncidentActionBase extends BaseAction<Incident>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Incident incident;

	@In(create = true, value = "occurenceTypeAction")
	com.nas.recovery.web.action.unusualoccurences.OccurenceTypeAction occurenceTypeAction;

	@In(create = true, value = "patientAction")
	com.nas.recovery.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction createdByAction;

	@DataModel
	private List<Incident> incidentRecordList;

	public void setIncidentId(Long id) {
		if (id == 0) {
			clearInstance();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setIncidentIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setOccurenceTypeId(Long id) {

		if (id != null && id > 0)
			getInstance().setOccurenceType(occurenceTypeAction.loadFromId(id));

	}

	public Long getOccurenceTypeId() {
		if (getInstance().getOccurenceType() != null)
			return getInstance().getOccurenceType().getId();
		return 0L;
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public void setCreatedById(Long id) {

		if (id != null && id > 0)
			getInstance().setCreatedBy(createdByAction.loadFromId(id));

	}

	public Long getCreatedById() {
		if (getInstance().getCreatedBy() != null)
			return getInstance().getCreatedBy().getId();
		return 0L;
	}

	public Long getIncidentId() {
		return (Long) getId();
	}

	public Incident getEntity() {
		return incident;
	}

	//@Override
	public void setEntity(Incident t) {
		this.incident = t;
		loadAssociations();
	}

	public Incident getIncident() {
		return (Incident) getInstance();
	}

	@Override
	protected Incident createInstance() {
		return new Incident();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.unusualoccurences.OccurenceType occurenceType = occurenceTypeAction
				.getDefinedInstance();
		if (occurenceType != null && isNew()) {
			getInstance().setOccurenceType(occurenceType);
		}

		com.oreon.trkincidents.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.trkincidents.employee.Employee createdBy = createdByAction
				.getDefinedInstance();
		if (createdBy != null && isNew()) {
			getInstance().setCreatedBy(createdBy);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Incident getDefinedInstance() {
		return (Incident) (isIdDefined() ? getInstance() : null);
	}

	public void setIncident(Incident t) {
		this.incident = t;
		loadAssociations();
	}

	@Override
	public Class<Incident> getEntityClass() {
		return Incident.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (incident.getOccurenceType() != null) {
			criteria = criteria.add(Restrictions.eq("occurenceType.id",
					incident.getOccurenceType().getId()));
		}

		if (incident.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", incident
					.getPatient().getId()));
		}

		if (incident.getCreatedBy() != null) {
			criteria = criteria.add(Restrictions.eq("createdBy.id", incident
					.getCreatedBy().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (incident.getOccurenceType() != null) {
			occurenceTypeAction.setInstance(getInstance().getOccurenceType());
		}

		if (incident.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

		if (incident.getCreatedBy() != null) {
			createdByAction.setInstance(getInstance().getCreatedBy());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
