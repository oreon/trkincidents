package com.oreon.incidents.web.action.customReports;

import com.oreon.incidents.customReports.ReportParameter;

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

public abstract class ReportParameterActionBase
		extends
			BaseAction<ReportParameter> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ReportParameter reportParameter;

	@In(create = true, value = "customReportAction")
	com.oreon.incidents.web.action.customReports.CustomReportAction customReportAction;

	@In(create = true, value = "metaFieldAction")
	com.oreon.incidents.web.action.customReports.MetaFieldAction metaFieldAction;

	@DataModel
	private List<ReportParameter> reportParameterRecordList;

	public void setReportParameterId(Long id) {
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
	public void setReportParameterIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setCustomReportId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomReport(customReportAction.loadFromId(id));

	}

	public Long getCustomReportId() {
		if (getInstance().getCustomReport() != null)
			return getInstance().getCustomReport().getId();
		return 0L;
	}

	public void setMetaFieldId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaField(metaFieldAction.loadFromId(id));

	}

	public Long getMetaFieldId() {
		if (getInstance().getMetaField() != null)
			return getInstance().getMetaField().getId();
		return 0L;
	}

	public Long getReportParameterId() {
		return (Long) getId();
	}

	public ReportParameter getEntity() {
		return reportParameter;
	}

	//@Override
	public void setEntity(ReportParameter t) {
		this.reportParameter = t;
		loadAssociations();
	}

	public ReportParameter getReportParameter() {
		return (ReportParameter) getInstance();
	}

	@Override
	protected ReportParameter createInstance() {
		ReportParameter instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.incidents.customReports.CustomReport customReport = customReportAction
				.getDefinedInstance();
		if (customReport != null && isNew()) {
			getInstance().setCustomReport(customReport);
		}

		com.oreon.incidents.customReports.MetaField metaField = metaFieldAction
				.getDefinedInstance();
		if (metaField != null && isNew()) {
			getInstance().setMetaField(metaField);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ReportParameter getDefinedInstance() {
		return (ReportParameter) (isIdDefined() ? getInstance() : null);
	}

	public void setReportParameter(ReportParameter t) {
		this.reportParameter = t;
		if (reportParameter != null)
			setReportParameterId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ReportParameter> getEntityClass() {
		return ReportParameter.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (reportParameter.getCustomReport() != null) {
			criteria = criteria.add(Restrictions.eq("customReport.id",
					reportParameter.getCustomReport().getId()));
		}

		if (reportParameter.getMetaField() != null) {
			criteria = criteria.add(Restrictions.eq("metaField.id",
					reportParameter.getMetaField().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (reportParameter.getCustomReport() != null) {
			customReportAction.setInstance(getInstance().getCustomReport());
		}

		if (reportParameter.getMetaField() != null) {
			metaFieldAction.setInstance(getInstance().getMetaField());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewReportParameter() {
		load(currentEntityId);
		return "viewReportParameter";
	}

}
