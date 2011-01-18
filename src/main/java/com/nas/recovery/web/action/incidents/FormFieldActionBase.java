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

public abstract class FormFieldActionBase extends BaseAction<FormField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FormField formField;

	@DataModel
	private List<FormField> formFieldRecordList;

	public void setFormFieldId(Long id) {
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
	public void setFormFieldIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
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

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
