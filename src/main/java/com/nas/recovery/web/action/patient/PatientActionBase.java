package com.nas.recovery.web.action.patient;

import com.oreon.trkincidents.patient.Patient;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.oreon.trkincidents.incidents.Incident;
import com.oreon.trkincidents.patient.Document;

public abstract class PatientActionBase
		extends
			com.nas.recovery.web.action.patient.PersonAction<Patient>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentsAction;

	@In(create = true, value = "documentAction")
	com.nas.recovery.web.action.patient.DocumentAction documentsAction;

	@DataModel
	private List<Patient> patientRecordList;

	public void setPatientId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
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
	public void setPatientIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getPatientId() {
		return (Long) getId();
	}

	public Patient getEntity() {
		return patient;
	}

	//@Override
	public void setEntity(Patient t) {
		this.patient = t;
		loadAssociations();
	}

	public Patient getPatient() {
		return (Patient) getInstance();
	}

	@Override
	protected Patient createInstance() {
		return new Patient();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Patient getDefinedInstance() {
		return (Patient) (isIdDefined() ? getInstance() : null);
	}

	public void setPatient(Patient t) {
		this.patient = t;
		loadAssociations();
	}

	@Override
	public Class<Patient> getEntityClass() {
		return Patient.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListIncidents();

		initListDocuments();

	}

	public void updateAssociations() {

		com.oreon.trkincidents.incidents.Incident incidents = (com.oreon.trkincidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidents.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedIncident");

		com.oreon.trkincidents.patient.Document documents = (com.oreon.trkincidents.patient.Document) org.jboss.seam.Component
				.getInstance("document");
		documents.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedDocument");

	}

	protected List<com.oreon.trkincidents.incidents.Incident> listIncidents = new ArrayList<com.oreon.trkincidents.incidents.Incident>();

	void initListIncidents() {

		if (listIncidents.isEmpty())
			listIncidents.addAll(getInstance().getIncidents());

	}

	public List<com.oreon.trkincidents.incidents.Incident> getListIncidents() {

		prePopulateListIncidents();
		return listIncidents;
	}

	public void prePopulateListIncidents() {
	}

	public void setListIncidents(
			List<com.oreon.trkincidents.incidents.Incident> listIncidents) {
		this.listIncidents = listIncidents;
	}

	public void deleteIncidents(int index) {
		listIncidents.remove(index);
	}

	@Begin(join = true)
	public void addIncidents() {
		initListIncidents();
		Incident incidents = new Incident();

		incidents.setPatient(getInstance());

		getListIncidents().add(incidents);
	}

	protected List<com.oreon.trkincidents.patient.Document> listDocuments = new ArrayList<com.oreon.trkincidents.patient.Document>();

	void initListDocuments() {

		if (listDocuments.isEmpty())
			listDocuments.addAll(getInstance().getDocuments());

	}

	public List<com.oreon.trkincidents.patient.Document> getListDocuments() {

		prePopulateListDocuments();
		return listDocuments;
	}

	public void prePopulateListDocuments() {
	}

	public void setListDocuments(
			List<com.oreon.trkincidents.patient.Document> listDocuments) {
		this.listDocuments = listDocuments;
	}

	public void deleteDocuments(int index) {
		listDocuments.remove(index);
	}

	@Begin(join = true)
	public void addDocuments() {
		initListDocuments();
		Document documents = new Document();

		documents.setPatient(getInstance());

		getListDocuments().add(documents);
	}

	public void updateComposedAssociations() {

		if (listIncidents != null) {
			getInstance().getIncidents().clear();
			getInstance().getIncidents().addAll(listIncidents);
		}

		if (listDocuments != null) {
			getInstance().getDocuments().clear();
			getInstance().getDocuments().addAll(listDocuments);
		}
	}

	public void clearLists() {
		listIncidents.clear();
		listDocuments.clear();

	}

}
