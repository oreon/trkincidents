package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.FormField;

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

public abstract class FormFieldActionBase extends BaseAction<FormField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FormField formField;

	@In(create = true, value = "incidentTypeAction")
	com.nas.recovery.web.action.incidents.IncidentTypeAction incidentTypeAction;

	@DataModel
	private List<FormField> formFieldRecordList;

	public void setFormFieldId(Long id) {
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
	public void setFormFieldIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setIncidentTypeId(Long id) {

		if (id != null && id > 0)
			getInstance().setIncidentType(incidentTypeAction.loadFromId(id));

	}

	public Long getIncidentTypeId() {
		if (getInstance().getIncidentType() != null)
			return getInstance().getIncidentType().getId();
		return 0L;
	}

	public Long getFormFieldId() {
		return (Long) getId();
	}

	public FormField getEntity() {
		return formField;
	}

	//@Override
	public void setEntity(FormField t) {
		this.formField = t;
		loadAssociations();
	}

	public FormField getFormField() {
		return (FormField) getInstance();
	}

	@Override
	protected FormField createInstance() {
		return new FormField();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.incidents.IncidentType incidentType = incidentTypeAction
				.getDefinedInstance();
		if (incidentType != null && isNew()) {
			getInstance().setIncidentType(incidentType);
		}

	}

	public boolean isWired() {
		return true;
	}

	public FormField getDefinedInstance() {
		return (FormField) (isIdDefined() ? getInstance() : null);
	}

	public void setFormField(FormField t) {
		this.formField = t;
		loadAssociations();
	}

	@Override
	public Class<FormField> getEntityClass() {
		return FormField.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (formField.getIncidentType() != null) {
			criteria = criteria.add(Restrictions.eq("incidentType.id",
					formField.getIncidentType().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (formField.getIncidentType() != null) {
			incidentTypeAction.setInstance(getInstance().getIncidentType());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
