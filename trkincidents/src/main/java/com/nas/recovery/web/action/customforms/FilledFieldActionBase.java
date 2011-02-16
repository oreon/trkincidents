package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.FilledField;

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

public abstract class FilledFieldActionBase extends BaseAction<FilledField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilledField filledField;

	@In(create = true, value = "customFieldAction")
	com.nas.recovery.web.action.customforms.CustomFieldAction customFieldAction;

	@In(create = true, value = "filledFormAction")
	com.nas.recovery.web.action.customforms.FilledFormAction filledFormAction;

	@DataModel
	private List<FilledField> filledFieldRecordList;

	public void setFilledFieldId(Long id) {
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
	public void setFilledFieldIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setCustomFieldId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomField(customFieldAction.loadFromId(id));

	}

	public Long getCustomFieldId() {
		if (getInstance().getCustomField() != null)
			return getInstance().getCustomField().getId();
		return 0L;
	}

	public void setFilledFormId(Long id) {

		if (id != null && id > 0)
			getInstance().setFilledForm(filledFormAction.loadFromId(id));

	}

	public Long getFilledFormId() {
		if (getInstance().getFilledForm() != null)
			return getInstance().getFilledForm().getId();
		return 0L;
	}

	public Long getFilledFieldId() {
		return (Long) getId();
	}

	public FilledField getEntity() {
		return filledField;
	}

	//@Override
	public void setEntity(FilledField t) {
		this.filledField = t;
		loadAssociations();
	}

	public FilledField getFilledField() {
		return (FilledField) getInstance();
	}

	@Override
	protected FilledField createInstance() {
		return new FilledField();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.customforms.CustomField customField = customFieldAction
				.getDefinedInstance();
		if (customField != null && isNew()) {
			getInstance().setCustomField(customField);
		}

		com.oreon.trkincidents.customforms.FilledForm filledForm = filledFormAction
				.getDefinedInstance();
		if (filledForm != null && isNew()) {
			getInstance().setFilledForm(filledForm);
		}

	}

	public boolean isWired() {
		return true;
	}

	public FilledField getDefinedInstance() {
		return (FilledField) (isIdDefined() ? getInstance() : null);
	}

	public void setFilledField(FilledField t) {
		this.filledField = t;
		loadAssociations();
	}

	@Override
	public Class<FilledField> getEntityClass() {
		return FilledField.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (filledField.getCustomField() != null) {
			criteria = criteria.add(Restrictions.eq("customField.id",
					filledField.getCustomField().getId()));
		}

		if (filledField.getFilledForm() != null) {
			criteria = criteria.add(Restrictions.eq("filledForm.id",
					filledField.getFilledForm().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (filledField.getCustomField() != null) {
			customFieldAction.setInstance(getInstance().getCustomField());
		}

		if (filledField.getFilledForm() != null) {
			filledFormAction.setInstance(getInstance().getFilledForm());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
