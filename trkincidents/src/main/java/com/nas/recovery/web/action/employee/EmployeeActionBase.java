package com.nas.recovery.web.action.employee;

import com.oreon.trkincidents.employee.Employee;

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

import com.oreon.trkincidents.incidents.Incident;
import com.oreon.trkincidents.incidents.Incident;
import com.oreon.trkincidents.incidents.Incident;

public abstract class EmployeeActionBase
		extends
			com.nas.recovery.web.action.patient.PersonAction<Employee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Employee employee;

	@In(create = true, value = "userAction")
	com.nas.recovery.web.action.users.UserAction userAction;

	@In(create = true, value = "departmentAction")
	com.nas.recovery.web.action.employee.DepartmentAction departmentAction;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentsCreatedAction;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentsResponsibleForAction;

	@In(create = true, value = "incidentAction")
	com.nas.recovery.web.action.incidents.IncidentAction incidentsReportedAction;

	@DataModel
	private List<Employee> employeeRecordList;

	public void setEmployeeId(Long id) {
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
	public void setEmployeeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setUser(userAction.loadFromId(id));

	}

	public Long getUserId() {
		if (getInstance().getUser() != null)
			return getInstance().getUser().getId();
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

	public Long getEmployeeId() {
		return (Long) getId();
	}

	public Employee getEntity() {
		return employee;
	}

	//@Override
	public void setEntity(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	public Employee getEmployee() {
		return (Employee) getInstance();
	}

	@Override
	protected Employee createInstance() {
		return new Employee();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.users.User user = userAction
				.getDefinedInstance();
		if (user != null && isNew()) {
			getInstance().setUser(user);
		}

		com.oreon.trkincidents.employee.Department department = departmentAction
				.getDefinedInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Employee getDefinedInstance() {
		return (Employee) (isIdDefined() ? getInstance() : null);
	}

	public void setEmployee(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (employee.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", employee
					.getUser().getId()));
		}

		if (employee.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", employee
					.getDepartment().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (employee.getUser() != null) {
			userAction.setInstance(getInstance().getUser());
		}

		if (employee.getDepartment() != null) {
			departmentAction.setInstance(getInstance().getDepartment());
		}

		initListIncidentsCreated();

		initListIncidentsResponsibleFor();

		initListIncidentsReported();

	}

	public void updateAssociations() {

		com.oreon.trkincidents.incidents.Incident incidentsCreated = (com.oreon.trkincidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidentsCreated.setCreatedBy(employee);
		events.raiseTransactionSuccessEvent("archivedIncident");

		com.oreon.trkincidents.incidents.Incident incidentsResponsibleFor = (com.oreon.trkincidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidentsResponsibleFor.setResponsibleEmployee(employee);
		events.raiseTransactionSuccessEvent("archivedIncident");

		com.oreon.trkincidents.incidents.Incident incidentsReported = (com.oreon.trkincidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidentsReported.setReportedTo(employee);
		events.raiseTransactionSuccessEvent("archivedIncident");

	}

	protected List<com.oreon.trkincidents.incidents.Incident> listIncidentsCreated = new ArrayList<com.oreon.trkincidents.incidents.Incident>();

	void initListIncidentsCreated() {

		if (listIncidentsCreated.isEmpty())
			listIncidentsCreated.addAll(getInstance().getIncidentsCreated());

	}

	public List<com.oreon.trkincidents.incidents.Incident> getListIncidentsCreated() {

		prePopulateListIncidentsCreated();
		return listIncidentsCreated;
	}

	public void prePopulateListIncidentsCreated() {
	}

	public void setListIncidentsCreated(
			List<com.oreon.trkincidents.incidents.Incident> listIncidentsCreated) {
		this.listIncidentsCreated = listIncidentsCreated;
	}

	public void deleteIncidentsCreated(int index) {
		listIncidentsCreated.remove(index);
	}

	@Begin(join = true)
	public void addIncidentsCreated() {
		initListIncidentsCreated();
		Incident incidentsCreated = new Incident();

		incidentsCreated.setCreatedBy(getInstance());

		getListIncidentsCreated().add(incidentsCreated);
	}

	protected List<com.oreon.trkincidents.incidents.Incident> listIncidentsResponsibleFor = new ArrayList<com.oreon.trkincidents.incidents.Incident>();

	void initListIncidentsResponsibleFor() {

		if (listIncidentsResponsibleFor.isEmpty())
			listIncidentsResponsibleFor.addAll(getInstance()
					.getIncidentsResponsibleFor());

	}

	public List<com.oreon.trkincidents.incidents.Incident> getListIncidentsResponsibleFor() {

		prePopulateListIncidentsResponsibleFor();
		return listIncidentsResponsibleFor;
	}

	public void prePopulateListIncidentsResponsibleFor() {
	}

	public void setListIncidentsResponsibleFor(
			List<com.oreon.trkincidents.incidents.Incident> listIncidentsResponsibleFor) {
		this.listIncidentsResponsibleFor = listIncidentsResponsibleFor;
	}

	public void deleteIncidentsResponsibleFor(int index) {
		listIncidentsResponsibleFor.remove(index);
	}

	@Begin(join = true)
	public void addIncidentsResponsibleFor() {
		initListIncidentsResponsibleFor();
		Incident incidentsResponsibleFor = new Incident();

		incidentsResponsibleFor.setResponsibleEmployee(getInstance());

		getListIncidentsResponsibleFor().add(incidentsResponsibleFor);
	}

	protected List<com.oreon.trkincidents.incidents.Incident> listIncidentsReported = new ArrayList<com.oreon.trkincidents.incidents.Incident>();

	void initListIncidentsReported() {

		if (listIncidentsReported.isEmpty())
			listIncidentsReported.addAll(getInstance().getIncidentsReported());

	}

	public List<com.oreon.trkincidents.incidents.Incident> getListIncidentsReported() {

		prePopulateListIncidentsReported();
		return listIncidentsReported;
	}

	public void prePopulateListIncidentsReported() {
	}

	public void setListIncidentsReported(
			List<com.oreon.trkincidents.incidents.Incident> listIncidentsReported) {
		this.listIncidentsReported = listIncidentsReported;
	}

	public void deleteIncidentsReported(int index) {
		listIncidentsReported.remove(index);
	}

	@Begin(join = true)
	public void addIncidentsReported() {
		initListIncidentsReported();
		Incident incidentsReported = new Incident();

		incidentsReported.setReportedTo(getInstance());

		getListIncidentsReported().add(incidentsReported);
	}

	public void updateComposedAssociations() {

		if (listIncidentsCreated != null) {
			getInstance().getIncidentsCreated().clear();
			getInstance().getIncidentsCreated().addAll(listIncidentsCreated);
		}

		if (listIncidentsResponsibleFor != null) {
			getInstance().getIncidentsResponsibleFor().clear();
			getInstance().getIncidentsResponsibleFor().addAll(
					listIncidentsResponsibleFor);
		}

		if (listIncidentsReported != null) {
			getInstance().getIncidentsReported().clear();
			getInstance().getIncidentsReported().addAll(listIncidentsReported);
		}
	}

	public void clearLists() {
		listIncidentsCreated.clear();
		listIncidentsResponsibleFor.clear();
		listIncidentsReported.clear();

	}

	public String retrieveCredentials() {

		return null;

	}

	public String login() {

		return null;

	}

	public String register() {

		return null;

	}

	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.user.userName = ?1";
		return (Employee) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

}
