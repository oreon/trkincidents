package com.nas.recovery.web.action.employee;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.employee.Department;
import com.oreon.incidents.web.action.employee.DepartmentAction;

public class DepartmentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Department> {

	DepartmentAction departmentAction = new DepartmentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Department> getAction() {
		return departmentAction;
	}

}
