package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Icd10;

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

public abstract class Icd10ActionBase extends BaseAction<Icd10>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Icd10 icd10;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentsAction;

	@DataModel
	private List<Icd10> icd10RecordList;

	public void setIcd10Id(Long id) {
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
	public void setIcd10IdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getIcd10Id() {
		return (Long) getId();
	}

	public Icd10 getEntity() {
		return icd10;
	}

	//@Override
	public void setEntity(Icd10 t) {
		this.icd10 = t;
		loadAssociations();
	}

	public Icd10 getIcd10() {
		return (Icd10) getInstance();
	}

	@Override
	protected Icd10 createInstance() {
		return new Icd10();
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

	public Icd10 getDefinedInstance() {
		return (Icd10) (isIdDefined() ? getInstance() : null);
	}

	public void setIcd10(Icd10 t) {
		this.icd10 = t;
		loadAssociations();
	}

	@Override
	public Class<Icd10> getEntityClass() {
		return Icd10.class;
	}

	public com.oreon.trkincidents.incidents.Icd10 findByUnqCode(String code) {
		return executeSingleResultNamedQuery("icd10.findByUnqCode", code);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListIncidents();

	}

	public void updateAssociations() {

		com.oreon.trkincidents.incidents.Incident incidents = (com.oreon.trkincidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidents.setIcd10(icd10);
		events.raiseTransactionSuccessEvent("archivedIncident");

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

		incidents.setIcd10(getInstance());

		getListIncidents().add(incidents);
	}

	public void updateComposedAssociations() {

		if (listIncidents != null) {
			getInstance().getIncidents().clear();
			getInstance().getIncidents().addAll(listIncidents);
		}
	}

	public void clearLists() {
		listIncidents.clear();

	}

}
