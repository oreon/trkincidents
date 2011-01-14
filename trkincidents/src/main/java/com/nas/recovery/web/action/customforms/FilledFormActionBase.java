package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.FilledForm;

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

import com.oreon.trkincidents.customforms.FilledField;

public abstract class FilledFormActionBase extends BaseAction<FilledForm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilledForm filledForm;

	@In(create = true, value = "customFormAction")
	com.nas.recovery.web.action.customforms.CustomFormAction customFormAction;

	@DataModel
	private List<FilledForm> filledFormRecordList;

	public void setFilledFormId(Long id) {
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
	public void setFilledFormIdForModalDlg(Long id) {
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

	public Long getFilledFormId() {
		return (Long) getId();
	}

	public FilledForm getEntity() {
		return filledForm;
	}

	//@Override
	public void setEntity(FilledForm t) {
		this.filledForm = t;
		loadAssociations();
	}

	public FilledForm getFilledForm() {
		return (FilledForm) getInstance();
	}

	@Override
	protected FilledForm createInstance() {
		return new FilledForm();
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

	public FilledForm getDefinedInstance() {
		return (FilledForm) (isIdDefined() ? getInstance() : null);
	}

	public void setFilledForm(FilledForm t) {
		this.filledForm = t;
		loadAssociations();
	}

	@Override
	public Class<FilledForm> getEntityClass() {
		return FilledForm.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (filledForm.getCustomForm() != null) {
			criteria = criteria.add(Restrictions.eq("customForm.id", filledForm
					.getCustomForm().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (filledForm.getCustomForm() != null) {
			customFormAction.setInstance(getInstance().getCustomForm());
		}

		initListFilledFields();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.customforms.FilledField> listFilledFields = new ArrayList<com.oreon.trkincidents.customforms.FilledField>();

	void initListFilledFields() {

		if (listFilledFields.isEmpty())
			listFilledFields.addAll(getInstance().getFilledFields());

	}

	public List<com.oreon.trkincidents.customforms.FilledField> getListFilledFields() {

		prePopulateListFilledFields();
		return listFilledFields;
	}

	public void prePopulateListFilledFields() {
	}

	public void setListFilledFields(
			List<com.oreon.trkincidents.customforms.FilledField> listFilledFields) {
		this.listFilledFields = listFilledFields;
	}

	public void deleteFilledFields(int index) {
		listFilledFields.remove(index);
	}

	@Begin(join = true)
	public void addFilledFields() {
		initListFilledFields();
		FilledField filledFields = new FilledField();

		filledFields.setFilledForm(getInstance());

		getListFilledFields().add(filledFields);
	}

	public void updateComposedAssociations() {

		if (listFilledFields != null) {
			getInstance().getFilledFields().clear();
			getInstance().getFilledFields().addAll(listFilledFields);
		}
	}

	public void clearLists() {
		listFilledFields.clear();

	}

}
