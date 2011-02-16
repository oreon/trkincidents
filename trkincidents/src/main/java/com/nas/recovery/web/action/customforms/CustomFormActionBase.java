package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.CustomForm;

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

import com.oreon.trkincidents.customforms.CustomField;

public abstract class CustomFormActionBase extends BaseAction<CustomForm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomForm customForm;

	@DataModel
	private List<CustomForm> customFormRecordList;

	public void setCustomFormId(Long id) {
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
	public void setCustomFormIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getCustomFormId() {
		return (Long) getId();
	}

	public CustomForm getEntity() {
		return customForm;
	}

	//@Override
	public void setEntity(CustomForm t) {
		this.customForm = t;
		loadAssociations();
	}

	public CustomForm getCustomForm() {
		return (CustomForm) getInstance();
	}

	@Override
	protected CustomForm createInstance() {
		return new CustomForm();
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

	public CustomForm getDefinedInstance() {
		return (CustomForm) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomForm(CustomForm t) {
		this.customForm = t;
		loadAssociations();
	}

	@Override
	public Class<CustomForm> getEntityClass() {
		return CustomForm.class;
	}

	public com.oreon.trkincidents.customforms.CustomForm findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("customForm.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListCustomFields();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.customforms.CustomField> listCustomFields = new ArrayList<com.oreon.trkincidents.customforms.CustomField>();

	void initListCustomFields() {

		if (listCustomFields.isEmpty())
			listCustomFields.addAll(getInstance().getCustomFields());

	}

	public List<com.oreon.trkincidents.customforms.CustomField> getListCustomFields() {

		prePopulateListCustomFields();
		return listCustomFields;
	}

	public void prePopulateListCustomFields() {
	}

	public void setListCustomFields(
			List<com.oreon.trkincidents.customforms.CustomField> listCustomFields) {
		this.listCustomFields = listCustomFields;
	}

	public void deleteCustomFields(int index) {
		listCustomFields.remove(index);
	}

	@Begin(join = true)
	public void addCustomFields() {
		initListCustomFields();
		CustomField customFields = new CustomField();

		customFields.setCustomForm(getInstance());

		getListCustomFields().add(customFields);
	}

	public void updateComposedAssociations() {

		if (listCustomFields != null) {
			getInstance().getCustomFields().clear();
			getInstance().getCustomFields().addAll(listCustomFields);
		}
	}

	public void clearLists() {
		listCustomFields.clear();

	}

}
