package com.oreon.incidents.web.action.employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.employee.Employee;

public class EmployeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Employee> {

	EmployeeAction employeeAction = new EmployeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Employee> getAction() {
		return employeeAction;
	}

	@Test
	public void testRetrieveCredentials() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component
						.getInstance("employeeAction");

				// assert(employeeAction.retrieveCredentials()).equals("");
			}

		}.run();
	}

	@Test
	public void testLogin() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component
						.getInstance("employeeAction");

				// assert(employeeAction.login()).equals("");
			}

		}.run();
	}

	@Test
	public void testRegister() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component
						.getInstance("employeeAction");

				// assert(employeeAction.register()).equals("");
			}

		}.run();
	}

}
