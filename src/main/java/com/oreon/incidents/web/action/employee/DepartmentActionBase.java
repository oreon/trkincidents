package com.oreon.incidents.web.action.employee;

import com.oreon.incidents.employee.Department;

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

import com.oreon.incidents.employee.Employee;
import com.oreon.incidents.incidents.Incident;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Department department;

	@In(create = true, value = "employeeAction")
	com.oreon.incidents.web.action.employee.EmployeeAction employeesAction;

	@In(create = true, value = "incidentAction")
	com.oreon.incidents.web.action.incidents.IncidentAction incidentsAction;

	@DataModel
	private List<Department> departmentRecordList;

	public void setDepartmentId(Long id) {
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
	public void setDepartmentIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	public Department getEntity() {
		return department;
	}

	//@Override
	public void setEntity(Department t) {
		this.department = t;
		loadAssociations();
	}

	public Department getDepartment() {
		return (Department) getInstance();
	}

	@Override
	protected Department createInstance() {
		Department instance = super.createInstance();

		return instance;
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

	public Department getDefinedInstance() {
		return (Department) (isIdDefined() ? getInstance() : null);
	}

	public void setDepartment(Department t) {
		this.department = t;
		if (department != null)
			setDepartmentId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	public com.oreon.incidents.employee.Department findByUnqName(String name) {
		return executeSingleResultNamedQuery("department.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListEmployees();

		initListIncidents();

	}

	public void updateAssociations() {

		com.oreon.incidents.employee.Employee employees = (com.oreon.incidents.employee.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employees.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

		com.oreon.incidents.incidents.Incident incidents = (com.oreon.incidents.incidents.Incident) org.jboss.seam.Component
				.getInstance("incident");
		incidents.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedIncident");

	}

	protected List<com.oreon.incidents.employee.Employee> listEmployees = new ArrayList<com.oreon.incidents.employee.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.oreon.incidents.employee.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.oreon.incidents.employee.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {
		initListEmployees();
		Employee employees = new Employee();

		employees.setDepartment(getInstance());

		getListEmployees().add(employees);
	}

	protected List<com.oreon.incidents.incidents.Incident> listIncidents = new ArrayList<com.oreon.incidents.incidents.Incident>();

	void initListIncidents() {

		if (listIncidents.isEmpty())
			listIncidents.addAll(getInstance().getIncidents());

	}

	public List<com.oreon.incidents.incidents.Incident> getListIncidents() {

		prePopulateListIncidents();
		return listIncidents;
	}

	public void prePopulateListIncidents() {
	}

	public void setListIncidents(
			List<com.oreon.incidents.incidents.Incident> listIncidents) {
		this.listIncidents = listIncidents;
	}

	public void deleteIncidents(int index) {
		listIncidents.remove(index);
	}

	@Begin(join = true)
	public void addIncidents() {
		initListIncidents();
		Incident incidents = new Incident();

		incidents.setDepartment(getInstance());

		getListIncidents().add(incidents);
	}

	public void updateComposedAssociations() {

		if (listEmployees != null) {
			getInstance().getEmployees().clear();
			getInstance().getEmployees().addAll(listEmployees);
		}

		if (listIncidents != null) {
			getInstance().getIncidents().clear();
			getInstance().getIncidents().addAll(listIncidents);
		}
	}

	public void clearLists() {
		listEmployees.clear();
		listIncidents.clear();

	}

	public String viewDepartment() {
		load(currentEntityId);
		return "viewDepartment";
	}

}
