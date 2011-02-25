package com.nas.recovery.web.action.customReports;

import com.oreon.trkincidents.customReports.CustomReport;

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

public abstract class CustomReportActionBase extends BaseAction<CustomReport>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomReport customReport;

	@In(create = true, value = "metaEntityAction")
	com.nas.recovery.web.action.customReports.MetaEntityAction metaEntityAction;

	@DataModel
	private List<CustomReport> customReportRecordList;

	public void setCustomReportId(Long id) {
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
	public void setCustomReportIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMetaEntityId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaEntity(metaEntityAction.loadFromId(id));

	}

	public Long getMetaEntityId() {
		if (getInstance().getMetaEntity() != null)
			return getInstance().getMetaEntity().getId();
		return 0L;
	}

	public Long getCustomReportId() {
		return (Long) getId();
	}

	public CustomReport getEntity() {
		return customReport;
	}

	//@Override
	public void setEntity(CustomReport t) {
		this.customReport = t;
		loadAssociations();
	}

	public CustomReport getCustomReport() {
		return (CustomReport) getInstance();
	}

	@Override
	protected CustomReport createInstance() {
		return new CustomReport();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.customReports.MetaEntity metaEntity = metaEntityAction
				.getDefinedInstance();
		if (metaEntity != null && isNew()) {
			getInstance().setMetaEntity(metaEntity);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CustomReport getDefinedInstance() {
		return (CustomReport) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomReport(CustomReport t) {
		this.customReport = t;
		loadAssociations();
	}

	@Override
	public Class<CustomReport> getEntityClass() {
		return CustomReport.class;
	}

	public com.oreon.trkincidents.customReports.CustomReport findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("customReport.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (customReport.getMetaEntity() != null) {
			criteria = criteria.add(Restrictions.eq("metaEntity.id",
					customReport.getMetaEntity().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (customReport.getMetaEntity() != null) {
			metaEntityAction.setInstance(getInstance().getMetaEntity());
		}

		initListFields();
		initListAvailableFields();

		initListGroupFields();
		initListAvailableGroupFields();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.customReports.MetaField> listFields = new ArrayList<com.oreon.trkincidents.customReports.MetaField>();

	void initListFields() {

		if (listFields.isEmpty())
			listFields.addAll(getInstance().getFields());

	}

	public List<com.oreon.trkincidents.customReports.MetaField> getListFields() {

		prePopulateListFields();
		return listFields;
	}

	public void prePopulateListFields() {
	}

	public void setListFields(
			List<com.oreon.trkincidents.customReports.MetaField> listFields) {
		this.listFields = listFields;
	}

	protected List<com.oreon.trkincidents.customReports.MetaField> listAvailableFields = new ArrayList<com.oreon.trkincidents.customReports.MetaField>();

	void initListAvailableFields() {

		listAvailableFields = getEntityManager().createQuery(
				"select r from MetaField r").getResultList();
		listAvailableFields.removeAll(getInstance().getFields());

	}

	@Begin(join = true)
	public List<com.oreon.trkincidents.customReports.MetaField> getListAvailableFields() {

		prePopulateListAvailableFields();
		return listAvailableFields;
	}

	public void prePopulateListAvailableFields() {
	}

	public void setListAvailableFields(
			List<com.oreon.trkincidents.customReports.MetaField> listAvailableFields) {
		this.listAvailableFields = listAvailableFields;
	}

	protected List<com.oreon.trkincidents.customReports.MetaField> listGroupFields = new ArrayList<com.oreon.trkincidents.customReports.MetaField>();

	void initListGroupFields() {

		if (listGroupFields.isEmpty())
			listGroupFields.addAll(getInstance().getGroupFields());

	}

	public List<com.oreon.trkincidents.customReports.MetaField> getListGroupFields() {

		prePopulateListGroupFields();
		return listGroupFields;
	}

	public void prePopulateListGroupFields() {
	}

	public void setListGroupFields(
			List<com.oreon.trkincidents.customReports.MetaField> listGroupFields) {
		this.listGroupFields = listGroupFields;
	}

	protected List<com.oreon.trkincidents.customReports.MetaField> listAvailableGroupFields = new ArrayList<com.oreon.trkincidents.customReports.MetaField>();

	void initListAvailableGroupFields() {

		listAvailableGroupFields = getEntityManager().createQuery(
				"select r from MetaField r").getResultList();
		listAvailableGroupFields.removeAll(getInstance().getGroupFields());

	}

	@Begin(join = true)
	public List<com.oreon.trkincidents.customReports.MetaField> getListAvailableGroupFields() {

		prePopulateListAvailableGroupFields();
		return listAvailableGroupFields;
	}

	public void prePopulateListAvailableGroupFields() {
	}

	public void setListAvailableGroupFields(
			List<com.oreon.trkincidents.customReports.MetaField> listAvailableGroupFields) {
		this.listAvailableGroupFields = listAvailableGroupFields;
	}

	public void updateComposedAssociations() {

		if (listFields != null) {
			getInstance().getFields().clear();
			getInstance().getFields().addAll(listFields);
		}

		if (listGroupFields != null) {
			getInstance().getGroupFields().clear();
			getInstance().getGroupFields().addAll(listGroupFields);
		}
	}

	public void clearLists() {

		listFields.clear();
		listGroupFields.clear();

	}

}
