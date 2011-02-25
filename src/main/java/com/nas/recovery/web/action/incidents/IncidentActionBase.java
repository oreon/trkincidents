package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Incident;

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

import com.oreon.trkincidents.incidents.FormFieldInstance;

public abstract class IncidentActionBase extends BaseAction<Incident>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Incident incident;

	@In(create = true, value = "incidentTypeAction")
	com.nas.recovery.web.action.incidents.IncidentTypeAction incidentTypeAction;

	@In(create = true, value = "patientAction")
	com.nas.recovery.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction createdByAction;

	@In(create = true, value = "departmentAction")
	com.nas.recovery.web.action.employee.DepartmentAction departmentAction;

	@In(create = true, value = "proccedureAction")
	com.nas.recovery.web.action.incidents.ProccedureAction proccedureAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction responsibleEmployeeAction;

	@In(create = true, value = "severityAction")
	com.nas.recovery.web.action.incidents.SeverityAction severityAction;

	@In(create = true, value = "wardAction")
	com.nas.recovery.web.action.incidents.WardAction wardAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.employee.EmployeeAction reportedToAction;

	@In(create = true, value = "drugAction")
	com.nas.recovery.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "morbidityAction")
	com.nas.recovery.web.action.incidents.MorbidityAction morbidityAction;

	@DataModel
	private List<Incident> incidentRecordList;

	public void setIncidentId(Long id) {
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
	public void setIncidentIdForModalDlg(Long id) {
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

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
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

	public void setProccedureId(Long id) {

		if (id != null && id > 0)
			getInstance().setProccedure(proccedureAction.loadFromId(id));

	}

	public Long getProccedureId() {
		if (getInstance().getProccedure() != null)
			return getInstance().getProccedure().getId();
		return 0L;
	}

	public void setResponsibleEmployeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setResponsibleEmployee(
					responsibleEmployeeAction.loadFromId(id));

	}

	public Long getResponsibleEmployeeId() {
		if (getInstance().getResponsibleEmployee() != null)
			return getInstance().getResponsibleEmployee().getId();
		return 0L;
	}

	public void setSeverityId(Long id) {

		if (id != null && id > 0)
			getInstance().setSeverity(severityAction.loadFromId(id));

	}

	public Long getSeverityId() {
		if (getInstance().getSeverity() != null)
			return getInstance().getSeverity().getId();
		return 0L;
	}

	public void setWardId(Long id) {

		if (id != null && id > 0)
			getInstance().setWard(wardAction.loadFromId(id));

	}

	public Long getWardId() {
		if (getInstance().getWard() != null)
			return getInstance().getWard().getId();
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

	public void setDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrug(drugAction.loadFromId(id));

	}

	public Long getDrugId() {
		if (getInstance().getDrug() != null)
			return getInstance().getDrug().getId();
		return 0L;
	}

	public void setMorbidityId(Long id) {

		if (id != null && id > 0)
			getInstance().setMorbidity(morbidityAction.loadFromId(id));

	}

	public Long getMorbidityId() {
		if (getInstance().getMorbidity() != null)
			return getInstance().getMorbidity().getId();
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

		com.oreon.trkincidents.incidents.IncidentType incidentType = incidentTypeAction
				.getDefinedInstance();
		if (incidentType != null && isNew()) {
			getInstance().setIncidentType(incidentType);
		}

		com.oreon.trkincidents.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
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

		com.oreon.trkincidents.incidents.Proccedure proccedure = proccedureAction
				.getDefinedInstance();
		if (proccedure != null && isNew()) {
			getInstance().setProccedure(proccedure);
		}

		com.oreon.trkincidents.employee.Employee responsibleEmployee = responsibleEmployeeAction
				.getDefinedInstance();
		if (responsibleEmployee != null && isNew()) {
			getInstance().setResponsibleEmployee(responsibleEmployee);
		}

		com.oreon.trkincidents.incidents.Severity severity = severityAction
				.getDefinedInstance();
		if (severity != null && isNew()) {
			getInstance().setSeverity(severity);
		}

		com.oreon.trkincidents.incidents.Ward ward = wardAction
				.getDefinedInstance();
		if (ward != null && isNew()) {
			getInstance().setWard(ward);
		}

		com.oreon.trkincidents.employee.Employee reportedTo = reportedToAction
				.getDefinedInstance();
		if (reportedTo != null && isNew()) {
			getInstance().setReportedTo(reportedTo);
		}

		com.oreon.trkincidents.drugs.Drug drug = drugAction
				.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.trkincidents.incidents.Morbidity morbidity = morbidityAction
				.getDefinedInstance();
		if (morbidity != null && isNew()) {
			getInstance().setMorbidity(morbidity);
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

	public void documentUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getDocument() == null)
			getInstance().setDocument(new FileAttachment());
		getInstance().getDocument().setName(uploadItem.getFileName());
		getInstance().getDocument().setContentType(uploadItem.getContentType());
		getInstance().getDocument().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
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

		if (incident.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", incident
					.getPatient().getId()));
		}

		if (incident.getCreatedBy() != null) {
			criteria = criteria.add(Restrictions.eq("createdBy.id", incident
					.getCreatedBy().getId()));
		}

		if (incident.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", incident
					.getDepartment().getId()));
		}

		if (incident.getProccedure() != null) {
			criteria = criteria.add(Restrictions.eq("proccedure.id", incident
					.getProccedure().getId()));
		}

		if (incident.getResponsibleEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("responsibleEmployee.id",
					incident.getResponsibleEmployee().getId()));
		}

		if (incident.getSeverity() != null) {
			criteria = criteria.add(Restrictions.eq("severity.id", incident
					.getSeverity().getId()));
		}

		if (incident.getWard() != null) {
			criteria = criteria.add(Restrictions.eq("ward.id", incident
					.getWard().getId()));
		}

		if (incident.getReportedTo() != null) {
			criteria = criteria.add(Restrictions.eq("reportedTo.id", incident
					.getReportedTo().getId()));
		}

		if (incident.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", incident
					.getDrug().getId()));
		}

		if (incident.getMorbidity() != null) {
			criteria = criteria.add(Restrictions.eq("morbidity.id", incident
					.getMorbidity().getId()));
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

		if (incident.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

		if (incident.getCreatedBy() != null) {
			createdByAction.setInstance(getInstance().getCreatedBy());
		}

		if (incident.getDepartment() != null) {
			departmentAction.setInstance(getInstance().getDepartment());
		}

		if (incident.getProccedure() != null) {
			proccedureAction.setInstance(getInstance().getProccedure());
		}

		if (incident.getResponsibleEmployee() != null) {
			responsibleEmployeeAction.setInstance(getInstance()
					.getResponsibleEmployee());
		}

		if (incident.getSeverity() != null) {
			severityAction.setInstance(getInstance().getSeverity());
		}

		if (incident.getWard() != null) {
			wardAction.setInstance(getInstance().getWard());
		}

		if (incident.getReportedTo() != null) {
			reportedToAction.setInstance(getInstance().getReportedTo());
		}

		if (incident.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
		}

		if (incident.getMorbidity() != null) {
			morbidityAction.setInstance(getInstance().getMorbidity());
		}

		initListFormFieldInstances();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.incidents.FormFieldInstance> listFormFieldInstances = new ArrayList<com.oreon.trkincidents.incidents.FormFieldInstance>();

	void initListFormFieldInstances() {

		if (listFormFieldInstances.isEmpty())
			listFormFieldInstances
					.addAll(getInstance().getFormFieldInstances());

	}

	public List<com.oreon.trkincidents.incidents.FormFieldInstance> getListFormFieldInstances() {

		prePopulateListFormFieldInstances();
		return listFormFieldInstances;
	}

	public void prePopulateListFormFieldInstances() {
	}

	public void setListFormFieldInstances(
			List<com.oreon.trkincidents.incidents.FormFieldInstance> listFormFieldInstances) {
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
