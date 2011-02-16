package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.SupportingDocuments;

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

public abstract class SupportingDocumentsActionBase
		extends
			BaseAction<SupportingDocuments> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private SupportingDocuments supportingDocuments;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentAction;

	@DataModel
	private List<SupportingDocuments> supportingDocumentsRecordList;

	public void setSupportingDocumentsId(Long id) {
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
	public void setSupportingDocumentsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setIncidentId(Long id) {

		if (id != null && id > 0)
			getInstance().setIncident(incidentAction.loadFromId(id));

	}

	public Long getIncidentId() {
		if (getInstance().getIncident() != null)
			return getInstance().getIncident().getId();
		return 0L;
	}

	public Long getSupportingDocumentsId() {
		return (Long) getId();
	}

	public SupportingDocuments getEntity() {
		return supportingDocuments;
	}

	//@Override
	public void setEntity(SupportingDocuments t) {
		this.supportingDocuments = t;
		loadAssociations();
	}

	public SupportingDocuments getSupportingDocuments() {
		return (SupportingDocuments) getInstance();
	}

	@Override
	protected SupportingDocuments createInstance() {
		return new SupportingDocuments();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.incidents.Incident incident = incidentAction
				.getDefinedInstance();
		if (incident != null && isNew()) {
			getInstance().setIncident(incident);
		}

	}

	public boolean isWired() {
		return true;
	}

	public SupportingDocuments getDefinedInstance() {
		return (SupportingDocuments) (isIdDefined() ? getInstance() : null);
	}

	public void setSupportingDocuments(SupportingDocuments t) {
		this.supportingDocuments = t;
		loadAssociations();
	}

	@Override
	public Class<SupportingDocuments> getEntityClass() {
		return SupportingDocuments.class;
	}

	public String downloadFile(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getFile());
		return "success";
	}

	public void fileUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getFile() == null)
			getInstance().setFile(new FileAttachment());
		getInstance().getFile().setName(uploadItem.getFileName());
		getInstance().getFile().setContentType(uploadItem.getContentType());
		getInstance().getFile().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (supportingDocuments.getIncident() != null) {
			criteria = criteria.add(Restrictions.eq("incident.id",
					supportingDocuments.getIncident().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (supportingDocuments.getIncident() != null) {
			incidentAction.setInstance(getInstance().getIncident());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
