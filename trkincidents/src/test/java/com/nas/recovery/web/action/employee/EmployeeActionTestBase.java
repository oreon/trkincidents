package com.nas.recovery.web.action.employee;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.employee.Employee;
import com.oreon.incidents.web.action.employee.EmployeeAction;

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
