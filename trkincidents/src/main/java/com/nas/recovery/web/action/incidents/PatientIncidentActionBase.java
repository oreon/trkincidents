package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.PatientIncident;

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

public abstract class PatientIncidentActionBase
		extends
			BaseAction<PatientIncident> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PatientIncident patientIncident;

	@In(create = true, value = "patientAction")
	com.nas.recovery.web.action.patient.PatientAction patientAction;

	@DataModel
	private List<PatientIncident> patientIncidentRecordList;

	public void setPatientIncidentId(Long id) {
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
	public void setPatientIncidentIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
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

	public Long getPatientIncidentId() {
		return (Long) getId();
	}

	public PatientIncident getEntity() {
		return patientIncident;
	}

	//@Override
	public void setEntity(PatientIncident t) {
		this.patientIncident = t;
		loadAssociations();
	}

	public PatientIncident getPatientIncident() {
		return (PatientIncident) getInstance();
	}

	@Override
	protected PatientIncident createInstance() {
		return new PatientIncident();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PatientIncident getDefinedInstance() {
		return (PatientIncident) (isIdDefined() ? getInstance() : null);
	}

	public void setPatientIncident(PatientIncident t) {
		this.patientIncident = t;
		loadAssociations();
	}

	@Override
	public Class<PatientIncident> getEntityClass() {
		return PatientIncident.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (patientIncident.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id",
					patientIncident.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (patientIncident.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
