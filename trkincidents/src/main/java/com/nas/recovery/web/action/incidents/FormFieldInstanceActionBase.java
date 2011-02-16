package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.FormFieldInstance;

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

public abstract class FormFieldInstanceActionBase
		extends
			BaseAction<FormFieldInstance> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FormFieldInstance formFieldInstance;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentAction;

	@In(create = true, value = "formFieldAction")
	com.nas.recovery.web.action.incidents.FormFieldAction formFieldAction;

	@DataModel
	private List<FormFieldInstance> formFieldInstanceRecordList;

	public void setFormFieldInstanceId(Long id) {
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
	public void setFormFieldInstanceIdForModalDlg(Long id) {
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

	public void setFormFieldId(Long id) {

		if (id != null && id > 0)
			getInstance().setFormField(formFieldAction.loadFromId(id));

	}

	public Long getFormFieldId() {
		if (getInstance().getFormField() != null)
			return getInstance().getFormField().getId();
		return 0L;
	}

	public Long getFormFieldInstanceId() {
		return (Long) getId();
	}

	public FormFieldInstance getEntity() {
		return formFieldInstance;
	}

	//@Override
	public void setEntity(FormFieldInstance t) {
		this.formFieldInstance = t;
		loadAssociations();
	}

	public FormFieldInstance getFormFieldInstance() {
		return (FormFieldInstance) getInstance();
	}

	@Override
	protected FormFieldInstance createInstance() {
		return new FormFieldInstance();
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

		com.oreon.trkincidents.incidents.FormField formField = formFieldAction
				.getDefinedInstance();
		if (formField != null && isNew()) {
			getInstance().setFormField(formField);
		}

	}

	public boolean isWired() {
		return true;
	}

	public FormFieldInstance getDefinedInstance() {
		return (FormFieldInstance) (isIdDefined() ? getInstance() : null);
	}

	public void setFormFieldInstance(FormFieldInstance t) {
		this.formFieldInstance = t;
		loadAssociations();
	}

	@Override
	public Class<FormFieldInstance> getEntityClass() {
		return FormFieldInstance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (formFieldInstance.getIncident() != null) {
			criteria = criteria.add(Restrictions.eq("incident.id",
					formFieldInstance.getIncident().getId()));
		}

		if (formFieldInstance.getFormField() != null) {
			criteria = criteria.add(Restrictions.eq("formField.id",
					formFieldInstance.getFormField().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (formFieldInstance.getIncident() != null) {
			incidentAction.setInstance(getInstance().getIncident());
		}

		if (formFieldInstance.getFormField() != null) {
			formFieldAction.setInstance(getInstance().getFormField());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
