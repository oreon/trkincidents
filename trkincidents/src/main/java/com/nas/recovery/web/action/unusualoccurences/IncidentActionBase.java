package com.nas.recovery.web.action.unusualoccurences;

import com.oreon.trkincidents.unusualoccurences.Incident;

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

import com.oreon.trkincidents.unusualoccurences.FormFieldInstance;

public abstract class IncidentActionBase extends BaseAction<Incident>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Incident incident;

	@In(create = true, value = "incidentTypeAction")
	com.nas.recovery.web.action.unusualoccurences.IncidentTypeAction incidentTypeAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction createdByAction;

	@In(create = true, value = "departmentAction")
	com.nas.recovery.web.action.employee.DepartmentAction departmentAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction reportedToAction;

	@DataModel
	private List<Incident> incidentRecordList;

	public void setIncidentId(Long id) {
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
	public void setIncidentIdForModalDlg(Long id) {
		setId(id);
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

	public void setCreatedById(Long id) {

		if (id != null && id > 0)
			getInstance().setCreatedBy(createdByAction.loadFromId(id));

	}

	public Long getCreatedById() {
		if (getInstance().getCreatedBy() != null)
			return getInstance().getCreatedBy().getId();
		return 0L;
	}

	public void setDepartmentId(Long id) {

		if (id != null && id > 0)
			getInstance().setDepartment(departmentAction.loadFromId(id));

	}

	public Long getDepartmentId() {
		if (getInstance().getDepartment() != null)
			return getInstance().getDepartment().getId();
		return 0L;
	}

	public void setReportedToId(Long id) {

		if (id != null && id > 0)
			getInstance().setReportedTo(reportedToAction.loadFromId(id));

	}

	public Long getReportedToId() {
		if (getInstance().getReportedTo() != null)
			return getInstance().getReportedTo().getId();
		return 0L;
	}

	public Long getIncidentId() {
		return (Long) getId();
	}

	public Incident getEntity() {
		return incident;
	}

	//@Override
	public void setEntity(Incident t) {
		this.incident = t;
		loadAssociations();
	}

	public Incident getIncident() {
		return (Incident) getInstance();
	}

	@Override
	protected Incident createInstance() {
		return new Incident();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.unusualoccurences.IncidentType incidentType = incidentTypeAction
				.getDefinedInstance();
		if (incidentType != null && isNew()) {
			getInstance().setIncidentType(incidentType);
		}

		com.oreon.trkincidents.employee.Employee createdBy = createdByAction
				.getDefinedInstance();
		if (createdBy != null && isNew()) {
			getInstance().setCreatedBy(createdBy);
		}

		com.oreon.trkincidents.employee.Department department = departmentAction
				.getDefinedInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

		com.oreon.trkincidents.employee.Employee reportedTo = reportedToAction
				.getDefinedInstance();
		if (reportedTo != null && isNew()) {
			getInstance().setReportedTo(reportedTo);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Incident getDefinedInstance() {
		return (Incident) (isIdDefined() ? getInstance() : null);
	}

	public void setIncident(Incident t) {
		this.incident = t;
		loadAssociations();
	}

	@Override
	public Class<Incident> getEntityClass() {
		return Incident.class;
	}

	public String downloadDocument(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getDocument());
		return "success";
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (incident.getIncidentType() != null) {
			criteria = criteria.add(Restrictions.eq("incidentType.id", incident
					.getIncidentType().getId()));
		}

		if (incident.getCreatedBy() != null) {
			criteria = criteria.add(Restrictions.eq("createdBy.id", incident
					.getCreatedBy().getId()));
		}

		if (incident.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", incident
					.getDepartment().getId()));
		}

		if (incident.getReportedTo() != null) {
			criteria = criteria.add(Restrictions.eq("reportedTo.id", incident
					.getReportedTo().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (incident.getIncidentType() != null) {
			incidentTypeAction.setInstance(getInstance().getIncidentType());
		}

		if (incident.getCreatedBy() != null) {
			createdByAction.setInstance(getInstance().getCreatedBy());
		}

		if (incident.getDepartment() != null) {
			departmentAction.setInstance(getInstance().getDepartment());
		}

		if (incident.getReportedTo() != null) {
			reportedToAction.setInstance(getInstance().getReportedTo());
		}

		initListFormFieldInstances();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.unusualoccurences.FormFieldInstance> listFormFieldInstances = new ArrayList<com.oreon.trkincidents.unusualoccurences.FormFieldInstance>();

	void initListFormFieldInstances() {

		if (listFormFieldInstances.isEmpty())
			listFormFieldInstances
					.addAll(getInstance().getFormFieldInstances());

	}

	public List<com.oreon.trkincidents.unusualoccurences.FormFieldInstance> getListFormFieldInstances() {

		prePopulateListFormFieldInstances();
		return listFormFieldInstances;
	}

	public void prePopulateListFormFieldInstances() {
	}

	public void setListFormFieldInstances(
			List<com.oreon.trkincidents.unusualoccurences.FormFieldInstance> listFormFieldInstances) {
		this.listFormFieldInstances = listFormFieldInstances;
	}

	public void deleteFormFieldInstances(int index) {
		listFormFieldInstances.remove(index);
	}

	@Begin(join = true)
	public void addFormFieldInstances() {
		initListFormFieldInstances();
		FormFieldInstance formFieldInstances = new FormFieldInstance();

		formFieldInstances.setIncident(getInstance());

		getListFormFieldInstances().add(formFieldInstances);
	}

	public void updateComposedAssociations() {

		if (listFormFieldInstances != null) {
			getInstance().getFormFieldInstances().clear();
			getInstance().getFormFieldInstances()
					.addAll(listFormFieldInstances);
		}
	}

	public void clearLists() {
		listFormFieldInstances.clear();

	}

}
