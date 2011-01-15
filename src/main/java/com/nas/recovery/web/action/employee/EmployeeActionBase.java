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

import com.oreon.trkincidents.unusualoccurences.Incident;

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
	com.nas.recovery.web.action.unusualoccurences.IncidentAction incidentsAction;

	@DataModel
	private List<Employee> employeeRecordList;

	public void setEmployeeId(Long id) {
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
	public void setEmployeeIdForModalDlg(Long id) {
		setId(id);
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

		initListIncidents();

	}

	public void updateAssociations() {

		com.oreon.trkincidents.unusualoccurences.Incident incident = (com.oreon.trkincidents.unusualoccurences.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incident.setCreatedBy(employee);
		events.raiseTransactionSuccessEvent("archivedIncident");

	}

	protected List<com.oreon.trkincidents.unusualoccurences.Incident> listIncidents = new ArrayList<com.oreon.trkincidents.unusualoccurences.Incident>();

	void initListIncidents() {

		if (listIncidents.isEmpty())
			listIncidents.addAll(getInstance().getIncidents());

	}

	public List<com.oreon.trkincidents.unusualoccurences.Incident> getListIncidents() {

		prePopulateListIncidents();
		return listIncidents;
	}

	public void prePopulateListIncidents() {
	}

	public void setListIncidents(
			List<com.oreon.trkincidents.unusualoccurences.Incident> listIncidents) {
		this.listIncidents = listIncidents;
	}

	public void deleteIncidents(int index) {
		listIncidents.remove(index);
	}

	@Begin(join = true)
	public void addIncidents() {
		initListIncidents();
		Incident incidents = new Incident();

		incidents.setCreatedBy(getInstance());

		getListIncidents().add(incidents);
	}

	public void updateComposedAssociations() {

		if (listIncidents != null) {
			getInstance().getIncidents().clear();
			getInstance().getIncidents().addAll(listIncidents);
		}
	}

	public void clearLists() {
		listIncidents.clear();

	}

	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.user.userName = ?1";
		return (Employee) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

}
