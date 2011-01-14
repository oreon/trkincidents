package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.CustomField;

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

public abstract class CustomFieldActionBase extends BaseAction<CustomField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomField customField;

	@In(create = true, value = "customFormAction")
	com.nas.recovery.web.action.customforms.CustomFormAction customFormAction;

	@DataModel
	private List<CustomField> customFieldRecordList;

	public void setCustomFieldId(Long id) {
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
	public void setCustomFieldIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setCustomFormId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomForm(customFormAction.loadFromId(id));

	}

	public Long getCustomFormId() {
		if (getInstance().getCustomForm() != null)
			return getInstance().getCustomForm().getId();
		return 0L;
	}

	public Long getCustomFieldId() {
		return (Long) getId();
	}

	public CustomField getEntity() {
		return customField;
	}

	//@Override
	public void setEntity(CustomField t) {
		this.customField = t;
		loadAssociations();
	}

	public CustomField getCustomField() {
		return (CustomField) getInstance();
	}

	@Override
	protected CustomField createInstance() {
		return new CustomField();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.customforms.CustomForm customForm = customFormAction
				.getDefinedInstance();
		if (customForm != null && isNew()) {
			getInstance().setCustomForm(customForm);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CustomField getDefinedInstance() {
		return (CustomField) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomField(CustomField t) {
		this.customField = t;
		loadAssociations();
	}

	@Override
	public Class<CustomField> getEntityClass() {
		return CustomField.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (customField.getCustomForm() != null) {
			criteria = criteria.add(Restrictions.eq("customForm.id",
					customField.getCustomForm().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (customField.getCustomForm() != null) {
			customFormAction.setInstance(getInstance().getCustomForm());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
